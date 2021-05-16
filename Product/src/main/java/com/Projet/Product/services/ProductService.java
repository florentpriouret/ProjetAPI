package com.Projet.Product.services;

import com.Projet.Product.entities.Product;
import com.Projet.Product.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    /**
     * The product repository used to request database.
     */
    private final ProductRepository productRepository;

    /**
     * Create a constructor with beans injected by spring as parameter
     * @param productRepository The repository of products to be injected by spring.
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Create a product object and persist it.
     *
     * @param product The product to add to catalog.
     * @return The created product enhanced with a new id.
     */
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    /**
     * Get all PRODUCTS.
     *
     * @return All the products found in database.
     */
    public List<Product> getProducts(int page, int size, String sortDirection, String sort) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sort);

        Page<Product> products = productRepository
                .findAll(pageRequest);
        return products.getContent();
    }

    /**
     * Get a product by its id.
     *
     * @param id The id of the product to get.
     * @return The product found or an EntityNotFoundException.
     */
    public Product getProduct(Long id) {
        Optional<Product> productOpt = productRepository.findById(id);

        return productOpt.orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Update a product by its id.
     *
     * @param product The new values to set.
     * @param id   The id of the product to update.
     * @return The updated product or entityNotFoundException when product cannot be found.
     */
    public Product updateProduct(Long id, Product product) {
        Optional<Product> productOpt = productRepository.findById(id);

        return productOpt.map(currentProduct -> {
            product.setId(currentProduct.getId());
            return productRepository.save(product);
        }).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Delete a product by its id.
     *
     * @param id The id of the product to delete.
     */
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
