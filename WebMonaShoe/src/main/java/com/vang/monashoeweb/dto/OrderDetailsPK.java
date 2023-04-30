/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.dto;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author kyqua
 */
@Data
public class OrderDetailsPK implements Serializable {

    private int orderID;
    private int productID;

   
}
