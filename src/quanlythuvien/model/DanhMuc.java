/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.model;

import java.io.Serializable;
import java.util.Scanner;
import quanlythuvien.main.Main;
import quanlythuvien.service.CheckError;
import static quanlythuvien.service.ChuanHoaChuoi.chuanHoa;

/**
 *
 * @author Admin
 */
public class DanhMuc implements ThuVien, Serializable {

    private static final long serialVersionUID = -6778530219900320801L;
    private String maDanhMuc;
    private String tenDanhMuc;

    public DanhMuc() {
    }

    public DanhMuc(String maDanhMuc, String tenDanhMuc) {
        this.maDanhMuc = maDanhMuc.toUpperCase();
        this.tenDanhMuc = chuanHoa(tenDanhMuc);
    }

    public String getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(String maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    @Override
    public void nhap() {
        String maDanhMuc, tenDanhMuc;
        System.out.println("======================================");
        System.out.println("\tThêm thông tin Danh mục:");
        System.out.print("Nhập mã Danh mục: ");
        do {
            maDanhMuc = CheckError.checkMa("DM-");
            if (Main.dsdm.contains(maDanhMuc) != -1) {
                System.out.println("Mã bị trùng! Nhập lại...");
            }
        } while (maDanhMuc.length() == 0 || (Main.dsdm.contains(maDanhMuc) != -1));
        System.out.print("Nhập tên Danh mục: ");
        do {
            tenDanhMuc = new Scanner(System.in).nextLine();
        } while (tenDanhMuc.length() == 0);
        System.out.println("======================================");
        this.maDanhMuc = maDanhMuc.toUpperCase();
        this.tenDanhMuc = chuanHoa(tenDanhMuc);
    }
    
    public void nhap(String maDanhMuc) {
        String tenDanhMuc;
        System.out.println("\tThêm thông tin Danh mục:");
        System.out.print("Nhập tên Danh mục: ");
        do {
            tenDanhMuc = new Scanner(System.in).nextLine();
        } while (tenDanhMuc.length() == 0);
        System.out.println("======================================");
        this.maDanhMuc = maDanhMuc.toUpperCase();
        this.tenDanhMuc = chuanHoa(tenDanhMuc);
    }

    @Override
    public void xuat() {
        System.out.printf("\t%-15s %-15s\n", maDanhMuc, tenDanhMuc);
        System.out.println("--------------------------------------------");
    }
}
