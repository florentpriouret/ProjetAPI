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
     * Repository used to request the book table.
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
     * Create a book object and persist it.
     *
     * @param productDto The book to add to catalog.
     * @return The created book enhanced with a new id.
     */
    @PostMapping(path = "/", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody UnidentifiedProductDto productDto) {

        Product product = toEntity(productDto);
        Product productCreated = productService.createBook(product);
        return toDto(productCreated);
    }

    /**
     * Get all books.
     *
     * @return All the books found in database.
     */
    @GetMapping(path = "/", produces = APPLICATION_JSON_VALUE)
    public List<ProductDto> getBooks(int page, int size, String sortDirection, String sort) {

        List<Product> books = productService.getProducts(page, size, sortDirection, sort);
        return books
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Get all books.
     *
     * @return All the books found in database.
     */
    @GetMapping(path = "/all", produces = APPLICATION_JSON_VALUE)
    public List<ProductDto> getBooks() {

        List<Product> products = productService.getProducts(5, 5, "ASC", "name");
        return products
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Get a book by its id.
     *
     * @param id The id of the book to get.
     * @return The book found or an EntityNotFoundException.
     */
    @GetMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ProductDto getProduct(@PathVariable("id") Long id) {

        Product product = productService.getProduct(id);
        return toDto(product);
    }

    /**
     * Update a book by its id.
     *
     * @param productDto The new values to set.
     * @param id      The id of the book to update.
     * @return The updated book or entityNotFoundException when book cannot be found.
     */
    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ProductDto updateProduct(@PathVariable("id") Long id, @RequestBody UnidentifiedProductDto productDto) {

        Product book = toEntity(productDto);
        Product bookUpdated = productService.updateProduct(id, book);

        return toDto(bookUpdated);
    }

    /**
     * Delete a book by its id.
     *
     * @param id The id of the book to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);
    }

    /**
     * Convert an unidentified book DTO to a book entity.
     * @param unidentifiedProductDto The unidentified book DTO.
     * @return The book entity.
     */
    private Product toEntity(UnidentifiedProductDto unidentifiedProductDto) {
        return modelMapper.map(unidentifiedProductDto, Product.class);
    }

    /**
     * Convert a book entity to an identified book DTO.
     * @param product The product entity.
     * @return The identified book DTO.
     */
    private ProductDto toDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }
}
