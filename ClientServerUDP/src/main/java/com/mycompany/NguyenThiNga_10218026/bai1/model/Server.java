/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NguyenThiNga_10218026.bai1.model;

/**
 *
 * @author OS
 */
import java.io.IOException;
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
    
    public String DaoNguoc(String str) {
        String a = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            char s = str.charAt(i);
            a += (char) s;
        }
        return a;
    }
    
    public String ThuongHoa(String m) {
        String a = "";
        for (int i = 0; i < m.length(); i++) {
            int s = m.charAt(i);
            if (s > 96 && s < 123) {
                s = s - 32;
            }
            a += (char) s;
        }
        return a;
    }
    
    public String HoaThuong(String str) {
        String a = "";
        for (int i = 0; i < str.length(); i++) {
            int s = str.charAt(i);
            if (s > 64 && s < 91) {
                s += 32;
            }
            a += (char) s;
        }
        return a;
    }
    
    public String VuaHoaVuaThuong(String str) {
        String a = "";
        for (int i = 0; i < str.length(); i++) {
            int s = str.charAt(i);
            if (s > 96 && s < 123) {
                s -= 32;
            } else if (s > 64 && s < 91) {
                s += 32;
            }
            a += (char) s;
        }
        return a;
    }
    
    public int WordCount(String str) {
        int count;
        if (str.charAt(0) != ' ') {
            count = 1;
        } else {
            count = 0;
        }
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == ' ' && str.charAt(i + 1) != ' ') {
                count++;
            }
        }        
        return count;
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

                String str = new String(receivePacket.getData());
                System.out.println(str);
                str = str.trim();
                sendData = ("Chuoi hoa: " + sct.ThuongHoa(str)
                        + "\nChuoi thuong: " + sct.HoaThuong(str)
                        + "\nChuoi vua hoa vua thuong: " + sct.VuaHoaVuaThuong(str)
                        + "\nSo ky tu trong chuoi: " + sct.WordCount(str)).getBytes();
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
