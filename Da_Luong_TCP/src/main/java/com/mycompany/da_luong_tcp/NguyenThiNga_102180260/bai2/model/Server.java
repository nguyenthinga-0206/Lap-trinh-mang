/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.da_luong_tcp.NguyenThiNga_102180260.bai2.model;

/**
 *
 * @author OS
 */
import java.net.*;
import java.io.*;
import java.util.Stack;
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
    public static int ThuTu(Character n) {
		if(n == '^' || n == '%') return 3;
		if(n == '*' || n == '/') return 2;
		if(n == '+' || n == '-') return 1;
		return 0;
    }

    public static String HauTo(String n) {
        Stack<Character> Sh = new Stack<Character>() ;
        String kq = "";
        for(int i = 0 ; i < n.length(); i++) {
            if(n.charAt(i) >= '0' && n.charAt(i) <= '9') kq += n.charAt(i);	
            else if(n.charAt(i) == '(' ) Sh.push(n.charAt(i));
            else if(n.charAt(i) == ')') {
                while(!Sh.empty()  && Sh.peek() != '(') {
                        kq += Sh.peek();
                        Sh.pop();
                }
                Sh.pop();
            }
            else if(n.charAt(i) == '+' || n.charAt(i) == '-' || n.charAt(i) == '*' ||n.charAt(i) == '/' ||n.charAt(i) == '^' ||n.charAt(i) =='%' ) {
                if(n.charAt(i-1) >= '0' && n.charAt(i-1) <= '9') kq += "#";	
                while(!Sh.empty() && ThuTu(Sh.peek()) >= ThuTu(n.charAt(i))) {
                        kq += Sh.peek();
                        Sh.pop();
                }
                Sh.push(n.charAt(i));
            }
        }

        while(!Sh.empty()) {
            if(Sh.peek() != '(') kq += Sh.peek();
            Sh.pop();
        }
        return kq;
    }
    public static String Tinh(String n) {
        Stack<Double> Sh = new Stack<Double>() ;
        for(int i = 0 ; i < n.length() ; i ++) {
            if(n.charAt(i) == '+' || n.charAt(i) == '-' || n.charAt(i) == '*' ||n.charAt(i) == '/' ||n.charAt(i) == '^' ||n.charAt(i) == '%') {
                double  a = Sh.peek();  Sh.pop();
                double  b = Sh.peek();  Sh.pop();
                double kq = 0;
                if(n.charAt(i) == '+') kq = a + b;
                else if(n.charAt(i) == '-') kq = b - a;
                else if(n.charAt(i) == '*') kq = a * b;
                else if(n.charAt(i) == '/') kq = b / a;
                else if(n.charAt(i) == '%') kq = b % a;
                else if(n.charAt(i) == '^') {
                    kq = b;
                    for(int j = 0 ; j < (int)a-1; j++) {
                        kq *= b;
                    }
                }
                Sh.push(kq);
            }
            else{
                double kq = 0;
                while( i < n.length() && n.charAt(i) >= '0' && n.charAt(i) <= '9' ) {
                    kq = 10 * kq + (double)(n.charAt(i)-'0');
                    i++;
                }	
                if( n.length() != i && n.charAt(i) != '#') i--;
                Sh.push(kq);
            }
        }
        return Sh.peek().toString();
    }
    public void run(){
      try{
        ServerClientThread serverThread = new ServerClientThread(serverClient, clientNo);
        DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
        DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
        String clientMessage="", serverMessage="";
        while(!clientMessage.equals("bye")){
          clientMessage=inStream.readUTF();
          System.out.println("Chuoi tu Client "+clientNo+" : "+clientMessage);
          squre = serverThread.Tinh(serverThread.HauTo(clientMessage));
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

