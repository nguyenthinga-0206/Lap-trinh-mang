package tcp.fibonaci;

import java.net.*;
import java.io.*;

/*
So fibonaci thu N
 */
public class Client {
    public static void main(String[] args) throws Exception {
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            DataInputStream inStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String clientMessage = "";
            int check;
            do {
                System.out.println("Nhap n :");
                clientMessage = br.readLine();
                outStream.writeUTF(clientMessage);
                outStream.flush();
                check = inStream.readInt();
                System.out.println("Thuoc day fibonaci thu " + check);
            } while (check <= 0);
            outStream.close();
            inStream.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
