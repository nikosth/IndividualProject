package com.proj;

import java.util.ArrayList;


public class Main {
    
    static Database db;
    static ArrayList<User> userArrayList = new ArrayList<>();
    static ArrayList<Message> messageArrayList = new ArrayList<>();
    Main() {
        db = new Database();
    }
    
    public static void main(String[] args) {
        
        
       
        new Main();
        new Menu();
        
        
        
    }
    
}
