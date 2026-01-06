package com.example.demo.listener;

import com.example.demo.model.dto.jms.EntityChangeEvent;
import com.example.demo.model.jms.EventType;
import com.example.demo.model.jms.LogEntity;
import com.example.demo.repository.LogEntityRepository;
import com.example.demo.service.mail.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.Message;
import jakarta.jms.ObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class LogMessageListener {

    private final LogEntityRepository logEntityRepository;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    @Autowired
    public LogMessageListener(LogEntityRepository logEntityRepository,
                              ObjectMapper objectMapper,
                              EmailService emailService) {
        this.logEntityRepository = logEntityRepository;
        this.objectMapper = objectMapper;
        this.emailService = emailService;
    }

    @JmsListener(destination = "log.queue")
    public void processLogMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                ObjectMessage objectMessage = (ObjectMessage) message;
                EntityChangeEvent event = (EntityChangeEvent) objectMessage.getObject();

                validateEvent(event);

                trySendEmail(event);

                LogEntity logEntity = new LogEntity();
                logEntity.setEventType(event.getEventType());
                logEntity.setEntityType(event.getEntityType());
                logEntity.setEntityId(event.getEntityId());
                logEntity.setChangeDescription(createChangeDescription(event));
                logEntity.setChangeData(objectMapper.writeValueAsString(event.getChangeData()));
                logEntity.setTimestamp(event.getTimestamp());

                logEntityRepository.save(logEntity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void validateEvent(EntityChangeEvent event) {
        if (event.getEventType() == null) {
            throw new IllegalArgumentException("Event type cannot be null");
        }
        if (event.getEntityType() == null || event.getEntityType().isEmpty()) {
            throw new IllegalArgumentException("Entity type cannot be null or empty");
        }
        if (event.getTimestamp() == null) {
            throw new IllegalArgumentException("Timestamp cannot be null");
        }
    }

    private void trySendEmail(EntityChangeEvent event) {
        if (event.getEventType() == EventType.DELETE) {
            emailService.sendMessage("Entity was deleted", createChangeDescription(event));
        }
    }

    private String createChangeDescription(EntityChangeEvent event) {
        return String.format("%s with id %d was %s",
                event.getEntityType(),
                event.getEntityId(),
                event.getEventType().name().toLowerCase());
    }
}
