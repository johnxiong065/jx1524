package com.BigbearStore.toolRental;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ToolStoreTest {
    private ToolStore rentalStore;
    private Tool tool;

    @BeforeEach
    public void setUp() {
        rentalStore = new ToolStore();
    }

    @Test
    public void test01() {  //Invalid Discount Percent
        LocalDate checkoutDate = LocalDate.of(2015, 9, 3);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rentalStore.checkout("JAKR", 5, 101, checkoutDate);
        });
        assertEquals("Discount percent must be in the range 0-100", exception.getMessage());
    }

    @Test
    public void test02() {  // 1 holiday, 1 weekend day, and 1 weekday. Holiday will not be charged for ladder
        LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
        RentalAgreement rentalAgreement = rentalStore.checkout("LADW", 3, 10, checkoutDate);

        assertEquals("LADW", rentalAgreement.getTool().getToolCode());
        assertEquals("Ladder", rentalAgreement.getTool().getToolType());
        assertEquals("Werner", rentalAgreement.getTool().getBrand());
        assertEquals(3, rentalAgreement.getRentalDays());
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
        assertEquals(checkoutDate.plusDays(3), rentalAgreement.getDueDate());
        assertEquals(1.99, rentalAgreement.getDailyRentalCharge(), 0.01);
        assertEquals(2, rentalAgreement.getChargeDays());
        assertEquals(10, rentalAgreement.getDiscountPercent());
        assertEquals(1.99 * 2, rentalAgreement.getPreDiscountCharge(), 0.01);
        assertEquals(0.398, rentalAgreement.getDiscountAmount(), 0.01);
        assertEquals(1.99 * 2 - 0.398, rentalAgreement.getFinalCharge(), 0.01);
    }

    @Test
    public void test03() {  // 1 holiday, 2 weekend days, and 3 weekdays. 2 days (weekend) will not be charged for chainsaw
        LocalDate checkoutDate = LocalDate.of(2015, 7, 2);
        RentalAgreement rentalAgreement = rentalStore.checkout("CHNS", 5, 25, checkoutDate);

        assertEquals("CHNS", rentalAgreement.getTool().getToolCode());
        assertEquals("Chainsaw", rentalAgreement.getTool().getToolType());
        assertEquals("Stihl", rentalAgreement.getTool().getBrand());
        assertEquals(5, rentalAgreement.getRentalDays());
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
        assertEquals(checkoutDate.plusDays(5), rentalAgreement.getDueDate());
        assertEquals(1.49, rentalAgreement.getDailyRentalCharge(), 0.01);
        assertEquals(3, rentalAgreement.getChargeDays());
        assertEquals(25, rentalAgreement.getDiscountPercent());
        assertEquals(1.49 * 3, rentalAgreement.getPreDiscountCharge(), 0.01);
        assertEquals(1.12, rentalAgreement.getDiscountAmount(), 0.01);
        assertEquals(1.49 * 3 - 1.12, rentalAgreement.getFinalCharge(), 0.01);
    }

    @Test
    public void test04() {  // 1 holiday, 2 weekend days, and 3 weekdays. 3 days will not be charged for Jackhammer
        LocalDate checkoutDate = LocalDate.of(2015, 9, 3);
        RentalAgreement rentalAgreement = rentalStore.checkout("JAKD", 6, 0, checkoutDate);

        assertEquals("JAKD", rentalAgreement.getTool().getToolCode());
        assertEquals("Jackhammer", rentalAgreement.getTool().getToolType());
        assertEquals("DeWalt", rentalAgreement.getTool().getBrand());
        assertEquals(6, rentalAgreement.getRentalDays());
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
        assertEquals(checkoutDate.plusDays(6), rentalAgreement.getDueDate());
        assertEquals(2.99, rentalAgreement.getDailyRentalCharge(), 0.01);
        assertEquals(3, rentalAgreement.getChargeDays());
        assertEquals(0, rentalAgreement.getDiscountPercent());
        assertEquals(2.99 * 3, rentalAgreement.getPreDiscountCharge(), 0.01);
        assertEquals(0.00, rentalAgreement.getDiscountAmount(), 0.01);
        assertEquals(2.99 * 3, rentalAgreement.getFinalCharge(), 0.01);
    }

    @Test
    public void test05() { // 1 holiday, 2 weekend days, and 6 weekdays. 3 days will not be charged for Jackhammer
        LocalDate checkoutDate = LocalDate.of(2015, 7, 2);
        RentalAgreement rentalAgreement = rentalStore.checkout("JAKR", 9, 0, checkoutDate);

        assertEquals("JAKR", rentalAgreement.getTool().getToolCode());
        assertEquals("Jackhammer", rentalAgreement.getTool().getToolType());
        assertEquals("Ridgid", rentalAgreement.getTool().getBrand());
        assertEquals(9, rentalAgreement.getRentalDays());
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
        assertEquals(checkoutDate.plusDays(9), rentalAgreement.getDueDate());
        assertEquals(2.99, rentalAgreement.getDailyRentalCharge(), 0.01);
        assertEquals(6, rentalAgreement.getChargeDays());
        assertEquals(0, rentalAgreement.getDiscountPercent());
        assertEquals(2.99 * 6, rentalAgreement.getPreDiscountCharge(), 0.01);
        assertEquals(0.00, rentalAgreement.getDiscountAmount(), 0.01);
        assertEquals(2.99 * 6, rentalAgreement.getFinalCharge(), 0.01);
    }

    @Test
    public void test06() { // 1 holiday, 2 weekend days, and 1 weekday. only one weekday will be charged for Jackhammer
        LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
        RentalAgreement rentalAgreement = rentalStore.checkout("JAKR", 4, 50, checkoutDate);

        assertEquals("JAKR", rentalAgreement.getTool().getToolCode());
        assertEquals("Jackhammer", rentalAgreement.getTool().getToolType());
        assertEquals("Ridgid", rentalAgreement.getTool().getBrand());
        assertEquals(4, rentalAgreement.getRentalDays());
        assertEquals(checkoutDate, rentalAgreement.getCheckoutDate());
        assertEquals(checkoutDate.plusDays(4), rentalAgreement.getDueDate());
        assertEquals(2.99, rentalAgreement.getDailyRentalCharge(), 0.01);
        assertEquals(1, rentalAgreement.getChargeDays());
        assertEquals(50, rentalAgreement.getDiscountPercent());
        assertEquals(2.99 * 1, rentalAgreement.getPreDiscountCharge(), 0.01);
        assertEquals(1.50, rentalAgreement.getDiscountAmount(), 0.01);
        assertEquals(2.99 * 1 - 1.50, rentalAgreement.getFinalCharge(), 0.01);
    }

    @Test
    public void test07() {  //Invalid number of Rental Days
        LocalDate checkoutDate = LocalDate.of(2024, 7, 1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rentalStore.checkout("CHNS", 0, 25, checkoutDate);
        });
        assertEquals("Rental day count must be 1 or greater", exception.getMessage());
    }

}



