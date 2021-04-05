/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tcpnhapchuoi.model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public String DaoNguoc(String str) {
        String a = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            char s = str.charAt(i);
            a+=(char)s;
        }
        return a;
    }
    
    public String ThuongHoa(String m){
        String a = "";
        for (int i = 0; i < m.length(); i++) {
            int s = m.charAt(i);
            if (s > 96 && s < 123) {
                s = s - 32;
            }
            a+=(char)s;
        }
        return a;
    } 

    public String HoaThuong(String str) {
        String a = "";
        for (int i = 0; i < str.length(); i++) {
            int s = str.charAt(i);
            if (s > 64 && s < 91) {
                s += 32;
            }
            a+=(char)s;
        }
        return a;
    }

    public String VuaHoaVuaThuong(String str) {
        String a = "";
        for (int i = 0; i < str.length(); i++) {
            int s = str.charAt(i);
            if (s > 96 && s < 123) {
                s -= 32;
            } else if (s > 64 && s < 91) {
                s += 32;
            }
            a+=(char)s;
        }
        return a;
    }
    public int WordCount(String str) {
        int count;
        if (str.charAt(0) != ' ') {
            count = 1;
        } else {
            count = 0;
        }
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == ' ' && str.charAt(i + 1) != ' ') {
                count++;
            }
        }
        return count;
    }

    public int VowelsCount(String str) {
        int count = 0;
        String ss = str.toLowerCase();
        for (int i = 0; i < ss.length(); i++) {
            char s = ss.charAt(i);
            if (s == 'u' || s == 'i' || s == 'a' || s == 'e' || s == 'o') {
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) throws  Exception
    {
        ServerSocket ss = new ServerSocket(3333);
        Socket s= ss.accept();
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new  DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        String str = din.readUTF();
        System.out.print("Chuoi tu client \n: " + str);
        Server sv = new Server();
        dos.writeUTF("Chuoi dao nguoc: "+sv.DaoNguoc(str)
                    +"\nChuoi hoa: "+sv.ThuongHoa(str)
                    +"\nChuoi thuong: "+sv.HoaThuong(str)
                    +"\nChuoi vua hoa vua thuong: "+sv.VuaHoaVuaThuong(str)
                    +"\nSo nguyen am: "+sv.VowelsCount(str)
                    +"\nSo tu trong chuoi: "+sv.WordCount(str));
    }
}