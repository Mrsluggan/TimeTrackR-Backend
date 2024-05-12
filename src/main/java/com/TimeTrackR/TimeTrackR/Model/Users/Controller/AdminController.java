package com.TimeTrackR.TimeTrackR.Model.Users.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TimeTrackR.TimeTrackR.Model.Tasks.TaskService;
import com.TimeTrackR.TimeTrackR.Model.Tasks.Tasks;
import com.TimeTrackR.TimeTrackR.Model.Users.Service.UserService;
import com.TimeTrackR.TimeTrackR.Model.Users.Model.Users;

@RestController
public class AdminController {

    private final TaskService taskService;

    private final UserService userService;

    public AdminController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("admin/getAllUsers")
    public List<Users> getUsers() {
        return userService.getAllUsers();
    }


}
