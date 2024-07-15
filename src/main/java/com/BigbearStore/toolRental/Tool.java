package com.BigbearStore.toolRental;

public class Tool {
    private String code;
    private String brand;
    private String type;
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;
    private double dailyCharge;

    public Tool(String toolCode, String toolType, String brand, double dailyCharge,boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.code = toolCode;
        this.type = toolType;
        this.brand = brand;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    public Tool(){}

    public String getToolCode() {
        return code;
    }

    public String getToolType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public double getDailyCharge() { return dailyCharge; }

    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWeekdayCharge(boolean weekdayCharge) {
        this.weekdayCharge = weekdayCharge;
    }

    public void setWeekendCharge(boolean weekendCharge) {
        this.weekendCharge = weekendCharge;
    }

    public void setHolidayCharge(boolean holidayCharge) {
        this.holidayCharge = holidayCharge;
    }

    public void setDailyCharge(double dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

}
