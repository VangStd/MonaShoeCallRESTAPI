/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author kyqua
 */
@Data
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer orderID;
    private Date orderDate;
    private int totalAmount;
    private BigDecimal totalPrice;
    private String paymentMethod;
    private String note;
    private int status;
    private Accounts accounts;
    private Accounts accounts1;
    private List<OrderDetails> orderDetailsList;

}
