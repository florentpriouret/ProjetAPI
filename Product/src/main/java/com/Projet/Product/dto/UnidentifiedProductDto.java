package com.Projet.Product.dto;

import java.util.Date;

public class UnidentifiedProductDto {
    /**
     * The title of the book.
     */
    private String name;
    /**
     * The author of the book.
     */
    private Date consumptionDate;
    /**
     * The date of publication of the book.
     */
    private String usageDescription;

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
    public void setConsumptionDate(Date consumptionDate) {
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
