/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NguyenThiNga_10218026.bai2.model;

/**
 *
 * @author OS
 */
import com.mycompany.clientserverudp.model.*;
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
public class Client {

    public final static int SERVER_PORT = 7;

    public static void main(String args[]) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("127.0.0.1");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        
        while (true) {

            System.out.println("Nhap phep tinh: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            sendData = br.readLine().getBytes();
            // tao datagram co noi dung yeu cau loai du lieu de gui cho server
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, SERVER_PORT);
            ds.send(sendPacket); // gui du lieu cho server
            // tao datagram rong de nhan du lieu
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            // nhan du lieu tu server
            ds.receive(receivePacket);
            String str = new String(receivePacket.getData());
            System.out.println("Ket qua tra ve tu server: " + str);
            ds.close();
        }
    }
}
