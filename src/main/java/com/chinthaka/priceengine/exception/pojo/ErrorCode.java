package com.chinthaka.priceengine.exception.pojo;

/**
 * @author Chinthaka Jayarathne
 * @E-mail jgcjayarathne@gmail.com
 * @Telephone +94761743256
 * @created on 2021-06-22
*/
public enum ErrorCode {

    PENG001("PENG001"), //Empty database
    PENG002("PENG002"), //Product not found for getting price
    PENG003("PENG003"), //Product not found for calculating  price list

    PENGXXX("PENGXXX"); // !!!Unforeseen exception !!!


    private final String value;

    ErrorCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
