/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kiemtratcp_udp.NguyenThiNga_102180260.bai2;

/**
 *
 * @author OS
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
//2.Chuong trinh client server giao thức UDP voi cac chuc nang nhu sau:
//Client liên tục gửi đến server 1 so nguyen duong k bat ki. 
//Server ktra neu k thuộc dãy fibonaci thi yeu cau client ngung gui va chi ra so fibonaci so mấy.
// 
public class Server {
    public static void main ( String args[]) throws Exception
    {
        // Gan cong 9876 cho chuong trinh 
        DatagramSocket serverSocket = new DatagramSocket(9876);
        System.out.println("Server is started!");
        byte[]sendData = new byte[1024];
        byte[]receiveData = new byte[1024];
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
       
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