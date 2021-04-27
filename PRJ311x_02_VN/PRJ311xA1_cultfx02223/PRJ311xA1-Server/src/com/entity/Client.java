package com.entity;

import java.net.Socket;

public class Client {
    private String username;
    public Client(){

    }
    public Client(String username) {
        this.username = username;
    }
    private Socket socket;
    public String getUsername(){
        return  username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setSocket(Socket socket){
        this.socket = socket;
    }

    @Override
    public String toString() {
        return username;
    }

    public Socket getSocket(){
        return socket;
    }
}
