/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vang.monashoeweb.dto.Brands;
import com.vang.monashoeweb.dto.Categories;
import com.vang.monashoeweb.dto.Products;
import com.vang.monashoeweb.dto.Suppliers;
import com.vang.monashoeweb.service.ProductService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class ProductServiceimpl implements ProductService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String indexProduct(Model model) {
        try {
            String url = "http://localhost:1602/api/administrator/employee/product/";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            List<Products> listProduct = objectMapper.readValue(response.getBody(), new TypeReference<List<Products>>() {
            });
            model.addAttribute("listProduct", listProduct);
        } catch (Exception e) {
            System.out.println("==" + e);
        }
        return "admin/product/index";
    }

    @Override
    public String createProduct(Model model) {
        try {
            Products products = new Products();
            model.addAttribute("listSup", findAllSupplier());
            model.addAttribute("listCate", findAllCategory());
            model.addAttribute("listBrand", findAllBrand());
            model.addAttribute("product", products);
        } catch (Exception e) {
        }
        return "admin/product/create";
    }

    @Override
    public String createProduct(Products products, BindingResult br, Model model) {
        if (br.hasErrors()) {
            try {
                model.addAttribute("listSup", findAllSupplier());
                model.addAttribute("listCate", findAllCategory());
                model.addAttribute("listBrand", findAllBrand());
                model.addAttribute("product", products);
            } catch (Exception e) {
            }
            return "admin/product/create";
        }
        if (!products.getImg().isEmpty()) {
            try {
                String imgName = LocalDateTime.now().toString();
                cloudinary.uploader().destroy(imgName, ObjectUtils.asMap("resouce_type", "auto", "public_id", imgName));
                Map upload = cloudinary.uploader().upload(products.getImg().getBytes(), ObjectUtils.asMap("public_id", imgName));
                products.setImage((String) upload.get("secure_url"));
            } catch (Exception e) {
            }
        }
        Categories categories = findCategoryByID(products.getCategoryID().getCategoryID());
        Suppliers suppliers = findSupplierByID(products.getSupplierID().getSupplierID());
        Brands brands = findBrandByID(products.getBrandID().getBrandID());
        products.setBrandID(brands);
        products.setCategoryID(categories);
        products.setSupplierID(suppliers);
        products.setStatus(0);
        products.setPromotionStatus(0);
        String urlPost = "http://localhost:1602/api/administrator/employee/product/";
        HttpHeaders headersPost = new HttpHeaders();
        headersPost.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Products> requestPost = new HttpEntity<>(products, headersPost);
        ResponseEntity<Products> responsePost = restTemplate.exchange(urlPost, HttpMethod.POST, requestPost, Products.class);
        Products pro = responsePost.getBody();
        return "redirect:/administrator/employee/product/index-product";
    }

    @Override
    public String editProduct(Model model, int id) {
        Products products = findProductByID(id);
        model.addAttribute("listSup", findAllSupplier());
        model.addAttribute("listCate", findAllCategory());
        model.addAttribute("listBrand", findAllBrand());
        model.addAttribute("product", products);
        return "admin/product/edit";
    }

    @Override
    public String editProduct(Products products, BindingResult br, Model model, int id) {
        if (br.hasErrors()) {
            try {
                model.addAttribute("listSup", findAllSupplier());
                model.addAttribute("listCate", findAllCategory());
                model.addAttribute("listBrand", findAllBrand());
                model.addAttribute("product", products);
            } catch (Exception e) {
            }
            return "admin/product/edit";
        }
        if (!products.getImg().isEmpty()) {
            try {
                String imgName = LocalDateTime.now().toString();
                String oldName = products.getImage().substring(products.getImage().lastIndexOf("/")+1, products.getImage().lastIndexOf("."));
                System.out.println("--->" + oldName);
                cloudinary.uploader().destroy(imgName, ObjectUtils.asMap("resouce_type", "auto", "public_id", oldName));
                Map upload = cloudinary.uploader().upload(products.getImg().getBytes(), ObjectUtils.asMap("public_id", imgName));
                products.setImage((String) upload.get("secure_url"));
            } catch (Exception e) {
            }
        }
        Categories categories = findCategoryByID(products.getCategoryID().getCategoryID());
        Suppliers suppliers = findSupplierByID(products.getSupplierID().getSupplierID());
        Brands brands = findBrandByID(products.getBrandID().getBrandID());
        products.setBrandID(brands);
        products.setCategoryID(categories);
        products.setSupplierID(suppliers);
        String urlPost = "http://localhost:1602/api/administrator/employee/product/";
        HttpHeaders headersPost = new HttpHeaders();
        headersPost.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Products> requestPost = new HttpEntity<>(products, headersPost);
        ResponseEntity<Products> responsePost = restTemplate.exchange(urlPost, HttpMethod.PUT, requestPost, Products.class);
        Products pro = responsePost.getBody();
        return "redirect:/administrator/employee/product/index-product";
    }

    @Override
    public String deleteProduct(int id) {
        System.out.println("dooooooooooooo");
        String url = "http://localhost:1602/api/administrator/employee/product/" + id + "/";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
        return "redirect:/administrator/employee/product/index-product";
    }

    private Categories findCategoryByID(int id) {
        Categories categories = new Categories();
        try {
            String url = "http://localhost:1602/api/administrator/employee/category/" + id + "/";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            categories = objectMapper.readValue(response.getBody(), new TypeReference<Categories>() {
            });
        } catch (Exception e) {
            System.out.println("--cate" + e);
        }
        return categories;
    }

    private List<Categories> findAllCategory() {
        List<Categories> list = new ArrayList<>();
        try {
            String url = "http://localhost:1602/api/administrator/employee/category/";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            list = objectMapper.readValue(response.getBody(), new TypeReference<List<Categories>>() {
            });
        } catch (Exception e) {
        }
        return list;
    }

    private Suppliers findSupplierByID(int id) {
        Suppliers suppliers = new Suppliers();
        try {
            String url = "http://localhost:1602/api/administrator/employee/supplier/" + id + "/";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            suppliers = objectMapper.readValue(response.getBody(), new TypeReference<Suppliers>() {
            });
        } catch (Exception e) {
        }
        return suppliers;
    }

    private List<Suppliers> findAllSupplier() {
        List<Suppliers> list = new ArrayList<>();
        try {
            String url = "http://localhost:1602/api/administrator/employee/supplier/";
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            list = objectMapper.readValue(response.getBody(), new TypeReference<List<Suppliers>>() {
            });
        } catch (Exception e) {
        }

        return list;
    }

    private List<Brands> findAllBrand() {
        List<Brands> list = new ArrayList<>();
        try {
            String url = "http://localhost:1602/api/administrator/employee/brand/";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            list = objectMapper.readValue(response.getBody(), new TypeReference<List<Brands>>() {
            });
        } catch (Exception e) {
        }
        return list;
    }

    private Brands findBrandByID(int id) {
        Brands brands = new Brands();
        try {
            String url = "http://localhost:1602/api/administrator/employee/brand/" + id + "/";
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            brands = objectMapper.readValue(response.getBody(), new TypeReference<Brands>() {
            });
        } catch (Exception e) {
        }
        return brands;
    }

    private Products findProductByID(int id) {
        Products products = new Products();
        try {
            String url = "http://localhost:1602/api/administrator/employee/product/" + id + "/";
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            products = objectMapper.readValue(response.getBody(), new TypeReference<Products>() {
            });
        } catch (Exception e) {
        }
        return products;
    }

}
