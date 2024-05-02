package com.TimeTrackR.TimeTrackR.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private final MongoOperations mongoOperations;

    public TaskService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public List<Tasks> getAllTasks() {
        return mongoOperations.findAll(Tasks.class);
    }

    public Tasks getTaskById(String id) {
        return mongoOperations.findById(id, Tasks.class);
    }

    public Tasks addTask(Tasks tasks) {
        mongoOperations.save(tasks);
        return tasks;
    }

    public Tasks removeTask(String id) {
        return null;
    }

    public Tasks startTask(String id) {
        Tasks task = getTaskById(id);

        LocalDateTime now = LocalDateTime.now();

        task.setIsActive(true);
        task.setStartDate(now);
        
        return mongoOperations.save(task);
    }

    public Tasks stopTask(String id) {
        Tasks task = getTaskById(id);
        LocalDateTime now = LocalDateTime.now();

        task.setIsActive(false);
        task.setEndDate(now);
        return mongoOperations.save(task);
    }

    public Tasks editTask(Tasks tasks, String id) {

        return tasks;
    }

}
