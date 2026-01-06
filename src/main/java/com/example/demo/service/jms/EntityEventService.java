package com.example.demo.service.jms;

import com.example.demo.model.jms.EventType;
import com.example.demo.model.dto.jms.EntityChangeEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.Message;
import jakarta.jms.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class EntityEventService {

    private final JmsTemplate jmsTemplate;
    private final Queue logQueue;
    private final ObjectMapper objectMapper;

    @Autowired
    public EntityEventService(JmsTemplate jmsTemplate,
                              Queue logQueue,
                              ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.logQueue = logQueue;
        this.objectMapper = objectMapper;
    }

    public void sendEntityChangeEvent(Object entity,
                                      EventType eventType) {
        EntityChangeEvent event = createEvent(entity, eventType);
        jmsTemplate.send(logQueue, session -> {
            Message message = session.createObjectMessage(event);
            message.setStringProperty("EVENT_TYPE", event.getEventType().name());
            return message;
        });
    }

    private EntityChangeEvent createEvent(Object entity, EventType eventType) {
        try {
            String entityType = entity.getClass().getSimpleName();
            Long entityId = getEntityId(entity);

            Map<String, Object> changeData = new HashMap<>();
            if (eventType != EventType.DELETE) {
                changeData = objectMapper.convertValue(entity, Map.class);
            }

            return new EntityChangeEvent(
                    eventType,
                    entityType,
                    entityId,
                    changeData,
                    LocalDateTime.now()
            );
        } catch (Exception e) {
            throw new RuntimeException("Error creating entity change event", e);
        }
    }

    private Long getEntityId(Object entity) throws Exception {
        Method getIdMethod = entity.getClass().getMethod("getId");
        return (Long) getIdMethod.invoke(entity);
    }
}
