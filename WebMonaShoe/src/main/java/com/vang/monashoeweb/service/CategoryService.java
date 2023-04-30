/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vang.monashoeweb.service;

import com.vang.monashoeweb.dto.Categories;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 *
 * @author kyqua
 */
public interface CategoryService {

    String indexCategory(Model model);

    String createCategory(Model model);

    String createCategory(Categories categories, BindingResult br, Model model);

    String editCategory(Model model, int id);

    String editCategory(Categories categories, BindingResult br, Model model, int id);

    String deleteCategory(int id);

}
