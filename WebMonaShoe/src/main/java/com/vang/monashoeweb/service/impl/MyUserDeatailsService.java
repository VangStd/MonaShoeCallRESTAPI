/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vang.monashoeweb.dto.Accounts;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author kyqua
 */
@Service
public class MyUserDeatailsService implements UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Accounts login = new Accounts();
        login.setUsername(username);
        Accounts accounts = login(login);
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(accounts.getRoles()));
        UserDetails userDetails = new User(accounts.getUsername(), accounts.getPassword(), list);
        return userDetails;
    }

    private Accounts login(Accounts acc) {
        Accounts accounts = new Accounts();
        try {
            String url = "http://localhost:1602/api/login/";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Accounts> request = new HttpEntity<>(acc, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            accounts = objectMapper.readValue(response.getBody(), new TypeReference<Accounts>() {
            }
            );
        } catch (Exception e) {
        }
        return accounts;
    }

}
