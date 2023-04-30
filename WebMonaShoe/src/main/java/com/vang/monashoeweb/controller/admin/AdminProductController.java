/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.controller.admin;

import com.vang.monashoeweb.dto.Products;
import com.vang.monashoeweb.service.ProductService;
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
@RequestMapping("/administrator/employee/product/")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("index-product")
    public String indexProduct(Model model) {
        return productService.indexProduct(model);
    }

    @GetMapping("create-product")
    public String createProduct(Model model) {
        return productService.createProduct(model);
    }

    @PostMapping("create-product")
    public String createProduct(@ModelAttribute("product") @Valid Products products, BindingResult br, Model model) {
        return productService.createProduct(products, br, model);
    }

    @GetMapping("edit-product/{id}")
    public String editProduct(Model model, @PathVariable("id") int id) {
        return productService.editProduct(model, id);
    }

    @PostMapping("edit-product/{id}")
    public String editProduct(@ModelAttribute("product") @Valid Products products, BindingResult br, Model model, @PathVariable("id") int id) {
        return productService.editProduct(products, br, model, id);
    }

    @PostMapping("delete-product")
    public String deleteProduct(@RequestParam("productID") int id) {
        return productService.deleteProduct(id);
    }
}
