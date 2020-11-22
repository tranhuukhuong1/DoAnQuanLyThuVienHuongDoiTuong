/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.model;

import java.util.Scanner;
import quanlythuvien.main.Main;
import quanlythuvien.service.CheckError;

/**
 *
 * @author Admin
 */
public class TacGia extends Person {

    private String maTacGia;

    public TacGia() {
    }

    public TacGia(String maTacGia) {
        this.maTacGia = maTacGia;
    }

    public String getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(String maTacGia) {
        this.maTacGia = maTacGia;
    }

    public TacGia(String maTacGia, String hoTen, String gioiTinh, int namSinh) {
        super(hoTen, gioiTinh, namSinh);
        this.maTacGia = maTacGia;
    }

    @Override
    public void nhap() {
        super.nhap();
        String maTG;
        System.out.print("Nhập mã Tác giả: ");
        do {
            maTG = CheckError.checkMa("TG-");
            if (Main.dstg.contains(maTG) != -1) {
                System.out.println("Mã bị trùng! Nhập lại:");
            }
        } while (maTG.length() == 0 || Main.dstg.contains(maTG) != -1);
        this.maTacGia = maTG.toUpperCase();
    }

    public void nhap(String maTG) {
        super.nhap();
        this.maTacGia = maTG.toUpperCase();
    }

    @Override
    public void xuat() {
        System.out.printf("\t%-15s %-20s %-10s\n", maTacGia, getHoTen(), getNamSinh());
        System.out.println("------------------------------------------------------------");
    }
}
