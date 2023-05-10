/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.controller;

import com.vang.monashoeweb.service.SecurityService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author kyqua
 */
@Controller
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    @RequestMapping("/login1")
    public String login() {
        return "client/login";
    }

    @RequestMapping("/login-success")
    public String loginSuccess(Principal principal) {
        return "redirect:/home";
    }

    @GetMapping("/logout-success")
    public String logout() {
        return "redirect:/home";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return securityService.register(model);
    }
}
