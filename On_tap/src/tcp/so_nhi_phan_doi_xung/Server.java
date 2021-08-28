package tcp.so_nhi_phan_doi_xung;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Server {
    public static void main(String[] args) throws Exception {
        try {
            ServerSocket server = new ServerSocket(8888);
            int counter = 0;
            System.out.println("Server Started ....");
            while (true) {
                counter++;
                Socket serverClient = server.accept();  //server accept the client connection request
                Thread sct = new Thread(serverClient, counter); //send  the request to a separate thread
                sct.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class Thread extends java.lang.Thread {
    Socket serverClient;
    int clientNo;
    String squre;

    Thread(Socket inSocket, int counter) {
        serverClient = inSocket;
        clientNo = counter;

    }

    public String binaryNumber(int n) {
        Stack<Integer> stack = new Stack<>();
        while (n != 0) {
            stack.push(n % 2);
            n /= 2;
        }
        String m = "";
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            m += stack.pop();
        }
        return m;
    }

    public boolean symmetricalNumber(String m) {
        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new LinkedList<>();

        for (int i = 0; i < m.length(); i++) {
            stack.push(m.charAt(i));
            queue.add(m.charAt(i));
        }

        int size = stack.size();
        for (int i = 0; i < size; i++) {
            if (stack.pop() != queue.remove()) {
                return false;
            }
        }
        return true;
    }

    public void run() {
        try {
            Thread serverThread = new Thread(serverClient, clientNo);
            DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
            DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
            String clientMessage = "";
            while (!clientMessage.equals("bye")) {
                clientMessage = inStream.readUTF();
                System.out.println("So nhan duoc tu client " + clientNo + " la : " + clientMessage);
                squre = "Ket qua la: " + binaryNumber(Integer.parseInt(clientMessage));
                outStream.writeUTF(squre);
                outStream.writeBoolean(symmetricalNumber(binaryNumber(Integer.parseInt(clientMessage))));
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

