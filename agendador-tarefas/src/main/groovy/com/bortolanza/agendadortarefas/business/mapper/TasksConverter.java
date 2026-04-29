package com.bortolanza.agendadortarefas.business.mapper;

import com.bortolanza.agendadortarefas.business.dto.TasksDTO;
import com.bortolanza.agendadortarefas.infrastructure.entity.TasksEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TasksConverter {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "eventDate", target = "eventDate")
    @Mapping(source = "creationDate", target = "creationDate")

    TasksEntity forTaskEntity(TasksDTO dto);

    TasksDTO forTaskDTO(TasksEntity entity);

    List<TasksEntity> forListTasksEntity(List<TasksDTO> dtos);

    List<TasksDTO> forListTasksDTO(List<TasksEntity> entities);
}
