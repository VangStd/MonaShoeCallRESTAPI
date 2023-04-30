/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vang.monashoeweb.dto;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.Data;

/**
 *
 * @author kyqua
 */
@Embeddable
@Data
public class FeedbackDetailsPK implements Serializable {

    private int feedbackID;
    private int productID;

}
