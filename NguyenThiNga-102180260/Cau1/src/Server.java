import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        try {
            ServerSocket server = new ServerSocket(8888);
            int counter = 0;
            System.out.println("Server Started ....");
            while (true) {
                counter++;
                Socket serverClient = server.accept();
                ServerClientThread sct = new ServerClientThread(serverClient, counter);
                sct.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class ServerClientThread extends Thread {
    Socket serverClient;
    int clientNo;
    String squre;

    ServerClientThread(Socket inSocket, int counter) {
        serverClient = inSocket;
        clientNo = counter;

    }

    public double ketQua(double a, double b, char c) {
        switch (c) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            default:
                return 0;
        }
    }

    public void run() {
        try {
            ServerClientThread serverThread = new ServerClientThread(serverClient, clientNo);
            DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
            DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
            String a = "", b = "", c = "";

            a = inStream.readUTF();
            b = inStream.readUTF();
            c = inStream.readUTF();
            System.out.println("Nhan duoc tu client " + clientNo + " la : " + a + " " + c + " " + b);
            outStream.writeDouble(ketQua(Float.parseFloat(a), Float.parseFloat(b), c.charAt(0)));
            outStream.flush();

            inStream.close();

            outStream.close();
            serverClient.close();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            System.out.println("Client -" + clientNo + " exit!! ");
        }
    }
}
