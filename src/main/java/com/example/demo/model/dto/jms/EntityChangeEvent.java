package com.example.demo.model.dto.jms;

import com.example.demo.model.jms.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class EntityChangeEvent implements Serializable {

    private EventType eventType;
    private String entityType;
    private Long entityId;
    private Map<String, Object> changeData;
    private LocalDateTime timestamp;
}
