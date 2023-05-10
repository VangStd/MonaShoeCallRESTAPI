/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vang.monashoeweb.defaultvariable.ConstVariable;
import com.vang.monashoeweb.dto.AccountConfig;
import com.vang.monashoeweb.dto.Accounts;
import com.vang.monashoeweb.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author kyqua
 */
@Service
public class SecurityServiceimpl implements SecurityService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HttpHeaders headers;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public String register(Model model) {
        AccountConfig acc = new AccountConfig();
        model.addAttribute("customer", acc);
        return "client/register";
    }

    @Override
    public String register(AccountConfig accountConfig, BindingResult br, Model model) {
        int statusCheckExist = 0;
        if (br.hasErrors()) {
            model.addAttribute("account", accountConfig);
            return "admin/customer/create";
        }
        if (!accountConfig.getAccountID().getPassword().equals(accountConfig.getAccountID().getConfirmNewPass())) {
            model.addAttribute("errNewPass", "Password confirm is incorrect");
            model.addAttribute("account", accountConfig);
            return "admin/customer/create";
        }
        long checkUsername = checkUsernameExist(accountConfig.getAccountID().getUsername());
        long checkEmail = checkEmailExist(accountConfig.getAccountID().getEmail());
        if (checkUsername > 0) {
            model.addAttribute("errUsername", "Username exist !");
            statusCheckExist++;
        }
        if (checkEmail > 0) {
            model.addAttribute("errEmail", "Email exist !");
            statusCheckExist++;
        }
        if (statusCheckExist > 0) {
            model.addAttribute("account", accountConfig);
            return "admin/customer/create";
        }
        try {
            Accounts account = accountConfig.getAccountID();
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            account.setRoles(ConstVariable.ROLE_CUSTOMER);
            account.setStatus(ConstVariable.STATUS_ENABLE);
            account.setDateCreate(ConstVariable.TODAY);
            String url = "http://localhost:1602/api/administrator/employee/account/";
            HttpEntity<Accounts> request = new HttpEntity<>(account, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            Accounts acc = objectMapper.readValue(response.getBody(), new TypeReference<Accounts>() {
            });
            accountConfig.setAccountID(acc);
            String url1 = "http://localhost:1602/api/administrator/employee/account-config/";
            HttpEntity<AccountConfig> request1 = new HttpEntity<>(accountConfig, headers);
            ResponseEntity<String> response1 = restTemplate.exchange(url1, HttpMethod.POST, request1, String.class);
        } catch (Exception e) {
        }
        return "redirect:/login";
    }

    private long checkUsernameExist(String username) {
        long valueReturn = 0;
        try {
            String url = "http://localhost:1602/api/administrator/employee/account/username/" + username + "/";
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<Long> response = restTemplate.exchange(url, HttpMethod.GET, request, Long.class);
            valueReturn = response.getBody();
        } catch (Exception e) {
        }
        return valueReturn;
    }

    private long checkEmailExist(String email) {
        long valueReturn = 0;
        try {
            String url = "http://localhost:1602/api/administrator/employee/account/email/" + email + "/";
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<Long> response = restTemplate.exchange(url, HttpMethod.GET, request, Long.class);
            valueReturn = response.getBody();
        } catch (Exception e) {
        }
        return valueReturn;
    }
}
