/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.model;

import java.io.Serializable;
import java.util.Scanner;
import quanlythuvien.main.Main;
import static quanlythuvien.service.ChuanHoaChuoi.chuanHoa;

/**
 *
 * @author Admin
 */
public class NhaXuatBan implements ThuVien, Serializable {
    
    private static final long serialVersionUID = -3520461358471476861L;
    private String maNXB;
    private String tenNXB;
    private String diaChi;
    
    public NhaXuatBan() {
    }
    
    public NhaXuatBan(String maNXB, String tenNXB, String diaChi) {
        this.maNXB = maNXB.toUpperCase();
        this.tenNXB = chuanHoa(tenNXB);
        this.diaChi = chuanHoa(diaChi);
    }
    
    public String getMaNXB() {
        return maNXB;
    }
    
    public void setMaNXB(String maNXB) {
        this.maNXB = maNXB;
    }
    
    public String getTenNXB() {
        return tenNXB;
    }
    
    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }
    
    public String getDiaChi() {
        return diaChi;
    }
    
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    @Override
    public void nhap() {
        String maNXB, tenNXB, diaChi;
        System.out.println("======================================");
        System.out.println("\tThêm thông tin NXB:");
        System.out.print("Nhập mã NXB: ");
        do {
            maNXB = new Scanner(System.in).nextLine();
            if (Main.dsnxb.contains(maNXB) != -1) {
                System.out.println("Đã tồn tại NXB!");
            }
        } while (maNXB.length() == 0 || Main.dsnxb.contains(maNXB) != -1);
        System.out.print("Nhập tên NXB: ");
        do {
            tenNXB = new Scanner(System.in).nextLine();
        } while (tenNXB.length() == 0);
        System.out.print("Nhập địa chỉ NXB: ");
        do {
            diaChi = new Scanner(System.in).nextLine();
        } while (diaChi.length() == 0);
        System.out.println("======================================");
        this.maNXB = maNXB.toUpperCase();
        this.tenNXB = chuanHoa(tenNXB);
        this.diaChi = chuanHoa(diaChi);
    }
    
    public void nhap(String maNXB) {
        String tenNXB, diaChi;
        System.out.println("======================================");
        System.out.print("Nhập tên NXB: ");
        do {
            tenNXB = new Scanner(System.in).nextLine();
        } while (tenNXB.length() == 0);
        System.out.print("Nhập địa chỉ NXB: ");
        do {
            diaChi = new Scanner(System.in).nextLine();
        } while (diaChi.length() == 0);
        System.out.println("======================================");
        this.maNXB = maNXB.toUpperCase();
        this.tenNXB = chuanHoa(tenNXB);
        this.diaChi = chuanHoa(diaChi);
    }
    
    @Override
    public void xuat() {
        System.out.printf("\t%-15s %-15s\n", maNXB, tenNXB);
        System.out.println("--------------------------------------------");
    }
    
}