package com.Projet.Product.dto;

public class ProductDto extends UnidentifiedProductDto{
    /**
     * The identifier of the product.
     */
    private Long id;

    /**
     * Get the identifier of the product.
     * @return The product's identifier.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the identifier of the product.
     * @param id The identifier of the product.
     */
    public void setId(Long id) {
        this.id = id;
    }
}
