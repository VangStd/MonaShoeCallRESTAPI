/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 *
 * @author kyqua
 */
@Data
public class Categories implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer categoryID;
    private String categoryName;
    private String description;
    private List<Products> productsList;

    
}
