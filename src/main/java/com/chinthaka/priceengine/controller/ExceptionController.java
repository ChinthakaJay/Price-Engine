package com.chinthaka.priceengine.controller;

import com.chinthaka.priceengine.exception.PriceEngineException;
import com.chinthaka.priceengine.exception.pojo.ErrorCode;
import com.chinthaka.priceengine.exception.pojo.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Chinthaka Jayarathne
 * @E-mail jgcjayarathne@gmail.com
 * @Telephone +94761743256
 * @created on 2021-06-22
*/

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(PriceEngineException.class)
    public ResponseEntity<ErrorResponse> handlePriceEngineExceptions(PriceEngineException ex) {
        log.error("Price engine exception occurred,  errorCode : {}   {}", ex.getErrorCode(), ex);
        return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode().getValue(), ex.getErrorMessage()),
                ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralExceptions(Exception ex) {
        log.error("Unforeseen exception occurred, {}", ex);
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.PENGXXX.getValue(),
                "Unforeseen exception occurred. Please try again"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
