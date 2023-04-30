/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author kyqua
 */
@Data
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    protected OrderDetailsPK orderDetailsPK;
    private int quantity;
    private BigDecimal unitPrice;
    private Orders orders;
    private Products products;

}
