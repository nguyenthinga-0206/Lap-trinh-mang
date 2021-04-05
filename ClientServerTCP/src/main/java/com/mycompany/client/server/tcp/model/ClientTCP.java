/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.client.server.tcp.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author OS
 */
/*
1. Viết chương trình Client kết nối Server để lấy về ngày, tháng, năm, giờ, phút, giây... theo ICP
2. Viết chương trình chat giữa Client và Server theo TCP
3. Viết chương trình trao đổi thông tin giữa Client/Server theo TCP. Chương trình Client gửi chuỗi bất kì lên Server.
Chương trình server tra về:
    + Chuõi đảo
    + Chuổi hoa
    + Chuỗi thường
    + Dem từ và nguyen âm

*/
public class ClientTCP {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("Localhost", 7788);
        DataInputStream din = new DataInputStream(socket.getInputStream());
        String time = din.readUTF();
        System.out.println(time);
        socket.close();
    }
}
