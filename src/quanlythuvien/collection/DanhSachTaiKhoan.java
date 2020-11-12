/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.collection;

import java.util.Scanner;
import quanlythuvien.model.TaiKhoan;

/**
 *
 * @author Admin
 */
public class DanhSachTaiKhoan {

    private TaiKhoan listTaiKhoan[];
    private int length;

    public DanhSachTaiKhoan() {
        listTaiKhoan = new TaiKhoan[100];
        length = 0;
    }

    public DanhSachTaiKhoan(TaiKhoan[] listTaiKhoan, int length) {
        this.listTaiKhoan = listTaiKhoan;
        this.length = length;
    }

    public TaiKhoan[] getListTaiKhoan() {
        return listTaiKhoan;
    }

    public void setListTaiKhoan(TaiKhoan[] listTaiKhoan) {
        this.listTaiKhoan = listTaiKhoan;
    }

    public int getLength() {
        resetLength();
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void them(Object o) {
        resetLength();
        TaiKhoan newTK = (TaiKhoan) o;
        if (contains(newTK.getTenDangNhap()) != -1) {
            System.err.println("Lỗi: Tài khoản đã có sẵn!");
            new Scanner(System.in).nextLine();
            return;
        }
        listTaiKhoan[length++] = newTK;
    }

    public int contains(String tenDangnhap) {
        resetLength();
        for (int i = 0; i < length; i++) {
            if (listTaiKhoan[i].getTenDangNhap().equalsIgnoreCase(tenDangnhap)) {
                return i;
            }
        }
        return -1;
    }

    private void resetLength() {
        if (length == 0) {
            while (listTaiKhoan[length] != null) {
                length++;
            }
        }
    }

}
