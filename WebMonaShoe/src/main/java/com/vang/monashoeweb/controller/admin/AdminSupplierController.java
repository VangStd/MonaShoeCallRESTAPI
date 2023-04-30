/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.controller.admin;

import com.vang.monashoeweb.dto.Suppliers;
import com.vang.monashoeweb.service.SupplierService;
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
@RequestMapping("/administrator/employee/supplier/")
public class AdminSupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("index-supplier")
    public String indexSupplier(Model model) {
        return supplierService.indexSupplier(model);
    }

    @GetMapping("create-supplier")
    public String createSupplier(Model model) {
        return supplierService.createSupplier(model);
    }

    @PostMapping("create-supplier")
    public String createSupplier(@ModelAttribute("supplier") @Valid Suppliers suppliers, BindingResult br, Model model) {
        return supplierService.createSupplier(suppliers, br, model);
    }

    @GetMapping("edit-supplier/{id}")
    public String editSupplier(Model model, @PathVariable("id") int id) {
        return supplierService.editSupplier(model, id);
    }

    @PostMapping("edit-supplier/{id}")
    public String editSupplier(@ModelAttribute("supplier") @Valid Suppliers suppliers, BindingResult br, Model model, @PathVariable("id") int id) {
        return supplierService.editSupplier(suppliers, br, model, id);
    }

    @PostMapping("delete-supplier")
    public String deleteSupplier(@RequestParam("supID") int id) {
        return supplierService.deleteSupplier(id);
    }
}
