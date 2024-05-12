package com.TimeTrackR.TimeTrackR.Model.Users.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TimeTrackR.TimeTrackR.Model.Tasks.TaskService;
import com.TimeTrackR.TimeTrackR.Model.Tasks.Tasks;
import com.TimeTrackR.TimeTrackR.Model.Users.Model.Users;
import com.TimeTrackR.TimeTrackR.Model.Users.Service.UserService;

@RestController
public class UserController {

    @Autowired
    private PasswordEncoder bcryptEncoder;

    private final TaskService taskService;

    private UserService userService;

    public UserController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("task/getTasks")
    public List<Tasks> getCurrentUser(Authentication authentication) {
        String username = authentication.getName();

        return userService.getUsersTaskByName(username);
    }

    @GetMapping("/check")
    public ResponseEntity<?> CheckIfAuthenticated(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Map<String, Object> response = new HashMap<>();
            response.put("authenticated", true);
            response.put("username", username);
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("authenticated", false);
            return ResponseEntity.ok(response);
        }

    }

    @GetMapping("users")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("users/add/newuser")
    public Users addUser(@RequestBody Users users) {
        String newPassword = bcryptEncoder.encode(users.getPassword());
        users.setPassword(newPassword);
        return userService.addUser(users);
    }

    @PostMapping("task/add")
    public Tasks addTask(Authentication authentication, @RequestBody Tasks tasks) {
        String username = authentication.getName();

        return userService.addTask(username, tasks);
    }

    @DeleteMapping("task/remove")
    public Tasks removeTask(Authentication authentication, @RequestParam(value = "id") String id) {
        String username = authentication.getName();

        return userService.removeTask(username, id);
    }

    @PostMapping("task/start")
    public Tasks startTask(Authentication authentication, @RequestParam(value = "id") String id) {
        String username = authentication.getName();

        return userService.startTask(id, username);
    }

    @PostMapping("task/stop")
    public Tasks stopTask(Authentication authentication, @RequestParam(value = "id") String id) {
        String username = authentication.getName();

        return userService.stopTask(id, username);
    }

}
