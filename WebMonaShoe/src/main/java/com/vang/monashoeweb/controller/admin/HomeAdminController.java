/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.vang.monashoeweb.dto.Brands;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author kyqua
 */
@Controller
@RequestMapping("/administrator/")
public class HomeAdminController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("home")
    public String homeAdmin() {
        return "admin/index";
    }

    @GetMapping("table")
    public String homeTable() throws JsonProcessingException {
        getBrand();
        return "admin/table";
    }

    @GetMapping("home-brand")
    public String homeBrand(Model model) throws JsonProcessingException {
        String url = "http://localhost:1602/api/brand/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Brands> myObjects = objectMapper.readValue(response.getBody(), new TypeReference<List<Brands>>() {
        });
        model.addAttribute("listBrand", myObjects);
        return "admin/brand/index";
    }

    public String getBrand() throws JsonProcessingException {
        String url = "http://localhost:1602/api/brand/1/";
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        Brands brands = objectMapper.readValue(response.getBody(), new TypeReference<Brands>() {
        });
        System.out.println("--" + brands.getBrandName());
        return "admin/table";
    }

    @GetMapping("create-brand")
    public String createBrand(Model model) {
        Brands brands = new Brands();
        model.addAttribute("brand", brands);
        return "admin/brand/create";
    }

    @PostMapping("create-brand")
    public String createBrand(@ModelAttribute("brand") @Valid Brands brands, BindingResult br, Model model) throws JsonProcessingException {
        if (br.hasErrors()) {
            model.addAttribute("brand", brands);
            return "admin/brand/create";
        }
        String url = "http://localhost:1602/api/brand/";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Brands> request = new HttpEntity<>(brands, headers);
        ResponseEntity<Brands> response = restTemplate.exchange(url, HttpMethod.POST, request, Brands.class);
        Brands brand = response.getBody();
        return "redirect:/administrator/home-brand";
    }

    @GetMapping("edit-brand/{id}")
    public String editBrand(Model model, @PathVariable("id") int id) throws JsonProcessingException {
        String url = "http://localhost:1602/api/brand/" + id + "/";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        Brands brands = objectMapper.readValue(response.getBody(), new TypeReference<Brands>() {
        });
        model.addAttribute("brand", brands);
        return "admin/brand/edit";
    }

    @PostMapping("edit-brand/{id}")
    public String editbrand(@ModelAttribute("brand") @Valid Brands brands, BindingResult br, Model model, @PathVariable("id") int id) {
        String url = "http://localhost:1602/api/brand/";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Brands> request = new HttpEntity<>(brands, headers);
        ResponseEntity<Brands> response = restTemplate.exchange(url, HttpMethod.PUT, request, Brands.class);
        Brands brand = response.getBody();
        return "redirect:/administrator/home-brand";
    }

    @GetMapping("delete-brand/{id}")
    public String removeBrand(@PathVariable("id") int id) {
        String url = "http://localhost:1602/api/brand/" + id + "/";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Integer> request = new HttpEntity<>(id, headers);
        ResponseEntity<Brands> response = restTemplate.exchange(url, HttpMethod.DELETE, request, Brands.class);
        Brands brands = response.getBody();
        return "redirect:/administrator/home-brand";
    }
}
