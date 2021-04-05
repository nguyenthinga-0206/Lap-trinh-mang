/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tcpchat.model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author OS
 */
public class Client {
    public static void main(String[] args) throws IOException{
        Socket socket = new Socket("Localhost", 8888);
        
        DataInputStream in = new DataInputStream(socket.getInputStream()); //Nhận từ server về
        DataOutputStream out = new DataOutputStream(socket.getOutputStream()); // Gửi lên server
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // Nhập từ bàn phím
        String chat1="";String chat2="";
        while(!chat1.equals("stop")){
            System.out.println("Nhap tin nhan tu ban phim:");
            chat1 = br.readLine();
            out.writeUTF("Client gui: "+chat1);
            chat2 = in.readUTF();
            System.out.println(chat2);
             
        } 
        socket.close();
    }
}
