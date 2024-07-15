package com.BigbearStore.toolRental;
/*
Improvements using DB (ToolStore class not hooked up with DB yet)

1. Database Integration:
   - Replace hardcoded tool inventory in ToolStore with H2 database
   - This change will make the solution more flexible and general-purpose

2. Core Business Logic:
   - Current implementation focuses on calculating actual charge days and amount
   - This forms the essential functionality of the rental system

3. Instead of using jdbc, Use JPA to re-design Tool class and add new ToolType class with JPA integration

4. Proposed DB-related for future enhancement:
   a) setupToolDB(): Initialize the database with tool data
        (sample code done in this file) -- code not used in the project
   b) addTool(Tool tool): Add a new tool to the inventory
        (sample data done in this file in setupToolDB) -- code not used in the project
   c) updateTool(int toolCode, Tool updatedTool): Modify existing tool information
   d) deleteTool(int toolCode): Remove a tool from the inventory
   e) getAllTools(): Fetch the complete tool inventory

Note: Due to time constraints, some database features are not implemented in the current version.
They represent potential areas for future improvement to enhance the system's functionality and flexibility.
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ToolDB {

    private static final String db_url = "jdbc:h2:~/tolRental;AUTO_SERVER=TRUE";

    public static void setupToolDB() {
        // Establish a connection
        try (
            Connection connection = DriverManager.getConnection(db_url, "sa", "")) {
            // Create a statement
            Statement statement = connection.createStatement();

            // Execute the SQL script to create and populate tables
            String sqlScript =
                    "-- Drop the existing tables if they exist\n" +
                            "DROP TABLE IF EXISTS tool;\n" +
                            "DROP TABLE IF EXISTS tool_type;\n" +
                            "\n" +
                            "-- Create the tool_type table\n" +
                            "CREATE TABLE tool_type (\n" +
                            " tool_type VARCHAR(50) PRIMARY KEY,\n" +
                            " daily_charge DECIMAL(5, 2),\n" +
                            " weekday_charge BOOLEAN,\n" +
                            " weekend_charge BOOLEAN,\n" +
                            " holiday_charge BOOLEAN\n" +
                            ");\n" +
                            "\n" +
                            "-- Insert data into the tool_type table\n" +
                            "INSERT INTO tool_type (tool_type, daily_charge, weekday_charge, weekend_charge, holiday_charge) VALUES \n" +
                            "('Ladder', 1.99, TRUE, TRUE, FALSE),\n" +
                            "('Chainsaw', 1.49, TRUE, FALSE, TRUE),\n" +
                            "('Jackhammer', 2.99, TRUE, FALSE, FALSE);\n" +
                            "\n" +
                            "-- Create the tool table\n" +
                            " CREATE TABLE tool (\n" +
                            " tool_code VARCHAR(10) PRIMARY KEY,\n" +
                            " tool_type VARCHAR(50),\n" +
                            " brand VARCHAR(50),\n" +
                            " CONSTRAINT fk_tool_type FOREIGN KEY (tool_type) REFERENCES tool_type(tool_type)\n" +
                            ");\n" +
                            "\n" +
                            "-- Insert data into the tool table\n" +
                            "INSERT INTO tool (tool_code, tool_type, brand) VALUES \n" +
                            "('CHNS', 'Chainsaw', 'Stihl'),\n" +
                            "('LADW', 'Ladder', 'Werner'),\n" +
                            "('JAKD', 'Jackhammer', 'DeWalt'),\n" +
                            "('JAKR', 'Jackhammer', 'Ridgid');\n" +
                            "\n" +
                            "-- Verify the data\n" +
                            "SELECT * FROM tool_type;\n" +
                            "SELECT * FROM tool;\n";

            statement.execute(sqlScript);
            System.out.println("Tables created and populated successfully.");

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public static Tool getToolByCode(String toolCode) {
        String db_url = "jdbc:h2:~/test;AUTO_SERVER=TRUE";

        String query =
                "SELECT " +
                        "t.tool_code, " +
                        "t.tool_type, " +
                        "t.brand, " +
                        "tt.daily_charge, " +
                        "tt.weekday_charge, " +
                        "tt.weekend_charge, " +
                        "tt.holiday_charge " +
                        "FROM " +
                        "tool t " +
                        "INNER JOIN " +
                        "tool_type tt ON t.tool_type = tt.tool_type " +
                        "WHERE " +
                        "t.tool_code = ?";
        Tool tool = null;

        try (Connection conn = DriverManager.getConnection(db_url, "sa", "");
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, toolCode);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                tool = new Tool();
                tool.setCode(rs.getString("tool_code"));
                tool.setType(rs.getString("tool_type"));
                tool.setBrand(rs.getString("brand"));
                tool.setDailyCharge(rs.getDouble("daily_charge"));
                tool.setWeekdayCharge(rs.getBoolean("weekday_charge"));
                tool.setWeekendCharge(rs.getBoolean("weekend_charge"));
                tool.setHolidayCharge(rs.getBoolean("holiday_charge"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tool;
    }
}