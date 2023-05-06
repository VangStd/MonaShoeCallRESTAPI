/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vang.monashoeweb.service;

import com.vang.monashoeweb.dto.AccountConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 *
 * @author kyqua
 */
public interface AccountService {

    String indexCustomer(Model model);

    String createCustomer(Model model);

    @Transactional
    String createCustomer(AccountConfig accountConfig, BindingResult br, Model model);

    String editCustomer(Model model, int id);

    @Transactional
    String editCustomer(AccountConfig accountConfig, BindingResult br, Model model,HttpServletRequest request, int id);

    String deleteAccount(int id);

}
