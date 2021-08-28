package udp.fibonaci;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

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

    private int clientNo;
    private DatagramSocket ds;
    // Vùng đệm chứa dữ liệu cho gói tin
    byte[] sendData = new byte[1024];
    byte[] receiveData = new byte[1024];

    public ServerClientThread(DatagramSocket ds, int count) {
        this.ds = ds;
        this.clientNo = count;
    }

    public int fibonaci(String str) {
        int n = Integer.parseInt(str);
        int f = 0, f1 = 1, f2 = 1;
        if (n == 1) {
            return 1;
        }
        int i = 2;
        while (f <= n) {
            f = f1 + f2;
            f1 = f2;
            f2 = f;
            i++;
            if (f == n) {
                return i;
            }
        }
        return 0;
    }

    public void run() {
        try {
            ServerClientThread sct = new ServerClientThread(ds, clientNo);
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
                System.out.println("Nhan tu client " + clientNo + " la: " + str);
                str = str.trim();
                sendData = ("" + fibonaci(str)).getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                // Gui du lieu lai client
                ds.send(sendPacket);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            System.out.println("Client " + clientNo + " exit!! ");
        }
    }
}
