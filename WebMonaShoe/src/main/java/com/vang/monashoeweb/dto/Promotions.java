/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author kyqua
 */
@Data
public class Promotions implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer promotionID;
    private String promotionName;
    private Date promotionDate;
    private int discount;
    private BigDecimal amountApplyPromotion;
    private String note;
    private Products products;

}
