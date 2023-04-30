/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.configuation;

import com.vang.monashoeweb.service.impl.MyUserDeatailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author kyqua
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MyUserDeatailsService userDeatailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDeatailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().requestMatchers("/employee/**").access("hasRole('ROLE_EMPLOYEE')")
                .and().authorizeRequests().requestMatchers("/owner/**").access("hasRole('ROLE_OWNER')")
                .and().authorizeRequests().anyRequest().permitAll();
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        http.formLogin().loginPage("/login").successForwardUrl("/login-success").failureForwardUrl("/login?err=true")
                .loginProcessingUrl("/login-process").usernameParameter("username").passwordParameter("password");
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/logout-success");
        return http.build();
    }
}
