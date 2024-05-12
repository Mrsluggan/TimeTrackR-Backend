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
                .requestMatchers("/TestUser").authenticated()
                .anyRequest().permitAll())

                .userDetailsService(jpsUserDetailService)

                .formLogin(formLogin -> formLogin
                        .defaultSuccessUrl("http://localhost:5173/") // Redirect to this address after login
                        .permitAll()) // Allow everyone to access the login page
                .logout(logout -> logout
                        .logoutUrl("http://localhost:5173/")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"));

        // CORS configuration
        http.cors(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
