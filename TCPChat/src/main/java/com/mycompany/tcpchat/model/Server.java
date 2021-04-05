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
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author OS
 */
public class Server {
    public static void main(String[] args) throws IOException{
        ServerSocket server = new ServerSocket(8888);
        System.out.println("Server is started");
        
        Socket socket = server.accept();
        DataInputStream in = new DataInputStream(socket.getInputStream()); //Nhận từ client về
        DataOutputStream out = new DataOutputStream(socket.getOutputStream()); // Gửi lên client
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // Nhập từ bàn phím
        String chat1="";String chat2="";
        while(!chat1.equals("stop")){
            chat1 = in.readUTF();
            System.out.println(chat1);
            System.out.println("Nhap tin nhan tra loi:");
            chat2 = br.readLine();
            out.writeUTF("Server gui: "+chat2);
            
        }
        socket.close();
    }
}
