package com.Projet.Product.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Entity used to map the Product table of the database to a spring bean.
 */
@Entity
public class Product {
    /**
     * id is the primary key of the table. So @Id set used on it.
     * We let the persistence provider choose the strategy to generate the identifier.
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * The name of the test.
     */
    private String name;
    /**
     * The consumption date of the test.
     */
    private Date consumptionDate;
    /**
     * The usage description.
     */
    private String usageDescription;

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

    /**
     * Get the title of the book.
     * @return The title of the book.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the title of the book;
     * @param name The title of the book.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the author of the book.
     * @return The author of the book.
     */
    public Date getConsumptionDate() {
        return consumptionDate;
    }

    /**
     * Set the author of the book.
     * @param consumptionDate The author of the book.
     */
    public void setAuthor(Date consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    /**
     * Get the date of publication of the book.
     * @return The date of publication of the book.
     */
    public String getUsageDescription() {
        return usageDescription;
    }

    /**
     * Set the date of publication of the book.
     * @param usageDescription The date of publication of the book.
     */
    public void setUsageDescription(String usageDescription) {
        this.usageDescription = usageDescription;
    }
}
