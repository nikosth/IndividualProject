package com.proj;


import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Menu {

    private static User currentUser;

    public Menu(){
        Main.db.getAllUser();
        if (Main.userArrayList.size() == 0){
            Main.db.createUser("admin", "admin", "nikos", "thomos", "admin");
            Main.db.createUser("nthomo", "nthomo", "tom", "tomy", "user");
        }
        init();
    }
    
    
    
    
    
    
    private void init(){
        Scanner in = new Scanner(System.in);

       
        System.out.println("===== Welcome =====");
        System.out.println("PRESS:");
        System.out.println("1 --> LOG IN");
        System.out.println("2 --> RRGISTER");
        System.out.println("3 --> EXIT");
        int choice = in.nextInt();
        switch (choice){
            case 1:
                logInMenu(in);
                break;
            case 2:
                registerUserMenu(in);
                break;
            case 3:
                System.out.println("Exit");
                break;
        }
    }

    public void showMenu(Scanner in){
        
        System.out.println("===== MENU ====");
        System.out.println("PRESS: ");
        System.out.println("1 --> CREATE USER");
        System.out.println("2 --> DELETE USER");
        System.out.println("3 --> UPDATE USER");
        System.out.println("4 --> CHANGE USER");
        System.out.println("5 --> EXIT");
        int choice = in.nextInt();

        
        
        switch (choice){
            case 1:
                registerUserMenu(in);
                break;
            case 2:
                deleteUserMenu(in);
                break;
            case 3:
                updateUserMenu(in);
                break;
            case 4:
                logInMenu(in);
                break;
           case 5:
                System.out.println("Exit");
                break;
                
        }
    }
    public void showMenusimpleuser(Scanner in){
        System.out.println("===== MENU ====");
        System.out.println("1 --> SEND MESSAGE");
        System.out.println("2 --> CHANGE USER");
        System.out.println("3 --> EXIT");
        
        
        int choice = in.nextInt();
        switch (choice){
            case 1:
               createMessage(in);
               break;
           case 2:
                logInMenu(in);
                break;
            case 3:
                System.out.println("Exit");
                break;
        
    
       }
    
    }   
    
    public void showMenuUser(Scanner in){
        
        System.out.println("===== MENU ====");
        System.out.println("1 --> VIEW MESSAGE");
        System.out.println("2 --> EDIT MESSAGE");
        System.out.println("3 --> DELETE MESSAGE");
        System.out.println("4 --> SEND MESSAGE");
        System.out.println("5 --> CHANGE USER");
        System.out.println("6 --> EXIT");
       
       int choice = in.nextInt();

        
        
       switch (choice){
           case 1:
        {
            try {
                viewMessages(in);
            } catch (IOException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
               break;
           case 2:
               updatedata(in);
                break;
           case 3:
               deleteMessage(in);
                break;
            case 4:
                createMessage(in);
                break;
            case 5:
                logInMenu(in);
                break;
            case 6:
                System.out.println("Exit");
                break;
        }
    }

    public void logInMenu(Scanner in){
        int count = 0;
        
        System.out.println("===== LOG IN =====");
        System.out.println("ENTER USERNAME: ");
        String username = in.next();
        String password;
         
        User finduser = findUser(username);
      
        if (finduser == null){
            System.out.println("username not exists, register a new account.");
            registerUserMenu(in);
        }else{
            if (username.equals(finduser.getUsername())) {
                do {
                    System.out.println("ENTER PASSWORD: ");
                    password = in.next();
                    String role1="admin";
                    String role2="user";
                    String role3="smuser";
                    if (password.equals(finduser.getPassword()) && role1.equals(finduser.getType())){
                        currentUser = finduser;
                        System.out.println("Loged in");
                        showMenu(in);
                    }else if
                            (password.equals(finduser.getPassword()) && role2.equals(finduser.getType())){
                        currentUser = finduser;
                        System.out.println("Loged in");
                         showMenuUser(in);
                    }
                    else if
                        (password.equals(finduser.getPassword()) && role3.equals(finduser.getType())){
                        currentUser = finduser;
                        showMenusimpleuser(in);
                    }
                        
                    count = count + 1;
                }while(!password.equals(finduser.getPassword()) && count < 3);
                if (count > 2){
                    System.out.println("");
                    System.out.println("Log in failed...");
                }
            }
        }
    }

    public void registerUserMenu(Scanner in){
        
        System.out.println("===== SIGN IN =====");
        System.out.println("ENTER USERNAME: ");
        String username = in.next();
        System.out.println("ENTER PASSWORD: ");
        String password = in.next();
        System.out.println("EMTER FIRST NAME: ");
        String firstName = in.next();
        System.out.println("ENTER LAST NAME: ");
        String lastName = in.next();
        System.out.println("ENTER ROLE: ");
        String type = in.next();

        User user = new User(username, password, firstName, lastName, type);
        user.createUser(username, password, firstName, lastName, type);
        Main.db.getAllUser();
        logInMenu(in);
    }

    public void updateUserMenu(Scanner in){
        
        System.out.println("===== Update =====");
        System.out.println("ENTER USERNAME: ");
        String username = in.next();
        User finduser = findUser(username);
        if(finduser==null){
            
         System.out.println("WRONG USERNAME");
         showMenu(in);
        }else{
        System.out.println("ENTER NEW PASSWORD: ");
        String password = in.next();
        System.out.println("ENTER NEW FIRST NAME: ");
        String firstName = in.next();
        System.out.println("ENTER NEW LAST NAME: ");
        String lastName = in.next();
        System.out.println("ENTER NEW ROLE: ");
        String type = in.next();

        User user = new User(username, password, firstName, lastName, type);
        user.updateUser(username, password, firstName, lastName, type);
        Main.db.getAllUser();
        System.out.println("UPDATE OK");
        showMenu(in);
        }
    }

    public void deleteUserMenu(Scanner in){
        
        System.out.println("===== DELETE USER =====");
        System.out.println("ENTER USERNAME: ");
        String username = in.next();
        User finduser = findUser(username);
        if(finduser==null){
            
         System.out.println("WRONG USERNAME");
         showMenu(in);
        }else{
        Main.db.deleteUser(username);
        Main.db.getAllUser();
        System.out.println("Username " + username + " DELETED");
        showMenu(in);
        }
    }
    
    
    public void viewMessages(Scanner in) throws IOException{
         
        System.out.println("ENTER USERNAME: ");
        String username = in.next();
        User finduser = findUser(username);
        if(finduser==null){
            
         System.out.println("WRONG USERNAME");
         showMenuUser(in);
        }else {
            
         
                Main.db.getMessages(username);
            
             showMenuUser(in);
//        String role="user";
//        User finduser = findUser(username);
//        
//        if(role.equals(finduser.getType())){
//        currentUser = finduser;
        
      //  }else showMenusimpleuser(in);
        }
     }
    

    
    
    
    
    public void updatedata(Scanner in){
        System.out.println("ENTER USERNAME: ");
        String username = in.next();
        User finduser = findUser(username);
        
        if(finduser==null){
            
         System.out.println("WRONG USERNAME");
         showMenuUser(in);
        }else{
        
        System.out.println("CHANGE DATA");
        String data = in.next();
        Message message = new Message();
        message.updateMssage(username, data);
        Main.db.getAllMessages();
        showMenuUser(in);
        }
      }
    
    
    public void deleteMessage(Scanner in){
        
        System.out.println("==== DELETE MESSAGE ====");
        System.out.println("ENTER USERNAME: ");
        String username = in.next();
        User finduser = findUser(username);
        
        if(finduser==null){
            
         System.out.println("WRONG USERNAME");
         showMenuUser(in);
        }else{
        
        Main.db.deleteMessages(username);
        Main.db.getAllMessages();
        System.out.println("MESSAGE " + username + " DELETED");
        showMenuUser(in);
       }
    }
    public void createMessage(Scanner in){
    
        
        System.out.println("===== SEND MESSAGE =====");
        System.out.println("ENTER USERNAME YOU ARE LOGED IN");
        Main.db.getAllUser();
        String username = in.next();
        User finduser = findUser(username);
        if(finduser==null){
            
         System.out.println("WRONG USERNAME");
         showMenuUser(in);
        }else{
       
        String sender = finduser.getUsername();
        
        System.out.println("ENTER RECEIVER:");
        String receiver = in.next();
       
//        while(!receiver.equals(finduser.getFname())){
//            
//             System.out.println("RECEIVER DOES NOT EXIST");
//         
//               receiver = in.next();
//       }
        System.out.println("ENTER DATE:");
        String Data = in.next();
        System.out.println("ENTER DATA: ");
        String date = in.next();
        
        Message message = new Message(sender, receiver, Data, date);
        message.createMessage(sender,receiver,Data,date);
        Main.db.getAllMessages();
        
        showMenuUser(in);
        
       }
        
    } 
    
    
    private User findUser(String username){
        for (User person : Main.userArrayList){
            if (person.getUsername().equals(username)){
                return person;
            }
        }
        return null;
    }
//     private User findUser(String username){
//        for (User person : Main.userArrayList){
//            if (person.getUsername().equals(username)){
//                return person;
//            }
//        }
//        return null;
//    }
    
//    private Message findMessage(String receiver){
//        for (Message mnm : Main.messageArrayList){
//            if (mnm.getReceiver().equals(receiver)){
//                return mnm;
//            }
//        }
//        return null;
//    }
    
    
    
    
}
