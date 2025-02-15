package com.danielwestermann.PM_App.controller;

import com.danielwestermann.PM_App.entity.Task;
import com.danielwestermann.PM_App.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService=taskService;
    }

    @GetMapping
    public List<Task> getAll() {
        return taskService.findAll();
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        log.debug("Task fields - name: '{}', description: '{}', status: '{}', projectId: {}, userId: {}, dueDate: {}",
                task.getName(),
                task.getDescription(),
                task.getStatus(),
                task.getProjectId(),
                task.getUserId(),
                task.getDueDate());
        return taskService.save(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        Optional<Task> existingTask = taskService.findById(id);
        if(existingTask.isPresent()){
            taskService.delete(existingTask.get());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}