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

    //GET
    @GetMapping("/all")
    public List<Task> getAll() { return taskService.findAll(); }

    @GetMapping("/status")
    public List<Task> findByStatus(@RequestParam String status) { return taskService.findByStatus(status); }

    @GetMapping("/project")
    public List<Task> findByProjectId(@RequestParam Long projectId) { return taskService.findByProjectId(projectId); }

    //POST
    @PostMapping
    public Task create(@RequestBody Task task) {
        log.debug("Task fields - title: '{}', description: '{}', status: '{}', projectId: {}, userId: {}, dueDate: {}",
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getProjectId(),
                task.getUserId(),
                task.getDueDate());
        return taskService.save(task);
    }

    //PUT
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

    //DELETE
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