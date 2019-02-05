package com.proj;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Database {
    
    private static final String DB_URL2 = "localhost:3306";
    private static final String FULL_DB_URL = "jdbc:mysql://" + DB_URL2 + "/indproj?zeroDateTimeBehavior=convertToNull&serverTimezone=Europe/Athens&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWD = "1234nthomo";
    private String username;
    private String superAdmin;
    private int userId = 1;

    public boolean connectAndTest(boolean bPrint) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users");
            //if(resultSet.first()) return true;
            while (resultSet.next()) {
                if(bPrint) System.out.printf("%s\t%s\n", resultSet.getString(1), resultSet.getString(2));
            }
        } catch (SQLException ex) {
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException | NullPointerException ex) {
                System.out.println("Ooops! No working database!");
            }
        }
        return false;
    }
    
    public int createUser(String username, String password, String lname, String fname, String type) {
        String query = "INSERT INTO `indproj`.`users` (`username`,`password`, `lname`, `fname`, `type`)"
                + "VALUES ('" + username + "', '" +  password + "', '" + lname + "', '" + fname + "', '" + type + "')";
        
        int result = -1;
        try {
            Connection connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            ResultSet rs  = statement.executeQuery("SELECT LAST_INSERT_ID();");
            if (rs.next()) {
                result = rs.getInt(1);
            } else {
                // throw an exception from here
            }
            System.out.println("Key returned :" +  result);

            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return result;
    }

    public int createMessage(String sender, String receiver, String date, String data) {
        String query = "INSERT INTO `indproj`.`messages` (`sender`,`receiver`, `date`, `data`)"
                + "VALUES ('" + sender + "', '" +  receiver + "', '" + date + "', '" + data + "')";
        
        int result = -1;
        try {
            Connection connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            ResultSet rs  = statement.executeQuery("SELECT LAST_INSERT_ID();");
            if (rs.next()) {
                result = rs.getInt(1);
            } else {
                // throw an exception from here
            }
         //   System.out.println("Key returned :" +  result);

            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return result;
    }


    public void getMessages(String username) throws IOException {
        String query = "SELECT * FROM messages WHERE sender = '" + username + "'";
        try {
            Connection connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            File file = new File("view.txt");
            
           
            while (rs.next()){
                int id = rs.getInt("id");
                String sender = rs.getString("sender");
                String receiver = rs.getString("receiver");
                String date = rs.getString("date");
                String data = rs.getString("data");

                System.out.format("%s, %s, %s, %s, %s\n", id, sender, receiver, date, data);
                
                
                FileWriter fstream = null;
                
                try {
                    fstream = new FileWriter(file);
                } catch (IOException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                BufferedWriter out = new BufferedWriter(fstream);
                
                out.write(rs.getInt(1)+" "+ rs.getString(2)+" "+ rs.getString(3)+" "+ rs.getString(4)+" "+rs.getString(5));
                
                
                  out.close();
                
            }
            
            
             
             
               
                
            
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void updateMessages(String username, String data){
        String query = "UPDATE messages SET data='" + data + "' WHERE sender ='" + username + "'";
        try {
            Connection connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    
    
    public void deleteMessages(String username){
        String query = "DELETE FROM `indproj`.`messages` WHERE sender ='" + username + "'";
        try {
            Connection connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    
    
    
    
    
    
    public void updateUser(String username, String password, String lname, String fname, String type){
        String query = "UPDATE users SET password='" + password + "', fname='" + fname + "', lname='" + lname
                + "', type='" + type + "' WHERE username ='" + username + "'";
        try {
            Connection connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void deleteUser(String username){
        String query = "DELETE FROM `indproj`.`users` WHERE username ='" + username + "'";
        try {
            Connection connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void getAllUser() {
        Main.userArrayList.clear();
        String query = "SELECT * FROM users";
        try {
            Connection connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()){
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFname(rs.getString("fname"));
                user.setLname(rs.getString("lname"));
                user.setType(rs.getString("type"));

                Main.userArrayList.add(user);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }    

public void getAllMessages() {
        Main.userArrayList.clear();
        String query = "SELECT * FROM messages";
        try {
            Connection connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()){
                Message message = new Message();

                message.setId(rs.getInt("id"));
                message.setReceiver(rs.getString("sender"));
                message.setReceiver(rs.getString("receiver"));
                message.setMessageData(rs.getString("data"));
                message.setDate(rs.getString("date"));
               

                Main.messageArrayList.add(message);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }    










}

