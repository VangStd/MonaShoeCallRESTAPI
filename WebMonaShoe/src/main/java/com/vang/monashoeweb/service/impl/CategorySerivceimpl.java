/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vang.monashoeweb.dto.Categories;
import com.vang.monashoeweb.service.CategoryService;
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
public class CategorySerivceimpl implements CategoryService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String indexCategory(Model model) {
        try {
            String url = "http://localhost:1602/api/administrator/employee/category/";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            List<Categories> listCate = objectMapper.readValue(response.getBody(), new TypeReference<List<Categories>>() {
            });
            model.addAttribute("listCate", listCate);
        } catch (Exception e) {
        }
        return "admin/category/index";
    }

    @Override
    public String createCategory(Model model) {
        Categories categories = new Categories();
        model.addAttribute("category", categories);
        return "admin/category/create";
    }

    @Override
    public String createCategory(Categories categories, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("category", categories);
            return "admin/category/create";
        }
        String url = "http://localhost:1602/api/administrator/employee/category/";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Categories> request = new HttpEntity<>(categories, headers);
        ResponseEntity<Categories> response = restTemplate.exchange(url, HttpMethod.POST, request, Categories.class);
        Categories cate = response.getBody();
        return "redirect:/administrator/employee/category/index-category";
    }

    @Override
    public String editCategory(Model model, int id) {
        try {
            String url = "http://localhost:1602/api/administrator/employee/category/" + id + "/";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            Categories categories = objectMapper.readValue(response.getBody(), new TypeReference<Categories>() {
            });
            model.addAttribute("category", categories);
        } catch (Exception e) {
        }
        return "admin/category/edit";
    }

    @Override
    public String editCategory(Categories categories, BindingResult br, Model model, int id) {
        if (br.hasErrors()) {
            model.addAttribute("categories", categories);
            return "admin/category/edit";
        }
        String url = "http://localhost:1602/api/administrator/employee/category/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Categories> request = new HttpEntity<>(categories);
        ResponseEntity<Categories> response = restTemplate.exchange(url, HttpMethod.PUT, request, Categories.class);
        return "redirect:/administrator/employee/category/index-category";
    }

    @Override
    public String deleteCategory(int id) {
        try {
            String url = "http://localhost:1602/api/administrator/employee/category/" + id + "/";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<Categories> response = restTemplate.exchange(url, HttpMethod.DELETE, request, Categories.class);
        } catch (Exception e) {
        }

        return "redirect:/administrator/employee/category/index-category";
    }

}
