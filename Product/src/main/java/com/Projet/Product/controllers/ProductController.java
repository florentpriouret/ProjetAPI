package com.Projet.Product.controllers;

import com.Projet.Product.dto.ProductDto;
import com.Projet.Product.dto.UnidentifiedProductDto;
import com.Projet.Product.entities.Product;
import com.Projet.Product.services.ProductService;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Rest controller.
 * It handles all HTTP requests received from server to perform operation based on mapped URLs.
 */
@RestController
@RequestMapping(path = "/products", produces = APPLICATION_JSON_VALUE)
public class ProductController {
    /**
     * Repository used to request the product table.
     */
    private final ProductService productService;

    /**
     * Model mapper used to convert Entities to DTO.
     */
    private final ModelMapper modelMapper;

    /**
     * Let spring create the controller and inject service into it.
     *
     * @param productService The service to manage products.
     * @param modelMapper The mapper to map entities to DTO.
     */
    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    /**
     * Create a product object and persist it.
     *
     * @param productDto The product to add to catalog.
     * @return The created product enhanced with a new id.
     */
    @PostMapping(path = "/", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody UnidentifiedProductDto productDto) {

        Product product = toEntity(productDto);
        Product productCreated = productService.createProduct(product);
        return toDto(productCreated);
    }

    /**
     * Get all products.
     *
     * @return All the products found in database.
     */
    @GetMapping(path = "/", produces = APPLICATION_JSON_VALUE)
    public List<ProductDto> getProducts(int page, int size, String sortDirection, String sort) {

        List<Product> products = productService.getProducts(page, size, sortDirection, sort);
        return products
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Get all products.
     *
     * @return All the products found in database.
     */
    @GetMapping(path = "/all", produces = APPLICATION_JSON_VALUE)
    public List<ProductDto> getProducts() {

        List<Product> products = productService.getProducts(5, 5, "ASC", "name");
        return products
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Get a product by its id.
     *
     * @param id The id of the product to get.
     * @return The product found or an EntityNotFoundException.
     */
    @GetMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ProductDto getProduct(@PathVariable("id") Long id) {

        Product product = productService.getProduct(id);
        return toDto(product);
    }

    /**
     * Update a product by its id.
     *
     * @param productDto The new values to set.
     * @param id      The id of the product to update.
     * @return The updated product or entityNotFoundException when product cannot be found.
     */
    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ProductDto updateProduct(@PathVariable("id") Long id, @RequestBody UnidentifiedProductDto productDto) {

        Product product = toEntity(productDto);
        Product productUpdated = productService.updateProduct(id, product);

        return toDto(productUpdated);
    }

    /**
     * Delete a product by its id.
     *
     * @param id The id of the product to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);
    }

    /**
     * Convert an unidentified product DTO to a product entity.
     * @param unidentifiedProductDto The unidentified product DTO.
     * @return The product entity.
     */
    private Product toEntity(UnidentifiedProductDto unidentifiedProductDto) {
        return modelMapper.map(unidentifiedProductDto, Product.class);
    }

    /**
     * Convert a product entity to an identified product DTO.
     * @param product The product entity.
     * @return The identified product DTO.
     */
    private ProductDto toDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }
}
