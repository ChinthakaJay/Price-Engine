package com.chinthaka.priceengine.repository;

import com.chinthaka.priceengine.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Chinthaka Jayarathne
 * @E-mail chinthaka.jayarathne@axiatadigitallabs.com
 * @Telephone +94761743256
 * @created on 2021-06-22
 * @company Axiata Digital Labs (pvt)Ltd.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findOneById(int id);
}
