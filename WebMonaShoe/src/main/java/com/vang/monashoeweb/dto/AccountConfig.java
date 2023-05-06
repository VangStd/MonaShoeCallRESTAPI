package com.vang.monashoeweb.dto;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import lombok.Data;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author kyqua
 */
@Data
public class AccountConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotBlank(message = "Not Null")
    private String fullName;
    private String address;
    private String phone;
    private Accounts accountID;
}
