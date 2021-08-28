package udp.fibonaci;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public final static int SERVER_PORT = 7;

    public static void main(String args[]) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("127.0.0.1");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        int check;
        do {
            System.out.print("Nhap n: ");
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
            str = str.trim();
            check = Integer.parseInt(str);
            System.out.println("ket qua: " + check);
        } while (check <= 0);

        ds.close();
    }
}

