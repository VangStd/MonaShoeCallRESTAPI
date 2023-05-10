/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.controller.admin;

import com.vang.monashoeweb.dto.AccountConfig;
import com.vang.monashoeweb.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author kyqua
 */
@Controller
@RequestMapping("/administrator/employee/staff/")
public class AdminEmployeeController {

    @Autowired
    private AccountService accountService;

    @GetMapping("index-staff")
    public String indexEmployee(Model model) {
        return accountService.indexEmployee(model);
    }

    @GetMapping("create-staff")
    public String createCustomer(Model model) {
        return accountService.createEmployee(model);
    }

    @PostMapping("create-staff")
    public String createCustomer(@ModelAttribute("employee") @Valid AccountConfig accountConfig, BindingResult br, Model model) {
        return accountService.createEmployee(accountConfig, br, model);
    }
}
