/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author kyqua
 */
@Data
public class Accounts implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer accountID;
    private String username;
    private String password;
    private String email;
    private String roles;
    private Date dateCreate;
    private String status;
    private List<Orders> ordersList;
    private List<AccountConfig> accountConfigList;
}
