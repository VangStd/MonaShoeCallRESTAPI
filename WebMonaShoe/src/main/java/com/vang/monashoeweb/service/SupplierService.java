/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vang.monashoeweb.service;

import com.vang.monashoeweb.dto.Suppliers;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 *
 * @author kyqua
 */
public interface SupplierService {

    String indexSupplier(Model model);

    String createSupplier(Model model);

    String createSupplier(Suppliers suppliers, BindingResult br, Model model);

    String editSupplier(Model model, int id);

    String editSupplier(Suppliers suppliers, BindingResult br, Model model, int id);

    String deleteSupplier(int id);
}
