package com.bortolanza.agendadortarefas.controller;

import com.bortolanza.agendadortarefas.business.TasksService;
import com.bortolanza.agendadortarefas.business.dto.TasksDTO;
import com.bortolanza.agendadortarefas.infrastructure.entity.TasksEntity;
import com.bortolanza.agendadortarefas.infrastructure.enums.StatusNotificationEnum;
import com.bortolanza.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.bortolanza.agendadortarefas.infrastructure.repository.TasksRepository;
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
    private final TasksRepository tasksRepository;

    @PostMapping
    public ResponseEntity<TasksDTO> save(@RequestBody TasksDTO dto,
                                         @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tasksService.saveTask(token, dto));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TasksDTO>> searchListTasksForPeriod(
            @RequestParam("initialDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime initialDate,
            @RequestParam("finalDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalDate) {
        return ResponseEntity.ok(tasksService.searchScheduledTasksByPeriod(initialDate, finalDate));
    }

    @GetMapping
    public ResponseEntity<List<TasksDTO>> searchTasksByEmail(@RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(tasksService.searchTasksByEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTaskById(@RequestParam("id") String id) {
        tasksService.deleteTaskById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TasksDTO> changingStatusNotification(@RequestParam("status") StatusNotificationEnum status,
                                                               @RequestParam("id") String id) {
        return ResponseEntity.ok(tasksService.changingStatus(status, id));
    }

    @PutMapping
     public ResponseEntity<TasksDTO> updateTasks(@RequestBody TasksDTO dto,
                                                 @RequestParam("id") String id) {
        return ResponseEntity.ok(tasksService.updateTasks(dto, id));

    }
}