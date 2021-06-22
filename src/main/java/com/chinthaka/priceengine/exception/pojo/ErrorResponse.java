package com.chinthaka.priceengine.exception.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Chinthaka Jayarathne
 * @E-mail jgcjayarathne@gmail.com
 * @Telephone +94761743256
 * @created on 2021-06-22
*/

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final String errorCode;
    private final String errorMessage;
}
