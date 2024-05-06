package com.TimeTrackR.TimeTrackR.Tasks;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class TaskMapping {

    private final TaskService taskService;

    public TaskMapping(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("tasks/gettask/{id}")
    public Tasks getTaskById(@PathVariable("id") String id) {

        return taskService.getTaskById(id);
    }

    @GetMapping("tasks/gettasks")
    public List<Tasks> getAllTasks() {

        return taskService.getAllTasks();
    }

    @PostMapping("tasks/newtask")
    public Tasks newTask(@RequestBody Tasks tasks) {
        tasks.setIsActive(false);
        return taskService.addTask(tasks);
    }

    @DeleteMapping("tasks/remove")
    public void removeTask(String id) {

    }

    @PostMapping("tasks/start/{id}")
    public Tasks startTask(@PathVariable("id") String id) {
        return taskService.startTask(id);
    }

    @PostMapping("tasks/stop/{id}")
    public Tasks stopTask(@PathVariable("id") String id) {
        return taskService.stopTask(id);
    }
}
