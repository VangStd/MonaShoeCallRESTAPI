/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vang.monashoeweb.dto.Suppliers;
import com.vang.monashoeweb.service.SupplierService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author kyqua
 */
@Service
public class SupplierServiceimpl implements SupplierService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String indexSupplier(Model model) {
        try {
            String url = "http://localhost:1602/api/administrator/employee/supplier/";
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            List<Suppliers> listSupplier = objectMapper.readValue(response.getBody(), new TypeReference<List<Suppliers>>() {
            });
            model.addAttribute("listSup", listSupplier);
        } catch (Exception e) {
        }

        return "admin/supplier/index";
    }

    @Override
    public String createSupplier(Model model) {
        Suppliers suppliers = new Suppliers();
        model.addAttribute("supplier", suppliers);
        return "admin/supplier/create";
    }

    @Override
    public String createSupplier(Suppliers suppliers, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("supplier", suppliers);
            return "admin/supplier/create";
        }
        String url = "http://localhost:1602/api/administrator/employee/supplier/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Suppliers> request = new HttpEntity<>(suppliers, headers);
        ResponseEntity<Suppliers> response = restTemplate.exchange(url, HttpMethod.POST, request, Suppliers.class);
        Suppliers sup = response.getBody();
        return "redirect:/administrator/employee/supplier/index-supplier";
    }

    @Override
    public String editSupplier(Model model, int id) {
        try {
            String url = "http://localhost:1602/api/administrator/employee/supplier/" + id + "/";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            Suppliers suppliers = objectMapper.readValue(response.getBody(), new TypeReference<Suppliers>() {
            });
            model.addAttribute("supplier", suppliers);
        } catch (Exception e) {
        }
        return "admin/supplier/edit";
    }

    @Override
    public String editSupplier(Suppliers suppliers, BindingResult br, Model model, int id) {
        if (br.hasErrors()) {
            model.addAttribute("supplier", suppliers);
            return "admin/supplier/create";
        }
        String url = "http://localhost:1602/api/administrator/employee/supplier/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Suppliers> request = new HttpEntity<>(suppliers, headers);
        ResponseEntity<Suppliers> response = restTemplate.exchange(url, HttpMethod.PUT, request, Suppliers.class);
        Suppliers sup = response.getBody();
        return "redirect:/administrator/employee/supplier/index-supplier";
    }

    @Override
    public String deleteSupplier(int id) {
        String url = "http://localhost:1602/api/administrator/employee/supplier/" + id + "/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
        return "redirect:/administrator/employee/supplier/index-supplier";
    }

}
