package com.proj;

import java.io.IOException;


public class Message {
    private int id;
    private String sender;
    private String receiver;
    private String messageData;
    private String date;
   
    public Message(){
       
    }

    public Message(String sender, String receiver, String messageData, String date) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.messageData = messageData;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessageData() {
        return messageData;
    }

    public void setMessageData(String messageData) {
        this.messageData = messageData;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
   
     public Message createMessage(String sender, String receiver, String Data, String date) {
        Message message = new Message(sender, receiver, messageData, date);
        setId(Main.db.createMessage(sender,receiver, messageData,date));
        return message;
    }

    
    public void updateMssage(String username,String Data){
        Main.db.updateMessages(username, Data);
    }   
    
    public void deleteMessage(String username){
        Main.db.deleteMessages(username);
    } 
    
    
    public void getMessages(Database db) throws IOException{
        db.getAllMessages();
    }
    
   
   
}
