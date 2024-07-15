package com.BigbearStore.toolRental;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.time.DayOfWeek;

public class ToolStore {
    // Will use H2 DB (please review the code in ToolDB.java) in next version.
    // Hardcoded data store for simple testing and reviewing

    private final Map<String, Tool>  toolInventory = new HashMap<>();
    public ToolStore() {
        toolInventory.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl", 1.49, true, false, true));
        toolInventory.put("LADW", new Tool("LADW", "Ladder", "Werner", 1.99, true, true, false));
        toolInventory.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt", 2.99, true, false, false));
        toolInventory.put("JAKR", new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false, false));
    }

    public RentalAgreement checkout(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        if (rentalDays < 1) {
            throw new IllegalArgumentException("Rental day count must be 1 or greater");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be in the range 0-100");
        }

        Tool tool = toolInventory.get(toolCode);
        if (tool == null) {
            throw new IllegalArgumentException("Invalid toolCode.");
        }
        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        int chargeDays = calculateActualChargeDays(tool, checkoutDate, dueDate);
        double dailyCharge = tool.getDailyCharge();
        double preDiscountCharge = chargeDays * dailyCharge;
        double discountAmount = preDiscountCharge * discountPercent / 100;
        double finalCharge = preDiscountCharge - discountAmount;

        return new RentalAgreement(tool, rentalDays, discountPercent, checkoutDate, dueDate, dailyCharge, chargeDays,
                preDiscountCharge, discountAmount, finalCharge);
    }

    private int calculateActualChargeDays(Tool tool, LocalDate startDate, LocalDate endDate) {
        int actualChargeDays = 0;
        LocalDate currentDate = startDate;

        while (currentDate.isBefore(endDate)) {
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();

            if (Helper.isHoliday(currentDate)) {
                if (tool.isHolidayCharge()) {
                    actualChargeDays++;
                }
            }
            else if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                if (tool.isWeekendCharge()) {
                    actualChargeDays++;
                }
            } else {
                if (tool.isWeekdayCharge()) {
                    actualChargeDays++;
                }
            }
            currentDate = currentDate.plusDays(1);
        }
        return actualChargeDays;
    }
}
