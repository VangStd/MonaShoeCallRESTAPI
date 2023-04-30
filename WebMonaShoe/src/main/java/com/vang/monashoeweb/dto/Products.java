/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Transient;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author kyqua
 */
@Data
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer productID;
    private String productName;
    private String description;
    private BigDecimal unitPrice;
    private String image;
    private int quantity;
    private int promotionStatus;
    private int status;
    private List<FeedbackDetails> feedbackDetailsList;
    private Brands brandID;
    private Categories categoryID;
    private Suppliers supplierID;
    private List<Promotions> promotionsList;
    private List<OrderDetails> orderDetailsList;
    private List<Feedbacks> feedbacksList;
    @JsonIgnore
    private MultipartFile img;
}
