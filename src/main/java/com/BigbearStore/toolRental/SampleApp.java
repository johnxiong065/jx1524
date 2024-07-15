package com.BigbearStore.toolRental;

import java.time.LocalDate;

/**
 *  A simple sample for printing out rental agreement
 *
 */
public class SampleApp
{
    public static void main( String[] args )
    {
        System.out.println( "Rental agreement from BigbearStore:" );
        ToolStore rentalStore = new ToolStore();
        try {
            LocalDate checkoutDate = LocalDate.of(2024, 7, 1);
            RentalAgreement rentalAgreement = rentalStore.checkout("LADW", 10, 10, checkoutDate);
            rentalAgreement.printAgreement();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }

    }
}
