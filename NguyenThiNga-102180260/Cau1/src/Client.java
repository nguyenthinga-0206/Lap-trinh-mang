import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

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
            String a, b, c;
            double kq;

            System.out.println("Nhap a :");
            a = br.readLine();
            outStream.writeUTF(a);
            System.out.println("Nhap b :");
            b = br.readLine();
            outStream.writeUTF(b);
            System.out.println("Nhap phep tinh :");
            c = br.readLine();
            outStream.writeUTF(c);
            outStream.flush();
            kq = inStream.readDouble();
            System.out.println("Ket qua cua phep tinh: " + kq);

            outStream.close();
            inStream.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
