/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NguyenThiNga_10218026.bai3.model;

/**
 *
 * @author OS
 */
import com.mycompany.clientserverudp.model.*;
import java.io.BufferedReader;
import java.io.IOException;
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
// Chuong trinh tu server 
// 
public class Server {

    public final static int SERVER_PORT = 7;

    public static void main(String[] args) {
        int count = 7; // Cổng mặc định của Echo Server
        DatagramSocket ds = null;
        try {
//            System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
            ds = new DatagramSocket(SERVER_PORT); // Tạo Socket với cổng
            System.out.println("Server started ");
            System.out.println("Waiting for messages from Client ... ");
            while (true) { // Tạo gói tin nhận
                count++;
                ServerClientThread thread = new ServerClientThread(ds, count);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ds != null) {
                ds.close();
            }
        }
    }
}

class ServerClientThread extends Thread {

    private int count;
    private DatagramSocket ds;
    // Vùng đệm chứa dữ liệu cho gói tin
    byte[] sendData = new byte[1024];
    byte[] receiveData = new byte[1024];

    public ServerClientThread(DatagramSocket ds, int count) {
        this.ds = ds;
        this.count = count;
    }

    public void run() {
        try {
            ServerClientThread sct = new ServerClientThread(ds, count);
            while (true) {
                // Tao goi rong de nhan du lieu tu client
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                //Nhan du lieu tu client 
                ds.receive(receivePacket);
                // Lay dia chi Ip cua may client 
                InetAddress IPAddress = receivePacket.getAddress();
//             Lay port cua chuong trinh client 
                int port = receivePacket.getPort();

//                Nhap tin nhan
                System.out.println("Nhap tin nhan: ");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                sendData = br.readLine().getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                // Gui du lieu lai client
                ds.send(sendPacket);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            System.out.println("Client " + count + " exit!! ");
        }
    }
}
