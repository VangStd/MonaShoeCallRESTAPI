/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.controller.admin;

import com.vang.monashoeweb.dto.AccountConfig;
import com.vang.monashoeweb.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author kyqua
 */
@Controller
@RequestMapping("/administrator/employee/customer/")
public class AdminCustomerController {

    @Autowired
    private AccountService accountService;

    @GetMapping("index-customer")
    public String indexCustomer(Model model) {
        return accountService.indexCustomer(model);
    }

    @GetMapping("create-customer")
    public String createCustomer(Model model) {
        return accountService.createCustomer(model);
    }

    @PostMapping("create-customer")
    public String createCustomer(@ModelAttribute("customer") @Valid AccountConfig accountConfig, BindingResult br, Model model) {
        return accountService.createCustomer(accountConfig, br, model);
    }

    @GetMapping("edit-customer/{id}")
    public String editCustomer(Model model, @PathVariable("id") int id) {
        return accountService.editCustomer(model, id);
    }

    @PostMapping("edit-customer/{id}")
    public String editCustomer(@ModelAttribute("customer") @Valid AccountConfig accountConfig, BindingResult br, Model model, HttpServletRequest request, @PathVariable("id") int id) {
        return accountService.editCustomer(accountConfig, br, model, request, id);
    }

    @PostMapping("delete-customer")
    public String deleteCustomer(@RequestParam("customerID") int id) {
        return accountService.deleteAccount(id);
    }

}
