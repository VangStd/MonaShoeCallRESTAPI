/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.controller.admin;

import com.vang.monashoeweb.dto.Brands;
import com.vang.monashoeweb.service.BrandService;
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

@Controller
@RequestMapping("/administrator/employee/brand/")
public class AdminBrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("index-brand")
    public String indexBrand(Model model) {
        return brandService.indexBrand(model);
    }

    @GetMapping("create-brand")
    public String createBrand(Model model) {
        return brandService.createBrand(model);
    }

    @PostMapping("create-brand")
    public String createBrand(@ModelAttribute("brand") @Valid Brands brands, BindingResult br, Model model) {
        return brandService.createBrand(brands, br, model);
    }

    @GetMapping("edit-brand/{id}")
    public String editBrand(Model model, @PathVariable("id") int id) {
        return brandService.editBrand(model, id);
    }

    @PostMapping("edit-brand/{id}")
    public String editBrand(@ModelAttribute("brand") @Valid Brands brands, BindingResult br, Model model, @PathVariable("id") int id) {
        return brandService.editBrand(brands, br, model, id);
    }

    @PostMapping("delete-brand")
    public String deleteBrand(@RequestParam("brandID") int id) {
        return brandService.deleteBrand(id);
    }
}
