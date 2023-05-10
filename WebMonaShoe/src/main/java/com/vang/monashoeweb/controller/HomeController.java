/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author kyqua
 */
@Controller
public class HomeController {

    //@Secured(value = "ROLE_ADMIN")
    @GetMapping("/home")
    public String index() {
        return "client/index";
    }

    @Secured(value = "ROLE_ADMIN")
    @GetMapping("/about")
    public String about() {
        return "client/about";
    }

    @GetMapping("/shop")
    public String shop() {
        return "client/shop";
    }

    @GetMapping("/new")
    public String news() {
        return "client/news";
    }

    @GetMapping("/new-detail")
    public String newdetail() {
        return "client/newdetail";
    }

    @GetMapping("/contact")
    public String contact() {
        return "client/contact";
    }

    @GetMapping("/map")
    public String map() {
        return "client/map";
    }


}
