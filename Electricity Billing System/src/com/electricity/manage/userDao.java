package com.electricity.manage;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class userDao {
    Scanner sc = new Scanner(System.in);

    // Customer Management
    public static void displayCustomer() {
        System.out.println("1: ADD Customer");
        System.out.println("2: ALL Customer");
        System.out.println("3: UPDATE Customer");
        System.out.println("4: DELETE Customer");

    }

    // ADD Customers
    public static Boolean insertStudentToDB(managingUser st) {
        // jdbc code
        Boolean f = false;
        try {

            Connection con = CP.createConnection();
            String q = "INSERT INTO customers (sname, meterType, scity, meterNumber) VALUES (?, ?, ?, ?)";

            // preparedStatement
            PreparedStatement pstmt = con.prepareStatement(q);
            // set the value of parameters
            pstmt.setString(1, st.getName());
            pstmt.setString(2, st.getMeterType());
            pstmt.setString(3, st.getCity());
            pstmt.setDouble(4, st.getMeterNumber());

            pstmt.executeUpdate();
            f = true;

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return f;
    }

    // Show all customers
    public static Boolean showAllCustomers() {
        Boolean f = false;
        try {
            Connection con = CP.createConnection();
            String q = "select * from customers";
            PreparedStatement pstmt = con.prepareStatement(q);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                String sname = resultSet.getString("sname");
                String meterType = resultSet.getString("meterType");
                String scity = resultSet.getString("scity");
                double meterNumber = resultSet.getDouble("meterNumber");

                // Print or process the retrieved data
                System.out.println("Name: " + sname);
                System.out.println("Meter Type: " + meterType);
                System.out.println("City: " + scity);
                System.out.println("Meter Number: " + meterNumber);
                System.out.println("-------------------------");
            }
            f = true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return f;
    }

    // update values in my sql
    public static Boolean updateValues(managingUser mt) {
        try {
            Connection connection = CP.createConnection(); // Establish a database connection

            // Create an UPDATE query
            String updateQuery = "UPDATE customers SET sname = ?, scity = ?, meterType = ?, meterNumber = ? WHERE id = ?";

            // Provide the new values and the condition value
            String newName = mt.getName(); // Use the provided managingUser's name
            String newCity = mt.getCity();
            String newMeterType = mt.getMeterType();
            double newMeterNumber = mt.getMeterNumber();
            // Use the provided managingUser's city
            int customerIdToUpdate = mt.getId(); // Use the provided managingUser's ID

            // Prepare the statement
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

            // Set the parameter values
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newCity);
            preparedStatement.setString(3, newMeterType);
            preparedStatement.setDouble(4, newMeterNumber);

            preparedStatement.setInt(5, customerIdToUpdate);

            // Execute the UPDATE query
            int rowsAffected = preparedStatement.executeUpdate();

            // Close the resources
            preparedStatement.close();
            connection.close();

            // Return true if rows were affected, indicating successful update
            return rowsAffected > 0;
        } catch (Exception e) {
            // Handle exceptions by printing the stack trace for debugging
            e.printStackTrace();
            return false; // Return false if an exception occurs
        }
    }

    // error checking on this
    public static managingUser getUserFromDatabase(int userId) {
        managingUser user = null;
        try {
            Connection connection = CP.createConnection(); // Establish a database connection

            // Create a SELECT query to retrieve user data by ID
            // error checking here for sname or scity now add metertype ,number also others
            String selectQuery = "SELECT id, sname, scity , meterType, meterNumber FROM customers WHERE id = ?";

            // Prepare the statement
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            // Set the parameter value
            preparedStatement.setInt(1, userId);

            // Execute the query and get the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if a record was found
            if (resultSet.next()) {
                // Create a managingUser object with the retrieved data
                // also error checking here
                user = new managingUser();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("sname"));
                user.setCity(resultSet.getString("scity"));
                user.setMeterType(resultSet.getString("meterType"));
                user.setMeterNumber(resultSet.getDouble("meterNumber"));

                // Set other properties if needed
            }

            // Close the resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
        return user;
    }
    // ... (other imports and code)


   
    // Bill generation
    public void generateBill() {
        System.out.println("Enter your id : ");
        int userId = sc.nextInt();
        managingUser user = getUserFromDatabase(userId);
        if (user != null) {
            int taxs = 30;
            int fixedChargess = 100;
            double lightRate;
            double result = 0.0;
            double consumptionLevels;
            System.out.println("---GENERATION OF BILL---");
            System.out.println("Enter the consumption level : ");
            consumptionLevels = sc.nextDouble();
            if (consumptionLevels > 0 && consumptionLevels <= 50) {
                lightRate = 2.00;
                result = lightRate * consumptionLevels;
                System.out.println("Your Total bill is : " + result);
            } else if (consumptionLevels > 50 && consumptionLevels <= 100) {
                lightRate = 2.60;
                result = lightRate * consumptionLevels;
                System.out.println("Your Total bill is : " + result);
            }

            else if (consumptionLevels > 100 && consumptionLevels <= 200) {
                lightRate = 3.20;
                result = lightRate * consumptionLevels + taxs + fixedChargess;
                System.out.println("Your Total bill is : " + result);
            }

            else if (consumptionLevels > 200 && consumptionLevels <= 300) {
                lightRate = 3.80;
                result = lightRate * consumptionLevels + taxs + fixedChargess;
                System.out.println("Your Total bill is : " + result);
            } else if (consumptionLevels > 300 && consumptionLevels <= 400) {
                lightRate = 4.40;
                result = lightRate * consumptionLevels + taxs + fixedChargess;
                System.out.println("Your Total bill is : " + result);
            } else if (consumptionLevels > 400 && consumptionLevels <= 500) {
                lightRate = 5.00;
                result = lightRate * consumptionLevels + taxs + fixedChargess;
                System.out.println("Your Total bill is : " + result);
            } else if (consumptionLevels > 500) {
                lightRate = 5.60;
                result = lightRate * consumptionLevels + taxs + fixedChargess;
                System.out.println("Your Total bill is : " + result);
            } else {
                System.out.println("Write correct level of consumption ");
            }
      

            insertBillIntoDatabase(consumptionLevels, result,user);
        } else {
            System.out.println("User not found.");
        }
    }

    private void insertBillIntoDatabase(double consumptionLevels, double result, managingUser user) {
        try {
            Connection connection = CP.createConnection();
            String insertQuery = "INSERT INTO bill_table (name, meterType, meterNumber, consumption_level, total_bill) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getMeterType());
            preparedStatement.setDouble(3, user.getMeterNumber());
            preparedStatement.setDouble(4, consumptionLevels);
            preparedStatement.setDouble(5, result);
            preparedStatement.executeUpdate();
    
            // Close the connection
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
   
    // select customer by id
    private static managingUser getCustomerDetails(Connection connection, int customerId) throws Exception {
        // Retrieve customer details using SQL query
        String query = "SELECT * FROM customer WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, customerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Create and return a Customer object
                    managingUser customer = new managingUser();
                    customer.setId(resultSet.getInt("id"));
                    customer.setName(resultSet.getString("name"));
                    // Set other properties
                    return customer;
                }
            }
        }
        throw new Exception("Customer not found");
    }
    public void retrieveBillData(String userName) {
        try {
            Connection connection = CP.createConnection();
            String selectQuery = "SELECT * FROM bill_table WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String meterType = resultSet.getString("meterType");
                String meterNumber = resultSet.getString("meterNumber");
                double consumptionLevel = resultSet.getDouble("consumption_level");
                double totalBill = resultSet.getDouble("total_bill");
    
                System.out.println("Bill ID: " + id);
                System.out.println("Meter Type: " + meterType);
                System.out.println("Meter Number: " + meterNumber);
                System.out.println("Consumption Level: " + consumptionLevel);
                System.out.println("Total Bill: " + totalBill);
                System.out.println("-----------");
            }
    
            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public void paymentReceiving(String userName) {
        System.out.println("Enter Amount to Pay: ");
        double billAmount = sc.nextDouble();
 
        System.out.println("Your bill amount is paid " + billAmount);
    
        // Update bill status to 0 (indicating paid)
        try {
            Connection connection = CP.createConnection();
            String updateQuery = "UPDATE bill_table SET total_bill = 0, consumption_level = 0 WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, userName);
            preparedStatement.executeUpdate();
    
            System.out.println("Bill is paid: " + billAmount);
    
            // Close resources
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
