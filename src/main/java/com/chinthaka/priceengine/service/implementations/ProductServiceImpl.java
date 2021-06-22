package com.chinthaka.priceengine.service.implementations;

import com.chinthaka.priceengine.dto.ProductDto;
import com.chinthaka.priceengine.exception.PriceEngineException;
import com.chinthaka.priceengine.exception.pojo.ErrorCode;
import com.chinthaka.priceengine.model.Product;
import com.chinthaka.priceengine.repository.ProductRepository;
import com.chinthaka.priceengine.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Chinthaka Jayarathne
 * @E-mail chinthaka.jayarathne@axiatadigitallabs.com
 * @Telephone +94761743256
 * @created on 2021-06-22
 * @company Axiata Digital Labs (pvt)Ltd.
 */

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDto> getProductList() throws PriceEngineException {
        List<Product> productList = productRepository.findAll();
        if (CollectionUtils.isEmpty(productList)) {
            throw new PriceEngineException(HttpStatus.INTERNAL_SERVER_ERROR,
                    ErrorCode.PENG001, "No product available",
                    "Product repository find all returned empty list");
        }
        List<ProductDto> productDtoList = productList.stream().map(product -> new ProductDto(product.getId(),
                product.getName())).collect(Collectors.toList());
        log.info("Product list retrieved, size: {}", productDtoList.size());
        return productDtoList;
    }
}
