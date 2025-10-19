package com.mycompany.librarymanagementsystem;

import DataBase.DBAccounts;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:database/LibManagementSystem.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {

                Scanner scan = new Scanner(System.in);
                char choice;
                boolean loop = false;
                String email, password;

                System.out.println("=====[LIBRARY MANAGEMENT SYSTEM]=====");
                System.out.println("LOGIN [A]");
                System.out.println("CREATE ACCOUNT [B]");
                System.out.println("-------------------------------------");
                System.out.print("Choice: ");
                choice = scan.nextLine().charAt(0);

                switch (choice) {
                    case 'A' -> {
                        do {
                            System.out.println("-------------------------------------");
                            System.out.print("Email: ");
                            email = scan.nextLine();
                            if (DBAccounts.emailExists(conn, email) == true) {
                                System.out.print("Password: ");
                                password = scan.nextLine();
                                if (DBAccounts.passwordMatches(conn, email, password)) {
                                    System.out.println("Login Successful!");
                                    break;
                                }
                            } else {
                                System.out.println("Email Does Not Exist!");
                                loop = true;
                            }
                        } while (loop == true);
                        break;
                    }

                    case 'B' -> {
                    }
                }

                System.out.println("HELLO!");
            }
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
