package com.Projet.Product.repositories;

import com.Projet.Product.entities.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * This repository act as an SQL requester to the book entity.
 */
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
}
