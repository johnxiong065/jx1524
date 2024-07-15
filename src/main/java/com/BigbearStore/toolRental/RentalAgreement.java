package com.BigbearStore.toolRental;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

public class RentalAgreement {
    private final Tool tool;
    private final int rentalDays;
    private final LocalDate checkoutDate;
    private final LocalDate dueDate;
    private final int chargeDays;
    private final double dailyRentalCharge;
    private final double preDiscountCharge;
    private final double discountPercent;
    private final double discountAmount;
    private final double finalCharge;

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public Tool getTool() {
        return tool;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public double getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public double getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getFinalCharge() {
        return finalCharge;
    }

    public void printAgreement() {

        DecimalFormat dollarFormatter = new DecimalFormat("#,###.00");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");

        System.out.println("Tool code: " + tool.getToolCode());
        System.out.println("Tool type: " + tool.getToolType());
        System.out.println("Tool brand: " + tool.getBrand());
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Check out date: " + checkoutDate.format(dateFormatter));
        System.out.println("Due date: " + dueDate.format(dateFormatter));
        System.out.println("Daily rental charge: $" + dollarFormatter.format(dailyRentalCharge));
        ;
        System.out.println("Charge days: " + chargeDays);
        System.out.println("Pre-discount charge: $" + dollarFormatter.format(preDiscountCharge));
        System.out.println("Discount percent: " + discountPercent + "%");
        System.out.println("Discount amount: $" + dollarFormatter.format(discountAmount));
        System.out.println("Final charge: $" + dollarFormatter.format(finalCharge));
    }

    public RentalAgreement(Tool tool, int rentalDays, double discountPercent,
                           LocalDate checkoutDate, LocalDate dueDate,
                           double dailyRentalCharge, int chargeDays,
                           double preDiscountCharge, double discountAmount,
                           double finalCharge) {
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.discountPercent = discountPercent;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;

    }
}
