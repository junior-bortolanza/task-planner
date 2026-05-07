package com.bortolanza.agendadortarefas.business;

import com.bortolanza.agendadortarefas.business.dto.TasksDTO;
import com.bortolanza.agendadortarefas.business.mapper.TasksConverter;
import com.bortolanza.agendadortarefas.business.mapper.UpdateTaskConverter;
import com.bortolanza.agendadortarefas.infrastructure.entity.TasksEntity;
import com.bortolanza.agendadortarefas.infrastructure.enums.StatusNotificationEnum;
import com.bortolanza.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.bortolanza.agendadortarefas.infrastructure.repository.TasksRepository;
import com.bortolanza.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksService {

    private final TasksRepository tasksRepository;
    private final TasksConverter tasksConverter;
    private final JwtUtil jwtUtil;
    private final UpdateTaskConverter updateTaskConverter;

    public TasksDTO saveTask(String token, TasksDTO dto) {
        String email = jwtUtil.extractEmailToken(token.substring(7));

        dto.setCreationDate(LocalDateTime.now());
        dto.setStatusNotificationEnum(StatusNotificationEnum.PENDING);
        dto.setUserEmail(email);
        TasksEntity entity = tasksConverter.forTaskEntity(dto);

        return tasksConverter.forTaskDTO(tasksRepository.save(entity));
    }

    public List<TasksDTO> SearchScheduledTasksByPeriod(LocalDateTime initialDate, LocalDateTime finalDate) {
        return tasksConverter.forListTasksDTO(
                tasksRepository.findByEventDateBetween(initialDate, finalDate));
    }

    public List<TasksDTO> searchTasksByEmail(String token) {
        String email = jwtUtil.extractEmailToken(token.substring(7));
        List<TasksEntity> tasks = tasksRepository.findByUserEmail(email);

        return tasksConverter.forListTasksDTO(tasks);
    }
    public void deleteTaskById(String id) {
        try{
            tasksRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error to delete task with id, id does not exist " + id,
                    e.getCause());
        }
    }

    public TasksDTO changingStatus(StatusNotificationEnum status, String id) {
        try {
            TasksEntity entity = tasksRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " does not exist"));
            entity.setStatusNotificationEnum(status);
            return tasksConverter.forTaskDTO(tasksRepository.save(entity));
        }
        catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error changing task status", e.getCause());
        }
    }

    public TasksDTO updateTasks(TasksDTO dto, String id) {
        try {
            TasksEntity entity = tasksRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Task not found" + id));
            updateTaskConverter.updateTasks(dto, entity);
            return tasksConverter.forTaskDTO(tasksRepository.save(entity));
        }
        catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error changing task status", e.getCause());
        }
    }
}
