package com.bortolanza.agendadortarefas.controller;

import com.bortolanza.agendadortarefas.business.TasksService;
import com.bortolanza.agendadortarefas.business.dto.TasksDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TasksController {

    private final TasksService tasksService;

    @PostMapping
    public ResponseEntity<TasksDTO> save(@RequestBody TasksDTO dto,
                                         @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tasksService.saveTask(token, dto));
    }
}
