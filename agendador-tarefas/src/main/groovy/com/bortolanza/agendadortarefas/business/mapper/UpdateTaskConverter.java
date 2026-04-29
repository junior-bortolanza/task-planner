package com.bortolanza.agendadortarefas.business.mapper;

import com.bortolanza.agendadortarefas.business.dto.TasksDTO;
import com.bortolanza.agendadortarefas.infrastructure.entity.TasksEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UpdateTaskConverter {
    void updateTasks(TasksDTO dto, @MappingTarget TasksEntity entity);
}
