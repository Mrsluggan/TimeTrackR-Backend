package com.TimeTrackR.TimeTrackR.Tasks;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskMapping {

    private TaskService taskService;

    @GetMapping("task/getAll")
    public List<Tasks> getAllTasks() {

        return null;
    }

}
