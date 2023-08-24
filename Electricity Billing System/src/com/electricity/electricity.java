package com.electricity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.electricity.manage.CP;
import com.electricity.manage.managingUser;
import com.electricity.manage.userDao;

public class electricity {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("---------------------------------");
            System.out.println("|                               |");
            System.out.println("|                               |");
            System.out.println("|   Electricity Billing System  |");
            System.out.println("|                               |");
            System.out.println("|                               |");
            System.out.println("---------------------------------");
            System.out.println("|Option 1: Customers Management |");
            System.out.println("|Option 2: Bill Generation      |");
            System.out.println("|Option 3: Payment Receiving    |");
            System.out.println("|Option 4: Exit                 |");
            System.out.println("---------------------------------");

            int userChoice = sc.nextInt(); // Read user choice

            switch (userChoice) {
                case 1:
                    // Customer Management
                    userDao.displayCustomer();
                    int customerChoice = sc.nextInt(); // Read customer management choice

                    switch (customerChoice) {
                        case 1:
                            // ADD Customer
                            try {
                                // Collect customer information
                                System.out.println("Enter User Name: ");
                                String namedb = br.readLine();

                                System.out.println("Enter MeterType: ");
                                String Mtype = br.readLine();

                                System.out.println("Enter User City: ");
                                String citydb = br.readLine();

                                System.out.print("Enter meterNumber: ");
                                double meterNumberdb = Double.parseDouble(br.readLine());
                                // Create a managingUser object to store customer data
                                managingUser manage = new managingUser(namedb, citydb, meterNumberdb, Mtype);
                                // Insert customer data into the database
                                Boolean answer = userDao.insertStudentToDB(manage);
                                if (answer) {
                                    System.out.println("Customer added successfully.");
                                } else {
                                    System.out.println("Something went wrong while adding customer.");
                                }
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                            break;

                        case 2:
                            try {
                                Boolean answer = userDao.showAllCustomers();
                                if (answer) {
                                    System.out.println("Customers retrieved successfully.");
                                } else {
                                    System.out.println("Something went wrong while retrieving customers.");
                                }
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                            break;

                        case 3:
                            // update data
                            try {
                                System.out.println("Enter your id : ");
                                int userId = sc.nextInt();
                                managingUser userToUpdate = userDao.getUserFromDatabase(userId);
                                // Replace with the actual user ID
                                // userId is the ID of the user to update
                                System.out.println("Enter New Name : ");
                                String newName = sc.nextLine();
                                System.out.println("Enter new City : ");
                                String newCity = sc.nextLine();
                                System.out.println("Enter new MeterType : ");
                                String newMeterType = sc.nextLine();
                                System.out.println("Enter new MeterNumber : ");
                                double newMeterNumber = sc.nextDouble();
                                // Update the values of the managingUser object
                                userToUpdate.setName(newName);
                                userToUpdate.setCity(newCity);
                                userToUpdate.setMeterType(newMeterType);
                                userToUpdate.setMeterNumber(newMeterNumber);

                                Boolean answer = userDao.updateValues(userToUpdate);
                                if (answer) {
                                    System.out.println("Updated successfully.");
                                } else {
                                    System.out.println("Something went wrong while updating.");
                                }
                            } catch (Exception e) {
                                // Handle exceptions
                                e.printStackTrace();
                            }

                            break;
                        case 4:
                            try {
                                Connection connection = CP.createConnection();
                                String deleteQuery = "DELETE FROM  customers where id = ? ";
                                System.out.println("Enter id to delete ");
                                int customerIdToDelete = sc.nextInt();
                                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);

                                // Set the parameter value
                                preparedStatement.setInt(1, customerIdToDelete);

                                // Execute the DELETE query
                                int rowsAffected = preparedStatement.executeUpdate();

                                if (rowsAffected > 0) {
                                    System.out.println("Data deleted successfully.");
                                } else {
                                    System.out.println("No data deleted.");
                                }

                                // Close the resources
                                preparedStatement.close();
                                connection.close();

                            } catch (Exception e) {
                                // TODO: handle exception
                            }
                            break;
                      
                    }
                    break;

                    case 2:
                    // bill  generation
                               userDao newDao = new userDao();
                               newDao.generateBill();     
                    break;
                      case 3:
                    // payment receiving

                    System.out.println("Enter username : ");
                  
                    sc.nextLine();
    
    String userName = sc.nextLine().trim();
                    userDao Dao = new userDao();
                    Dao.retrieveBillData(userName);
                    Dao.paymentReceiving(userName);
                    break;
                      case 4:
                    // exiting the program 
                    
                    break;
                // ... (cases for other options like Billing Management, Payment Receiving, and
                // Exit)
                default:
                    System.out.println("Invalid choice.");
                    break;
            }

            System.out.println("... Thank you for using our Billing system ...");
            System.out.println("          ... ... Bye ... Bye ... ...        ");
        }

    }

}
