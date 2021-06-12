/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.da_luong_tcp.NguyenThiNga_102180260.bai3.model;

/**
 *
 * @author OS
 */
import java.net.*;
import java.io.*;
public class Server {
  public static void main(String[] args) throws Exception {
    try{
      ServerSocket server=new ServerSocket(8888);
      int counter=0;
      System.out.println("Server Started ....");
      while(true){
        counter++;
        Socket serverClient=server.accept();  //server accept the client connection request
        ServerClientThread sct = new ServerClientThread(serverClient,counter); //send  the request to a separate thread
        sct.start();
      }
    }catch(Exception e){
      System.out.println(e);
    }
  }
}
class ServerClientThread extends Thread {
    Socket serverClient;
    int clientNo;
    String squre;
    ServerClientThread(Socket inSocket,int counter){
      serverClient = inSocket;
      clientNo=counter;

    }
  
    public void run(){
      try{
        DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
        DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String clientMessage="", serverMessage="";
        while(!clientMessage.equals("bye")){
          clientMessage=inStream.readUTF();
          System.out.println("Tin nhan tu Client "+clientNo+" : "+clientMessage);
          System.out.println("Nhap tin nhan :");
          squre=br.readLine();
          outStream.writeUTF(squre);
          outStream.flush();
        }
        inStream.close();
        outStream.close();
        serverClient.close();
      }catch(Exception ex){
        System.out.println(ex);
      }finally{
        System.out.println("Client -" + clientNo + " exit!! ");
      }
    }
}

