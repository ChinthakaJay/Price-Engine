package com.chinthaka.priceengine.controller;

import com.chinthaka.priceengine.dto.PriceDto;
import com.chinthaka.priceengine.dto.ProductDto;
import com.chinthaka.priceengine.exception.PriceEngineException;
import com.chinthaka.priceengine.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Chinthaka Jayarathne
 * @E-mail jgcjayarathne@gmail.com
 * @Telephone +94761743256
 * @created on 2021-06-22
*/

@Slf4j
@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDto>> getProductList() throws PriceEngineException {
        log.info("Get product list api called");
        return new ResponseEntity<>(productService.getProductList(), HttpStatus.OK);
    }

    @GetMapping(value = "{id}/price", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceDto> getPrice(@PathVariable("id") Integer productId,
                                             @RequestParam("quantity") Long quantity) throws PriceEngineException {
        log.info("Get product price api called");
        log.debug("product Id : {}, quantity : {}", productId, quantity);
        return new ResponseEntity<>(productService.getPrice(productId, quantity), HttpStatus.OK);
    }

    @GetMapping(value = "{id}/price-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PriceDto>> getPriceList(@PathVariable("id") Integer productId) throws PriceEngineException {
        log.info("Get product price list api called");
        log.debug("product Id : {}", productId);
        return new ResponseEntity<>(productService.getPriceList(productId), HttpStatus.OK);
    }

}
