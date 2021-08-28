package tcp.fibonaci;

import java.net.*;
import java.io.*;

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

    public int fibonaci(int n) {
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
            ServerClientThread serverThread = new ServerClientThread(serverClient, clientNo);
            DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
            DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
            String clientMessage = "", serverMessage = "";
            while (!clientMessage.equals("bye")) {
                clientMessage = inStream.readUTF();
                System.out.println("So nhan duoc tu client " + clientNo + " la : " + clientMessage);
//                squre = "So Fibonacci cua <" + clientMessage + "> = " + fibonaci(Integer.parseInt(clientMessage));
                outStream.writeInt(fibonaci(Integer.parseInt(clientMessage)));
                outStream.flush();
            }
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
