package org.example.model;

import java.time.LocalDate;

public class CheckoutData {
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private int rentalDays;
    private int chargeableDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private Double dailyRentalCharge;

    private Double preDiscountChharge;
    private Double discountPercent;
    private Double discountAmount;
    private Double finalCharge;

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Double getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public void setDailyRentalCharge(Double dailyRentalCharge) {
        this.dailyRentalCharge = dailyRentalCharge;
    }



    public Double getPreDiscountChharge() {
        return preDiscountChharge;
    }

    public void setPreDiscountChharge(Double preDiscountChharge) {
        this.preDiscountChharge = preDiscountChharge;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(Double finalCharge) {
        this.finalCharge = finalCharge;
    }

    public int getChargeableDays() {
        return chargeableDays;
    }

    public void setChargeableDays(int chargeableDays) {
        this.chargeableDays = chargeableDays;
    }
}
