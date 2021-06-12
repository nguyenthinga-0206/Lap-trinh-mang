/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kiemtratcp_udp.NguyenThiNga_102180260.bai1;

/**
 *
 * @author OS
 */
//1.Chuong trinh client server giao thức TCP voi cac chuc nang nhu sau:
//Client liên tục gửi đến server 1 so nguyen duong k bat ki. Server ktra neu bieu dien nhi phan cua so do doi xung thi client ngừng gửi.

import java.net.*;
import java.io.*;
public class Client {
  public static void main(String[] args) throws Exception {
  try{
    Socket socket=new Socket("127.0.0.1",8888);
    DataInputStream inStream=new DataInputStream(socket.getInputStream());
    DataOutputStream outStream=new DataOutputStream(socket.getOutputStream());
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    String clientMessage="", serverMessage = "";
    System.out.println("Nhap so nguyen duong :");
    clientMessage=br.readLine();
    outStream.writeUTF(clientMessage);
    outStream.flush();      
    serverMessage=inStream.readUTF();
    System.out.println(serverMessage);
    
    while(!serverMessage.equals("0")){   
        System.out.println("Tiep tuc :");
        clientMessage=br.readLine();
        outStream.writeUTF(clientMessage);
        outStream.flush();      
        serverMessage=inStream.readUTF();
        System.out.println(serverMessage);
    }
    outStream.close();
    inStream.close();
    socket.close();
  }catch(Exception e){
    System.out.println(e);
  }
  }
}
