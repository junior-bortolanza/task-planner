package com.bortolanza.agendadortarefas.infrastructure.entity;

import com.bortolanza.agendadortarefas.infrastructure.enums.StatusNotificationEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("task")
public class TasksEntity {
    @Id
    private String id;
    private String taskName;
    private String taskDescription;
    private LocalDateTime creationDate;
    private LocalDateTime eventDate;
    private String userEmail;
    private LocalDateTime alterationDate;
    private StatusNotificationEnum statusNotificationEnum;

}
