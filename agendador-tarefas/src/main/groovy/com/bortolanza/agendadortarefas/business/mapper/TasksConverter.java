package com.bortolanza.agendadortarefas.business.mapper;

import com.bortolanza.agendadortarefas.business.dto.TasksDTO;
import com.bortolanza.agendadortarefas.infrastructure.entity.TasksEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TasksConverter {

    TasksEntity forTaskEntity(TasksDTO dto);

    TasksDTO forTaskDTO(TasksEntity entity);
}
