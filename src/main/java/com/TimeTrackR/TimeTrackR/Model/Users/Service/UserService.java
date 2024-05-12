package com.TimeTrackR.TimeTrackR.Model.Users.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.TimeTrackR.TimeTrackR.Model.Tasks.TaskService;
import com.TimeTrackR.TimeTrackR.Model.Tasks.Tasks;
import com.TimeTrackR.TimeTrackR.Model.Users.Model.Users;

@Service
public class UserService {

    private final MongoOperations mongoOperations;
    private final TaskService taskService;

    public UserService(MongoOperations mongoOperations, TaskService taskService) {
        this.mongoOperations = mongoOperations;
        this.taskService = taskService;
    }

    public List<Users> getAllUsers() {
        return mongoOperations.findAll(Users.class);
    }

    public Users getUserById(String id) {
        return mongoOperations.findById(id, Users.class);
    }

    public Optional<Users> findByUsername(String name) {
        Users user = mongoOperations.findOne(Query.query(Criteria.where("name").is(name)), Users.class);
        return Optional.ofNullable(user);
    }

    public Users addUser(Users users) {
        return mongoOperations.save(users);
    }

    // ---------------------------------------------------------------------------------------------

    public List<Tasks> getAllTasks() {
        return mongoOperations.findAll(Tasks.class);
    }

    public Tasks getTaskById(String id) {
        return mongoOperations.findById(id, Tasks.class);
    }

    public List<Tasks> getUsersTaskByName(String name) {
        Users user = findByUsername(name).orElse(null);
        if (user != null) {
            return user.getListOfTasks();
        } else {
            return Collections.emptyList();
        }
    }

    public Tasks addTask(String name, Tasks tasks) {
        Users user = findByUsername(name).orElse(null);
        tasks.setIsActive(false);
        tasks.setId(mongoOperations.insert(tasks).getId());
        tasks.setUserId(user.getUserId());
        mongoOperations.save(tasks);

        user.getListOfTasks().add(tasks);
        mongoOperations.save(user);
        return tasks;
    }

    public Tasks removeTask(String name, String id) {
        Users user = findByUsername(name).orElse(null);
        mongoOperations.remove(getTaskById(id));
        user.getListOfTasks().removeIf(e -> e.getId().equals(id));
        mongoOperations.save(user);

        return null;
    }

    public Tasks startTask(String id, String name) {
        Users user = findByUsername(name).orElse(null);
        if (user != null) {
            user.getListOfTasks().stream()
                    .filter(task -> task.getId().equals(id))
                    .forEach(task -> {
                        System.out.println(task);
                        task.setIsActive(true);
                        mongoOperations.save(user);
                    });

            Tasks task = getTaskById(id);

            LocalDateTime now = LocalDateTime.now();

            task.setIsActive(true);
            task.setStartDate(now);

            return mongoOperations.save(task);
        }
        return null;
    }

    public Tasks stopTask(String id, String name) {
        Users user = findByUsername(name).orElse(null);
        if (user != null) {
            user.getListOfTasks().stream()
                    .filter(task -> task.getId().equals(id))
                    .forEach(task -> {
                        task.setIsActive(false);
                    });

            Tasks task = getTaskById(id);
            LocalDateTime now = LocalDateTime.now();

            task.setIsActive(false);
            task.setEndDate(now);
            task.setTotalTime(task.getTotalTime() + countHours(task.getStartDate(), task.getEndDate()));
            mongoOperations.save(user);
            return mongoOperations.save(task);
        }

        return null;
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
