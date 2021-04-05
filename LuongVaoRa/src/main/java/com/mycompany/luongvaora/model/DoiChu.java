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
/*
Nhap vao 1 chuoi sau do thuc hien:  + doi nguoc(reverse)
                                    + In ra chu hoa,chu thuong,vua hoa vua thuong cua chuoi da cho
                                    + Dem so tu 
                                    + Dam cac nguyen am e,u,o,a,i
 */
class DoiChu {

    public String NhapChuoi() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        return s;
    }

    public void DaoNguoc(String str) {
        for (int i = str.length() - 1; i >= 0; i--) {
            char a = str.charAt(i);
            System.out.print(a);
        }
    }

    public void HoaThuong(String str) {
        for (int i = 0; i < str.length(); i++) {
            int s = str.charAt(i);
            if (s > 64 && s < 91) {
                s += 32;
            }
            System.out.print((char) s);
        }
    }

    public void ThuongHoa(String str) {
        for (int i = 0; i < str.length(); i++) {
            int s = str.charAt(i);
            if (s > 96 && s < 123) {
                s -= 32;
            }
            System.out.print((char) s);
        }
    }

    public void VuaHoaVuaThuong(String str) {
        for (int i = 0; i < str.length(); i++) {
            int s = str.charAt(i);
            if (s > 96 && s < 123) {
                s -= 32;
            } else if (s > 64 && s < 91) {
                s += 32;
            }
            System.out.print((char) s);
        }
    }

    public void WordCount(String str) {
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
        System.out.print(count);
    }

    public void VowelsCount(String str) {
        int count = 0;
        String ss = str.toLowerCase();
        for (int i = 0; i < ss.length(); i++) {
            char s = ss.charAt(i);
            if (s == 'u' || s == 'i' || s == 'a' || s == 'e' || s == 'o') {
                count++;
            }
        }
        System.out.print(count);
    }

    public static void main(String[] args) {
        String str = "";
        DoiChu dt = new DoiChu();
        try {
            System.out.println("Nhap mot chuoi: ");
            str = dt.NhapChuoi();
        } catch (Exception e) {
        }
        System.out.println("\nChuoi sau dao nguoc: ");
        dt.DaoNguoc(str);
        System.out.println("\nChuoi thuong: ");
        dt.HoaThuong(str);
        System.out.println("\nChuoi hoa: ");
        dt.ThuongHoa(str);
        System.out.println("\nChuoi vua hoa vua thuong: ");
        dt.VuaHoaVuaThuong(str);
        System.out.println("\nSo tu co trong chuoi: ");
        dt.WordCount(str);
        System.out.println("\nSo nguyen am co trong chuoi: ");
        dt.VowelsCount(str);
    }
}
