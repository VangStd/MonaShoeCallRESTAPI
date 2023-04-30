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
public class FeedbackDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    protected FeedbackDetailsPK feedbackDetailsPK;
    private String feedbackContent;
    private String reviews;
    private Feedbacks feedbacks;
    private Products products;

}
