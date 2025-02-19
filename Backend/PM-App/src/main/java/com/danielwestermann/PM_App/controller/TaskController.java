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

    @PutMapping
    @RequestMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable(value = "id") Long taskId, @RequestBody Task taskDetails){
        Task updatedTask = taskService.updateTask(taskId, taskDetails);

        if(updatedTask == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedTask);
        }
    }

    @PutMapping(value = "/{id}/{status}", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable("id") Long taskId, @PathVariable("status") String newStatus) {
        Task updatedTask = taskService.updateTaskStatus(taskId, newStatus);

        if (updatedTask == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedTask);
        }
    }
}