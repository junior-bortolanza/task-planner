package com.bortolanza.agendadortarefas.business.dto;

import com.bortolanza.agendadortarefas.infrastructure.enums.StatusNotificationEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TasksDTO {

    private String id;
    private String taskName;
    private String taskDescription;
    private LocalDateTime creationDate;
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime eventDate;
    private String userEmail;
    private LocalDateTime alterationDate;
    private StatusNotificationEnum statusNotificationEnum;
}
