package com.chinthaka.priceengine.service;

import com.chinthaka.priceengine.dto.ProductDto;
import com.chinthaka.priceengine.exception.PriceEngineException;
import com.chinthaka.priceengine.exception.pojo.ErrorCode;
import com.chinthaka.priceengine.model.Product;
import com.chinthaka.priceengine.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author Chinthaka Jayarathne
 * @E-mail chinthaka.jayarathne@axiatadigitallabs.com
 * @Telephone +94761743256
 * @created on 2021-06-22
 * @company Axiata Digital Labs (pvt)Ltd.
 */

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    private static final Product product1 = new Product("Product1", 20, new BigDecimal("175"));
    private static final Product product2 = new Product("Product2", 5, new BigDecimal("825"));
    private static final List<Product> productList = Stream.of(product1, product2).collect(Collectors.toList());

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void Given_DataBaseNotEmpty_Should_ReturnProductDtoList_When_getProductListMethodCalled() throws PriceEngineException {
        when(productRepository.findAll()).thenReturn(productList);

        List<ProductDto> result = productService.getProductList();

        assertFalse(CollectionUtils.isEmpty(result));
        assertEquals(2, result.size());
    }

    @Test
    void Given_DataBaseIsEmpty_Should_ThrowException_When_getProductListMethodCalled() {
        when(productRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
        PriceEngineException exception = assertThrows(PriceEngineException.class, () -> {
            productService.getProductList();
        });

        assertEquals(exception.getErrorCode(), ErrorCode.PENG001);
    }

}
