/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.luongvaora.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author OS
 */
class ReadInt {

    public int NhapSo() throws IOException {
        InputStreamReader luongVao = new InputStreamReader(System.in);//cach 1
        BufferedReader br = new BufferedReader(luongVao);
//      BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //cach 2
        String s = br.readLine();
        return Integer.parseInt(s);
    }

    public void Tongtichcacchuso(int m)//m=215
    {
        int S = 0, P = 1;
        while (m != 0) {
            S += m % 10;
            P *= m % 10;
            m /= 10;
        }
        System.out.println("Tong cac chu so: " + S);
        System.out.println("Tich cac chu so: " + P);
    }

    public static void main(String argx[]) {
        ReadInt dt = new ReadInt();
        int m = 0;
        try {
            do {
                System.out.println("Nhap mot so nguyen duong: ");
                m += dt.NhapSo();
            } while (m <= 0);
        } catch (Exception e) {
        }
        System.out.println("So da nhap la: " + m);
        dt.Tongtichcacchuso(m);
    }
}
