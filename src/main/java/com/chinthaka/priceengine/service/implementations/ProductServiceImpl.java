package com.chinthaka.priceengine.service.implementations;

import com.chinthaka.priceengine.dto.PriceDto;
import com.chinthaka.priceengine.dto.ProductDto;
import com.chinthaka.priceengine.exception.PriceEngineException;
import com.chinthaka.priceengine.exception.pojo.ErrorCode;
import com.chinthaka.priceengine.model.Product;
import com.chinthaka.priceengine.repository.ProductRepository;
import com.chinthaka.priceengine.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author Chinthaka Jayarathne
 * @E-mail jgcjayarathne@gmail.com
 * @Telephone +94761743256
 * @created on 2021-06-22
 */

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Value("${single-unit.markup}")
    private double singleUnitMarkup;

    @Value("${min.cartoons}")
    private int minCartoons;

    @Value("${discount}")
    private double discount;

    @Value("${price.decimal-places}")
    private int priceDecimalPlaces;

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

    @Override
    public PriceDto getPrice(Integer productId, Long quantity) throws PriceEngineException {
        Product product = productRepository.findOneById(productId);
        if (ObjectUtils.isEmpty(product)) {
            throw new PriceEngineException(HttpStatus.BAD_REQUEST,
                    ErrorCode.PENG002,
                    "Product does not exists", "Product not found for id: " + productId);
        }
        return new PriceDto(quantity, calculatePrice(product, quantity).toString());
    }

    @Override
    public List<PriceDto> getPriceList(Integer productId) throws PriceEngineException {
        Product product = productRepository.findOneById(productId);
        if (ObjectUtils.isEmpty(product)) {
            throw new PriceEngineException(HttpStatus.BAD_REQUEST,
                    ErrorCode.PENG003,
                    "Product does not exists", "Getting price list product not found for id: " + productId);
        }

        List<PriceDto> priceDtoList = LongStream.range(1, 51).parallel()
                .mapToObj(i -> new PriceDto(i, calculatePrice(product, i).toString()))
                .collect(Collectors.toList());
        log.info("price list created successfully size: {}", priceDtoList.size());
        return priceDtoList;
    }

    private BigDecimal calculatePrice(Product product, Long quantity) {

        long noOfFullCartoons = Math.floorDiv(quantity, product.getUnitsPerCartoon());
        long singleUnits = Math.floorMod(quantity, product.getUnitsPerCartoon());
        BigDecimal cartoonPrice;

        if (noOfFullCartoons >= minCartoons) {
            cartoonPrice =
                    product.getPricePerCartoon().multiply(BigDecimal.valueOf(noOfFullCartoons * (1 - discount)));
        } else {
            cartoonPrice =
                    product.getPricePerCartoon().multiply(BigDecimal.valueOf(noOfFullCartoons));
        }

        return cartoonPrice.add(product.getPricePerCartoon()
                .multiply(BigDecimal.valueOf(singleUnits * (1 + singleUnitMarkup) / product.getUnitsPerCartoon())))
                .setScale(priceDecimalPlaces, RoundingMode.CEILING);
    }
}
