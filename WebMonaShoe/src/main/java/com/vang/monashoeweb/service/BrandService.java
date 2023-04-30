/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vang.monashoeweb.service;

import com.vang.monashoeweb.dto.Brands;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 *
 * @author kyqua
 */
public interface BrandService {

    String indexBrand(Model model);

    String createBrand(Model model);

    String createBrand(Brands brands, BindingResult br, Model model);

    String editBrand(Model model, int id);

    String editBrand(Brands brands, BindingResult br, Model model, int id);

    String deleteBrand(int brandID);
}
