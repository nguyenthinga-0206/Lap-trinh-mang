/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.client.server.tcp.model;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import jdk.net.Sockets;

/*
 *
 * @author OS
 */

/*
Truyên a,b từ Client lên Server và trả kết quả từ Server về client
*/
public class ServerTCP {
    public static void main(String[] args) throws IOException{
        ServerSocket server = new ServerSocket(7788);
        System.out.println("Server is started");
        while(true){
            Socket socket = server.accept();
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            String time = new Date().toString();
            dos.writeUTF("Server tra lai ngay gio= "+time);
            socket.close();
        }
        
    }
}
