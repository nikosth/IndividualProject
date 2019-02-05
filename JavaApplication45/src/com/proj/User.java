package com.proj;


public class User {
    private int id;
    private String username;
    private String password;
    private String fname;
    private String lname;
    private String type;

    public User(){
        
    }
    
    public User(String username, String password, String fname, String lname, String type) {
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public User createUser(String username, String password, String fname, String lname, String type) {
        User user = new User(username, password, fname, lname, type);
        setId(Main.db.createUser(username, password, fname, lname, type));
        return user;
    }

    public void readAllUser(Database db){
        db.getAllUser();
    }

    public void updateUser(String username, String password, String lname, String fname, String type){
        Main.db.updateUser(username, password, lname, fname, type);
    }

    public void deleteUser(String username){
        Main.db.deleteUser(username);
    }   
}
