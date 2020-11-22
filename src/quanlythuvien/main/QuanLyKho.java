/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.main;

import java.util.Scanner;
import quanlythuvien.model.KhoSach;
import quanlythuvien.service.CheckError;

/**
 *
 * @author Admin
 */
public class QuanLyKho {

    public static void Running() {
        boolean flag = true;
        while (flag) {
            System.out.println("===================================================");
            System.out.println("||                  QUẢN LÝ KHO SÁCH             ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   1   ||        Xuất thông tin                ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   2   ||        Sửa thông tin                 ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   3   ||        Thoát chức năng               ||");
            System.out.println("===================================================");
            System.out.print("Mời chọn cú pháp: ");
            int cp = CheckError.ChuoiThanhSo();
            switch (cp) {
                case 1:
                    xuatThongTin();
                    System.out.println("Xuất thành công! Enter để tiếp tục...");
                    new Scanner(System.in).nextLine();
                    break;
                case 2:
                    suaThongTin();
                    break;
                case 3:
                    flag = false;
                    break;
                default:
                    System.err.println("Sai cú pháp!");
                    System.out.println("Enter để tiếp tục...");
                    new Scanner(System.in).nextLine();
                    break;
            }
        }
    }

    private static void suaThongTin() {
        xuatThongTin();
        System.out.print("Nhập mã Sách để sửa: ");
        String maSach;
        do {
            maSach = CheckError.checkMa("MS-");
        } while (maSach.length() == 0);
        if (containsMa(maSach)) {
            System.out.print("Nhập số lượng sách: ");
            int soLuong;
            do {
                soLuong = CheckError.ChuoiThanhSo();
            } while (soLuong < 0);
            for (KhoSach ks : Main.dsk) {
                if (ks.getMaSach().equalsIgnoreCase(maSach)) {
                    ks.setSoLuong(soLuong);
                    break;
                }
            }
        } else {
            System.out.println("Mã không tồn tại!");
        }
    }

    private static void xuatThongTin() {
        System.out.println("============================================");
        System.out.printf("\t %-15s %-15s\n", "Mã sách", "Số lượng");
        for (KhoSach ks : Main.dsk) {
            ks.xuat();
        }
        System.out.println("============================================");
    }

    public static boolean containsMa(String maSach) {
        for (KhoSach ks : Main.dsk) {
            if (ks.getMaSach().equalsIgnoreCase(maSach)) {
                return true;
            }
        }
        return false;
    }
}
