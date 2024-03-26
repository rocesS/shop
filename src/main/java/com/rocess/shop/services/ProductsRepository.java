package com.rocess.shop.services;

import com.rocess.shop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product, Integer> {

}
