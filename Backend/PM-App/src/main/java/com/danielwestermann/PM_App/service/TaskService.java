package com.danielwestermann.PM_App.service;

import com.danielwestermann.PM_App.entity.Task;
import com.danielwestermann.PM_App.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final Logger log = LoggerFactory.getLogger(TaskService.class);

    public TaskService(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }

    public Task save(Task task){
        return taskRepository.save(task);
    }

    public List<Task> findAll(){
        log.debug("Find All Tasks");
        return taskRepository.findAll();
    }

    public Optional<Task> findById(Long id){
        log.debug("Searching for Task with ID: " + id);
        return taskRepository.findById(id);
    }

    public List<Task> findByStatus(String status){
        log.debug("Searching for Tasks with status: " + status);
        return taskRepository.findByStatus(status);
    }

    public List<Task> findByProjectId(Long projectId){
        log.debug("Searching for Tasks with projectId: " + projectId);
        return taskRepository.findByProjectId(projectId);
    }

    public Task updateTask(Long id, Task taskDetails){
        log.debug("Updating task with id: " + id);
        Optional<Task> taskOptional = taskRepository.findById(id);

        if(taskOptional.isPresent()){
            Task task = taskOptional.get();
            task.setDescription(taskDetails.getDescription());
            task.setDueDate(taskDetails.getDueDate());
            task.setTitle(taskDetails.getTitle());
            task.setStatus(taskDetails.getStatus());
            task.setProjectId(taskDetails.getProjectId());
            task.setUserId(taskDetails.getUserId());
            return taskRepository.save(task);
        }else{
            return null;
        }
    }

    public void delete(Task task){
        log.debug("Deleting Task with ID: " + task.getId());
        taskRepository.delete(task);
    }
}
