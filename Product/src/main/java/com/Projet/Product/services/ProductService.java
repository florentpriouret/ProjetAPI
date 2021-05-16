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
     * The book repository used to request database.
     */
    private final ProductRepository productRepository;

    /**
     * Create a constructor with beans injected by spring as parameter
     * @param productRepository The repository of books to be injected by spring.
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Create a book object and persist it.
     *
     * @param product The book to add to catalog.
     * @return The created book enhanced with a new id.
     */
    public Product createBook(@RequestBody Product product) {
        return productRepository.save(product);
    }

    /**
     * Get all BOKS.
     *
     * @return All the books found in database.
     */
    public List<Product> getProducts(int page, int size, String sortDirection, String sort) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sort);

        Page<Product> books = productRepository
                .findAll(pageRequest);
        return books.getContent();
    }

    /**
     * Get a book by its id.
     *
     * @param id The id of the book to get.
     * @return The book found or an EntityNotFoundException.
     */
    public Product getProduct(Long id) {
        Optional<Product> productOpt = productRepository.findById(id);

        return productOpt.orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Update a book by its id.
     *
     * @param product The new values to set.
     * @param id   The id of the book to update.
     * @return The updated book or entityNotFoundException when book cannot be found.
     */
    public Product updateProduct(Long id, Product product) {
        Optional<Product> productOpt = productRepository.findById(id);

        return productOpt.map(currentBook -> {
            product.setId(currentBook.getId());
            return productRepository.save(product);
        }).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Delete a book by its id.
     *
     * @param id The id of the book to delete.
     */
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
