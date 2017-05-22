package com.myjob.dao;

import java.sql.*;

public class DBconnection {

    private static final String dburl = "jdbc:mysql://localhost:3306/myjob";

    private static DBconnection INSTANCE = new DBconnection();

    private static Connection connection;

    private DBconnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("MySQL JDBC driver not found in DBConnection\n" + ex);
            System.exit(0);
        }
    }

    public static DBconnection getInstance(){
        return INSTANCE;
    }

    public static Connection getConnection(){
        if (connection==null){
            try {
                connection = DriverManager.getConnection(dburl, "root", "root");
            } catch (SQLException s) {
                System.out.println("Exception thrown in retrieveUser ....");
                s.printStackTrace();
            }
        }
        return connection;
    }
    String retrieveUserFullname(String email){
        String readQuery = "SELECT * from users where email = '" + email + "';";
        String fullname = "No information found for the requested user: " + email;

        try (Connection con = DriverManager.getConnection(dburl, "root", "root");
             Statement stmt = con.createStatement();) {

            System.out.println("the query: " + readQuery);
            ResultSet rs = stmt.executeQuery(readQuery);
            while (rs.next()) {
                fullname = rs.getString("fullname");
                String state=rs.getString("state");
                System.out.println("User Fullname: " + fullname);
                System.out.println("State: " + state);
            }
            stmt.close();

        } catch (SQLException s) {
            System.out.println("Exception thrown in retrieveUser ....");
            s.printStackTrace();
        }

        return fullname;
    }
    public static void main(String[] args) {
        DBconnection dBconnection = new DBconnection();
        dBconnection.retrieveUserFullname("ctmanoj55@gmail.com");
    }
    public String mockRetrieveUserFullname(String email) {
        String fullname = "no definition found";
        switch (email) {
            case "asaad@mum.edu":
                fullname = "Asaad Saad";
                break;
            default:
                fullname = "CS472 Student";
                break;
        }
        return fullname;
    }

}
