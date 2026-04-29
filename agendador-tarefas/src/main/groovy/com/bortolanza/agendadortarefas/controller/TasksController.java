package com.bortolanza.agendadortarefas.controller;

import com.bortolanza.agendadortarefas.business.TasksService;
import com.bortolanza.agendadortarefas.business.dto.TasksDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping("/eventos")
    public ResponseEntity<List<TasksDTO>> searchListTasksForPeriod(
            @RequestParam("initialDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime initialDate,
            @RequestParam("finalDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalDate) {
        return ResponseEntity.ok(tasksService.SearchScheduledTasksByPeriod(initialDate, finalDate));
    }

    @GetMapping
    public ResponseEntity<List<TasksDTO>> searchTasksByEmail(@RequestHeader("Authorization")  String token) {

        return ResponseEntity.ok(tasksService.searchTasksByEmail(token));
    }

}
