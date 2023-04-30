/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vang.monashoeweb.service;

import com.vang.monashoeweb.dto.Products;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 *
 * @author kyqua
 */
public interface ProductService {

    String indexProduct(Model model);

    String createProduct(Model model);

    String createProduct(Products products, BindingResult br, Model model);

    String editProduct(Model model, int id);

    String editProduct(Products products, BindingResult br, Model model, int id);

    String deleteProduct(int id);
}
