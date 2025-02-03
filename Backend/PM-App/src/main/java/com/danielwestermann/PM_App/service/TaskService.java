package com.danielwestermann.PM_App.service;

import com.danielwestermann.PM_App.entity.Task;
import com.danielwestermann.PM_App.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
