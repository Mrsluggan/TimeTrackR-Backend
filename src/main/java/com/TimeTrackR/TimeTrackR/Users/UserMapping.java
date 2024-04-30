package com.TimeTrackR.TimeTrackR.Users;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserMapping {

    private UserService userService;

    @GetMapping("users")
    public List<Users> getAllUsers() {
        return null;
    }

}
