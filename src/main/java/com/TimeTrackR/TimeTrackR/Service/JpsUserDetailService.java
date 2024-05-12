package com.TimeTrackR.TimeTrackR.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.TimeTrackR.TimeTrackR.Model.Users.Model.UserDto;
import com.TimeTrackR.TimeTrackR.Model.Users.Service.UserService;

@Service
public class JpsUserDetailService implements UserDetailsService {

    private final UserService userService;

    public JpsUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userService.findByUsername(username)
                .map(UserDto::new)
                .orElseThrow(() -> new UsernameNotFoundException("Error"));
    }

}