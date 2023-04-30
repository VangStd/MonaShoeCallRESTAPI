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
public class Suppliers implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer supplierID;
    private String companyName;
    private String phoneNumber;
    private String address;
    private String email;
    private String website;
    private int status;
    private List<Products> productsList;

}
