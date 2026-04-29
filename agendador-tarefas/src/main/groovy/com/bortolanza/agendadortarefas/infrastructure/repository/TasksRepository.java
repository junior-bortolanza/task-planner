package com.bortolanza.agendadortarefas.infrastructure.repository;

import com.bortolanza.agendadortarefas.infrastructure.entity.TasksEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends MongoRepository<TasksEntity, String> {
}
