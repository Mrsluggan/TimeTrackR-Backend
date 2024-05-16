package com.TimeTrackR.TimeTrackR.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.TimeTrackR.TimeTrackR.Service.JpsUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JpsUserDetailService jpsUserDetailService;

    public SecurityConfig(JpsUserDetailService jpsUserDetailService) {
        this.jpsUserDetailService = jpsUserDetailService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((request) -> request
                .anyRequest().permitAll())

                .userDetailsService(jpsUserDetailService)

                .formLogin(formLogin -> formLogin
                        .defaultSuccessUrl("https://lobster-app-2ifzk.ondigitalocean.app/") // Redirect to this address after login
                        .permitAll()) // Allow everyone to access the login page
                .logout(logout -> logout
                        .logoutUrl("https://walrus-app-fc7zi.ondigitalocean.app/logout")
                        .logoutSuccessUrl("https://lobster-app-2ifzk.ondigitalocean.app/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"));



        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
