import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Server {

    public final static int SERVER_PORT = 7;

    public static void main(String[] args) {
        int count = 7; // Cổng mặc định của Echo Server
        DatagramSocket ds = null;
        try {

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

    public int kiemTra(String str) {
        Stack stack = new Stack();
        Queue queue = new LinkedList();
        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
            queue.add(str.charAt(i));
        }
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            if (stack.pop()!=queue.remove()){
                return 0;
            }
        }
        return 1;
    }

    public void run() {
        try {
            ServerClientThread sct = new ServerClientThread(ds, clientNo);
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                ds.receive(receivePacket);
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();

                String str = new String(receivePacket.getData());
                System.out.println("Chuoi nhan tu client " + clientNo + " la: " + str);
                str = str.trim();
                sendData = ("" + kiemTra(str)).getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

                ds.send(sendPacket);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            System.out.println("Client " + clientNo + " exit!! ");
        }
    }
}
