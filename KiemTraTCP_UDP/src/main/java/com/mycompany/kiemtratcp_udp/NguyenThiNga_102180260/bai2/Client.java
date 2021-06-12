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
//Client liên tục gửi đến server 1 so nguyen duong k bat ki. Server ktra neu k thuộc dãy fibonaci thi yeu cau client ngung gui va chi ra so fibonaci so mấy.
// 
public class Client {
    public static void main ( String args[]) throws Exception
    {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[]sendData = new byte[1024];
        byte[]receiveData = new byte[1024];
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        sendData = br.readLine().getBytes();
        // tao datagram co noi dung yeu cau loai du lieu de gui cho server
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,IPAddress,9876);
       
        clientSocket.send(sendPacket); // gui du lieu cho server
        // tao datagram rong de nhan du lieu
         DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        // nhan du lieu tu server
        clientSocket.receive(receivePacket);
        String str = new String(receivePacket.getData());
        System.out.println(str);
        clientSocket.close();
    }
}
