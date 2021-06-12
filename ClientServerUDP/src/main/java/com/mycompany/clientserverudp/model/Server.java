/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientserverudp.model;

/**
 *
 * @author OS
 */
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author OS
 */
// Chuong trinh tu server 
// 
public class Server {
    public static void main ( String args[]) throws Exception
    {
        // Gan cong 9876 cho chuong trinh 
        DatagramSocket serverSocket = new DatagramSocket(9876);
        System.out.println("Server is started!");
        byte[]sendData = new byte[1024];
        byte[]receiveData = new byte[1024];
       
        while(true){
            // Tao goi rong de nhan du lieu tu client
            DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
            //Nhan du lieu tu client 
            serverSocket.receive(receivePacket);
            // Lay dia chi Ip cua may client 
            InetAddress IPAddress = receivePacket.getAddress();
            // Lay port cua chuong trinh client 
            int port = receivePacket.getPort();
            // Lay ngay gio de gui nguoc lai client 
            String request = new String(receivePacket.getData());
            System.out.println(request);
            if(request.trim().equals("getDate"))
                sendData = new Date().toString().getBytes();
            else sendData = " Server not know?".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,IPAddress,port);
            // Gui du lieu lai client
            serverSocket.send(sendPacket);
        }
    }
}