package com.chinthaka.priceengine.service.implementations;

import com.chinthaka.priceengine.dto.ProductDto;
import com.chinthaka.priceengine.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Override
    public List<ProductDto> getProductList() {
        return null;
    }
}
