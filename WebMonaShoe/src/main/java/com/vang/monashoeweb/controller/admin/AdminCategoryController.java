/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.controller.admin;

import com.vang.monashoeweb.dto.Categories;
import com.vang.monashoeweb.service.CategoryService;
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
@RequestMapping("/administrator/employee/category/")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("index-category")
    public String indexCategory(Model model) {
        return categoryService.indexCategory(model);
    }

    @GetMapping("create-category")
    public String createCategory(Model model) {
        return categoryService.createCategory(model);
    }

    @PostMapping("create-category")
    public String createCategory(@ModelAttribute("category") @Valid Categories categories, BindingResult br, Model model) {
        return categoryService.createCategory(categories, br, model);
    }

    @GetMapping("edit-category/{id}")
    public String editCategory(Model model, @PathVariable("id") int id) {
        return categoryService.editCategory(model, id);
    }

    @PostMapping("edit-category/{id}")
    public String createCategory(@ModelAttribute("category") @Valid Categories categories, BindingResult br, Model model, @PathVariable("id") int id) {
        return categoryService.editCategory(categories, br, model, id);
    }

    @PostMapping("delete-category")
    public String deleteCategory(@RequestParam("categoryID") int id) {
        return categoryService.deleteCategory(id);
    }
}
