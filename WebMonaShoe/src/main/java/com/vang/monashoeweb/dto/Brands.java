/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.dto;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import lombok.Data;


@Data
public class Brands implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer brandID;
    @NotBlank(message = "Name is not empty")
    private String brandName;
    @NotBlank(message = "Description is not empty")
    private String description;
    @NotBlank(message = "Website is not empty")
    private String website;
    private List<Products> productsList;

}
