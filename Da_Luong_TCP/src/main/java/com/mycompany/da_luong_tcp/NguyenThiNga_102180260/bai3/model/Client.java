/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.da_luong_tcp.bai3.model;

/**
 *
 * @author OS
 */
import java.net.*;
import java.io.*;
public class Client {
  public static void main(String[] args) throws Exception {
  try{
    Socket socket=new Socket("127.0.0.1",8888);
    DataInputStream inStream=new DataInputStream(socket.getInputStream());
    DataOutputStream outStream=new DataOutputStream(socket.getOutputStream());
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    String clientMessage="",serverMessage="";
    while(!clientMessage.equals("bye")){
      System.out.println("Nhap tin nhan :");
      clientMessage=br.readLine();
      outStream.writeUTF(clientMessage);
      outStream.flush();
      serverMessage=inStream.readUTF();
      System.out.println("Tin nhan tu Server: "+serverMessage);
    }
    outStream.close();
    inStream.close();
    socket.close();
  }catch(Exception e){
    System.out.println(e);
  }
  }
}
