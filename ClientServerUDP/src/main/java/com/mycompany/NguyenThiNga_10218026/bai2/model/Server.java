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
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Stack;


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

    public int ThuTu(Character n) {
        if (n == '^' || n == '%') {
            return 3;
        }
        if (n == '*' || n == '/') {
            return 2;
        }
        if (n == '+' || n == '-') {
            return 1;
        }
        return 0;
    }

    public String HauTo(String n) {
        Stack<Character> Sh = new Stack<Character>();
        String kq = "";
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) >= '0' && n.charAt(i) <= '9') {
                kq += n.charAt(i);
            } else if (n.charAt(i) == '(') {
                Sh.push(n.charAt(i));
            } else if (n.charAt(i) == ')') {
                while (!Sh.empty() && Sh.peek() != '(') {
                    kq += Sh.peek();
                    Sh.pop();
                }
                Sh.pop();
            } else if (n.charAt(i) == '+' || n.charAt(i) == '-' || n.charAt(i) == '*' || n.charAt(i) == '/' || n.charAt(i) == '^' || n.charAt(i) == '%') {
                if (n.charAt(i - 1) >= '0' && n.charAt(i - 1) <= '9') {
                    kq += "#";
                }
                while (!Sh.empty() && ThuTu(Sh.peek()) >= ThuTu(n.charAt(i))) {
                    kq += Sh.peek();
                    Sh.pop();
                }
                Sh.push(n.charAt(i));
            }
        }

        while (!Sh.empty()) {
            if (Sh.peek() != '(') {
                kq += Sh.peek();
            }
            Sh.pop();
        }
        return kq;
    }

    public String Tinh(String n) {
        Stack<Double> Sh = new Stack<Double>();
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) == '+' || n.charAt(i) == '-' || n.charAt(i) == '*' || n.charAt(i) == '/' || n.charAt(i) == '^' || n.charAt(i) == '%') {
                double a = Sh.peek();
                Sh.pop();
                double b = Sh.peek();
                Sh.pop();
                double kq = 0;
                if (n.charAt(i) == '+') {
                    kq = a + b;
                } else if (n.charAt(i) == '-') {
                    kq = b - a;
                } else if (n.charAt(i) == '*') {
                    kq = a * b;
                } else if (n.charAt(i) == '/') {
                    kq = b / a;
                } else if (n.charAt(i) == '%') {
                    kq = b % a;
                } else if (n.charAt(i) == '^') {
                    kq = b;
                    for (int j = 0; j < (int) a - 1; j++) {
                        kq *= b;
                    }
                }
                Sh.push(kq);
            } else {
                double kq = 0;
                while (i < n.length() && n.charAt(i) >= '0' && n.charAt(i) <= '9') {
                    kq = 10 * kq + (double) (n.charAt(i) - '0');
                    i++;
                }
                if (n.length() != i && n.charAt(i) != '#') {
                    i--;
                }
                Sh.push(kq);
            }
        }
        return Sh.peek().toString();
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
                sendData = (sct.Tinh(sct.HauTo(str))).getBytes();
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
