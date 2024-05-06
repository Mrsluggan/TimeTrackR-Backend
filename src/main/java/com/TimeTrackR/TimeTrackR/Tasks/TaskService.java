package com.TimeTrackR.TimeTrackR.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

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
        Tasks task = getTaskById(id);
        mongoOperations.remove(task);
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
        task.setTotalTime(task.getTotalTime() + countHours(task.getStartDate(), task.getEndDate()));

        return mongoOperations.save(task);
    }

    public Tasks editTask(Tasks tasks, String id) {

        return tasks;
    }

    public Long countHours(LocalDateTime start, LocalDateTime stop) {

        long seconds = ChronoUnit.SECONDS.between(start, stop);
        long hours = seconds / 3600;

        System.out.println("Antal timmar: " + hours + "  Antal minuter: " + seconds / 60);
        return hours;
    }

}
