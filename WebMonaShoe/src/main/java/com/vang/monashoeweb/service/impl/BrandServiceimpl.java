/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vang.monashoeweb.dto.Accounts;
import com.vang.monashoeweb.dto.Brands;
import com.vang.monashoeweb.dto.LoginDTO;
import com.vang.monashoeweb.service.BrandService;
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
public class BrandServiceimpl implements BrandService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String indexBrand(Model model) {
        try {
            String url = "http://localhost:1602/api/administrator/employee/brand/";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            List<Brands> myObjects = objectMapper.readValue(response.getBody(), new TypeReference<List<Brands>>() {
            });
            model.addAttribute("listBrand", myObjects);
            Accounts account = new Accounts();
            account.setUsername("vangadmin");
            account.setPassword("vangadmin");
            Accounts acc = login(account);
            System.out.println("-->" + acc.getUsername());
        } catch (Exception e) {
        }
        return "admin/brand/index";
    }

    private Accounts login(Accounts acc) {
        Accounts accounts = new Accounts();
        try {
            String url = "http://localhost:1602/api/login/";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Accounts> request = new HttpEntity<>(acc, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            accounts = objectMapper.readValue(response.getBody(), new TypeReference<Accounts>() {
            }
            );
        } catch (Exception e) {
        }
        return accounts;
    }

    @Override
    public String createBrand(Model model) {
        Brands brands = new Brands();
        model.addAttribute("brand", brands);
        return "admin/brand/create";
    }

    @Override
    public String createBrand(Brands brands, BindingResult br, Model model) {
        try {
            if (br.hasErrors()) {
                model.addAttribute("brand", brands);
                return "admin/brand/create";
            }
            String url = "http://localhost:1602/api/administrator/employee/brand/";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Brands> request = new HttpEntity<>(brands, headers);
            ResponseEntity<Brands> response = restTemplate.exchange(url, HttpMethod.POST, request, Brands.class);
            Brands brand = response.getBody();
        } catch (Exception e) {
        }
        return "redirect:/administrator/employee/brand/index-brand";
    }

    @Override
    public String editBrand(Model model, int id) {
        try {
            String url = "http://localhost:1602/api/administrator/employee/brand/" + id + "/";
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            Brands brands = objectMapper.readValue(response.getBody(), new TypeReference<Brands>() {
            });
            model.addAttribute("brand", brands);
        } catch (Exception e) {
        }
        return "admin/brand/edit";
    }

    @Override
    public String editBrand(Brands brands, BindingResult br, Model model, int id) {
        try {
            String url = "http://localhost:1602/api/administrator/employee/brand/";
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Brands> request = new HttpEntity<>(brands, headers);
            ResponseEntity<Brands> response = restTemplate.exchange(url, HttpMethod.PUT, request, Brands.class);
            Brands brand = response.getBody();
        } catch (Exception e) {
        }
        return "redirect:/administrator/employee/brand/index-brand";
    }

    @Override
    public String deleteBrand(int brandID) {
        try {
            String url = "http://localhost:1602/api/administrator/employee/brand/" + brandID + "/";
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Integer> request = new HttpEntity<>(brandID, headers);
            ResponseEntity<Brands> response = restTemplate.exchange(url, HttpMethod.DELETE, request, Brands.class);
            Brands brands = response.getBody();
        } catch (Exception e) {
        }
        return "redirect:/administrator/employee/brand/index-brand";
    }

}
