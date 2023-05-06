/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.configuation;

import com.vang.monashoeweb.service.impl.MyUserDeatailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author kyqua
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Autowired
    private MyUserDeatailsService userDeatailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDeatailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeHttpRequests().requestMatchers("/home","/login","/logout").permitAll();
//        http.authorizeHttpRequests().requestMatchers("/employee/**").hasRole("ADMIN");
//        http.logout().logoutUrl("/logout").logoutSuccessUrl("/logout-success");
//        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
//        http.authorizeRequests().and().formLogin().loginPage("/login").loginProcessingUrl("/login-proccess").defaultSuccessUrl("/login-success")
//                .failureForwardUrl("/login?err=true").usernameParameter("username").passwordParameter("password");
        http.csrf()
                .disable()
                .authorizeHttpRequests().requestMatchers("/employee/**").hasRole("ADMIN")
                .and().authorizeHttpRequests().requestMatchers("/customer/**").hasRole("CUSTOMER")
                .and().authorizeHttpRequests().anyRequest().permitAll()
                .and().formLogin().loginPage("/login1").loginProcessingUrl("/login-proc").defaultSuccessUrl("/login-success")
                .failureForwardUrl("/login1?fail=true").usernameParameter("username").passwordParameter("password")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logout-success")
                .and().exceptionHandling().accessDeniedPage("/403");
        return http.build();
    }
}
