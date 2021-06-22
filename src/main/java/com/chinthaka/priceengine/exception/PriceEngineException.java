package com.chinthaka.priceengine.exception;

import com.chinthaka.priceengine.exception.pojo.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Chinthaka Jayarathne
 * @E-mail jgcjayarathne@gmail.com
 * @Telephone +94761743256
 * @created on 2021-06-22
*/

@AllArgsConstructor
@Getter
public class PriceEngineException extends Exception {

    private final HttpStatus statusCode;
    private final ErrorCode errorCode;
    private final String errorMessage;
    private final String logMessage;
}
