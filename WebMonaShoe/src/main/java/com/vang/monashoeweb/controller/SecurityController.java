/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author kyqua
 */
@Controller
public class SecurityController {

    @RequestMapping("/login")
    public String login() {
        return "client/login";
    }

    @RequestMapping("/login-success")
    public String loginSuccess(Principal principal) {
        return "redirect:/home";
    }
}
