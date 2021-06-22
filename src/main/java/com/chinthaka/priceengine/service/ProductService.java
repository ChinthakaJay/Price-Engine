package com.chinthaka.priceengine.service;

import com.chinthaka.priceengine.dto.PriceDto;
import com.chinthaka.priceengine.dto.ProductDto;
import com.chinthaka.priceengine.exception.PriceEngineException;

import java.util.List;

/**
 * @author Chinthaka Jayarathne
 * @E-mail chinthaka.jayarathne@axiatadigitallabs.com
 * @Telephone +94761743256
 * @created on 2021-06-22
 * @company Axiata Digital Labs (pvt)Ltd.
 */
public interface ProductService {
    List<ProductDto> getProductList() throws PriceEngineException;

    PriceDto getPrice(Integer productId, Long quantity) throws PriceEngineException;

    List<PriceDto> getPriceList(Integer productId) throws PriceEngineException;
}
