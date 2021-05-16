package com.Projet.Front.dto;

public class ProductDto extends UnidentifiedProductDto{
    /**
     * The identifier of the book.
     */
    private Long id;

    /**
     * Get the identifier of the book.
     * @return The book's identifier.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the identifier of the book.
     * @param id The identifier of the book.
     */
    public void setId(Long id) {
        this.id = id;
    }
}
