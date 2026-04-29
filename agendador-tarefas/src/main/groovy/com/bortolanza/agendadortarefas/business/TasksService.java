package com.bortolanza.agendadortarefas.business;

import com.bortolanza.agendadortarefas.business.dto.TasksDTO;
import com.bortolanza.agendadortarefas.business.mapper.TasksConverter;
import com.bortolanza.agendadortarefas.infrastructure.entity.TasksEntity;
import com.bortolanza.agendadortarefas.infrastructure.enums.StatusNotificationEnum;
import com.bortolanza.agendadortarefas.infrastructure.repository.TasksRepository;
import com.bortolanza.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TasksService {

    private final TasksRepository tasksRepository;
    private final TasksConverter tasksConverter;
    private final JwtUtil jwtUtil;

    public TasksDTO saveTask(String token, TasksDTO dto) {
        String email = jwtUtil.extractEmailToken(token.substring(7));

        dto.setCreationDate(LocalDateTime.now());
        dto.setStatusNotificationEnum(StatusNotificationEnum.PENDING);
        dto.setUserEmail(email);
        TasksEntity entity = tasksConverter.forTaskEntity(dto);

        return tasksConverter.forTaskDTO(tasksRepository.save(entity));
    }
}
