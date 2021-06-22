package com.chinthaka.priceengine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Chinthaka Jayarathne
 * @E-mail jgcjayarathne@gmail.com
 * @Telephone +94761743256
 * @created on 2021-06-22
 */
@Data
@AllArgsConstructor
public class PriceDto {
    private long quantity;
    private String price;
}
