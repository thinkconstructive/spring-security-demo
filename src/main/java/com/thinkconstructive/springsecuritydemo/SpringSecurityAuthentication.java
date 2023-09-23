package com.thinkconstructive.springsecuritydemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityAuthentication {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.authorizeRequests(
                (req) -> req
                        .antMatchers("/demo", "/demo/one").permitAll()
                        .anyRequest().authenticated()
        ).formLogin();

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager()
    {
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        UserDetails userOne = users
                .username("testUserOne")
                .password("passwordOne")
                .roles("USER")
                .build();
        UserDetails userTwo = users
                .username("testUserTwo")
                .password("passwordTwo")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(userOne, userTwo);
    }
}
