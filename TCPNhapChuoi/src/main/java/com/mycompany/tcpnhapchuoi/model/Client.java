/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tcpnhapchuoi.model;

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
        Socket socket = new Socket("Localhost", 3333);
        DataInputStream in = new DataInputStream(socket.getInputStream()); //Nhận từ server về
        DataOutputStream out = new DataOutputStream(socket.getOutputStream()); // Gửi lên server
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // Nhập từ bàn phím
        System.out.println("Nhap chuoi tu ban phim:");
        String str = br.readLine();
        out.writeUTF(str);
        String str1 = in.readUTF();
        System.out.println(str1);
        socket.close();   
    }
}
