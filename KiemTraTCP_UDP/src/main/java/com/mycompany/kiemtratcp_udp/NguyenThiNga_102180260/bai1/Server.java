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
  public String ChuyenNhiPhan(String a){
        int b = Integer.parseInt(a);
        String c = "", d = "";
        while(b!=0){
            c += b%2;
            b /= 2; 
        }
        for (int i = c.length()-1 ; i >= 0; i--){
            char s = c.charAt(i);
            d += (char)s;
        }
        return d;
    }
    public String KiemTra(String b){
        int count = 0;
        String str = "";
        for (int i = 0 ; i < b.length(); i--){
            char h = b.charAt(i);
            char k = b.charAt(b.length()-1-i);
            if(h != k){
                count ++;
            }
        }
        str += count;
        return str;           
    }
    public void run(){
      try{
        ServerClientThread serverThread = new ServerClientThread(serverClient, clientNo);
        DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
        DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
        String clientMessage="", serverMessage="";
        while(!clientMessage.equals("bye")){
          clientMessage=inStream.readUTF();
          System.out.println("So nguyen tu Client "+clientNo+" : "+clientMessage);
          squre  = serverThread.KiemTra(serverThread.ChuyenNhiPhan(clientMessage));
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

