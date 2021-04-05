/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bai1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author OS
 */
public class QLCB {

    public static void themCanBo(CanBo canbo) {
        Scanner sc = new Scanner(System.in);
        System.out.println("nhap ten: ");
        String name = sc.next();
        canbo.setTen(name);
        System.out.println(":nhap tuoi: ");
        int tuoi = sc.nextInt();
        canbo.setTuoi(tuoi);
        System.out.println("nhap gioi tinh: ");
        String gioiTinh = sc.next();
        canbo.setGioiTinh(gioiTinh);
        System.out.println("nhap dia chi: ");
        String diaChi = sc.next();
        canbo.setDiaChi(diaChi);

    }

    public static void main(String[] args) {
        List<CanBo> canbos = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String cb = sc.next();
        switch (cb) {
            case "nv":
                NhanVien nv = new NhanVien();
                themCanBo(nv);
                System.out.println("cong viec: ");
                String cv = sc.next();
                nv.setCongViec(cv);
                canbos.add(nv);
                break;
            case "ks":
                KiSu ks = new KiSu();
                themCanBo(ks);
                System.out.println("nganh dao tao: ");
                String ndt = sc.next();
                ks.setNganhDaoTao(ndt);
                canbos.add(ks);
                break;
            case "cn":
                CongNhan cn = new CongNhan();
                themCanBo(cn);
                System.out.println("nhap bac: ");
                int bac = sc.nextInt();
                cn.setBac(bac);
                canbos.add(cn);
                break;
            default:

        }
        for (int i = 0; i < canbos.size(); i++) {
            System.out.println("Ten: " + canbos.get(i).getTen());
            System.out.println("Tuoi: " + canbos.get(i).getTuoi());
            System.out.println("gioi tinh: " + canbos.get(i).getGioiTinh());
            System.out.println("dia chi: " + canbos.get(i).getDiaChi());
            if (canbos.get(i) instanceof NhanVien) {
                NhanVien cv = (NhanVien) canbos.get(i);
                System.out.println("cong viec: " +cv.getCongViec());

            }
            if(canbos.get(i) instanceof CongNhan){
                CongNhan bac = (CongNhan) canbos.get(i);
                System.out.println("bac: "+bac.getBac());
            }
            else if (canbos.get(i) instanceof KiSu){
                KiSu ndt = (KiSu) canbos.get(i);
                System.out.println("nhanh dao tao: "+ ndt.getNganhDaoTao());
            }
        }
    }
}
