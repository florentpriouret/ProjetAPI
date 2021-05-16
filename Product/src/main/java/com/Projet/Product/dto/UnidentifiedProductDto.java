package com.Projet.Product.dto;

import java.util.Date;

public class UnidentifiedProductDto {
    /**
     * The name of the product.
     */
    private String name;
    /**
     * The consumption date of the product.
     */
    private Date consumptionDate;
    /**
     * The usage description of the product.
     */
    private String usageDescription;

    /**
     * Get the name of the product.
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the product;
     * @param name The name of the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the consumption Date of the product.
     * @return The consumption Date of the product.
     */
    public Date getConsumptionDate() {
        return consumptionDate;
    }

    /**
     * Set the consumptionDate of the product.
     * @param consumptionDate The consumptionDate of the product.
     */
    public void setConsumptionDate(Date consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    /**
     * Get the usage Description of the product.
     * @return The usage Description of the product.
     */
    public String getUsageDescription() {
        return usageDescription;
    }

    /**
     * Set the usage Description of the product.
     * @param usageDescription The usage Description of the product.
     */
    public void setUsageDescription(String usageDescription) {
        this.usageDescription = usageDescription;
    }
}
