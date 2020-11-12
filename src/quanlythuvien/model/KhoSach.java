/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.model;

import java.io.Serializable;
import java.util.Scanner;
import quanlythuvien.main.Main;

/**
 *
 * @author Admin
 */
public class KhoSach implements ThuVien, Serializable {

    private static final long serialVersionUID = -6778530219900320801L;
    private String maSach;
    private int soLuong;

    public KhoSach(String maSach, int soLuong) {
        this.maSach = maSach;
        this.soLuong = soLuong;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public void nhap() {
        Main.dss.xuat();
        System.out.println("Nhập mã sách: ");
        String maSach;
        do {
            maSach = new Scanner(System.in).nextLine();
            if (Main.dss.contains(maSach) == -1) {
                System.out.println("Mã không tồn tại! Enter để tiếp tục...");
                new Scanner(System.in).nextLine();
            }
        } while (maSach.length() == 0 || Main.dss.contains(maSach) == -1);
        System.out.print("Nhập số lượng sách trong kho: ");
        int soLuong;
        do {
            soLuong = new Scanner(System.in).nextInt();
            if (soLuong < 0) {
                System.out.println("Không hợp lệ! Nhập lại!");
            }
        } while (soLuong < 0);
    }

    @Override
    public void xuat() {
        System.out.println("--------------------------------------------");
        System.out.printf("\t %-15s %-15s\n", maSach, soLuong);
    }

    
}
