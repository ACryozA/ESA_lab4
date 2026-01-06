package com.example.demo.listener;

import com.example.demo.model.jms.EventType;
import com.example.demo.service.jms.EntityEventService;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityLogListener {
    private final EntityEventService eventService;

    @Autowired
    public EntityLogListener(EntityEventService eventService) {
        this.eventService = eventService;
    }

    @PostPersist
    public void onPostPersist(Object entity) {
        eventService.sendEntityChangeEvent(entity, EventType.CREATE);
    }

    @PostUpdate
    public void onPostUpdate(Object entity) {
        eventService.sendEntityChangeEvent(entity, EventType.UPDATE);
    }

    @PostRemove
    public void onPostRemove(Object entity) {
        eventService.sendEntityChangeEvent(entity, EventType.DELETE);
    }
}
