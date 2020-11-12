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
public class NgonNgu implements ThuVien, Serializable {

    private static final long serialVersionUID = -6778530219900320801L;
    private String maNgonNgu;
    private String tenNgonNgu;

    public NgonNgu() {
    }

    public NgonNgu(String maNgonNgu, String tenNgonNgu) {
        this.maNgonNgu = maNgonNgu.toUpperCase();
        this.tenNgonNgu = chuanHoa(tenNgonNgu);
    }

    public String getMaNgonNgu() {
        return maNgonNgu;
    }

    public void setMaNgonNgu(String maNgonNgu) {
        this.maNgonNgu = maNgonNgu;
    }

    public String getTenNgonNgu() {
        return tenNgonNgu;
    }

    public void setTenNgonNgu(String tenNgonNgu) {
        this.tenNgonNgu = tenNgonNgu;
    }

    @Override
    public void nhap() {
        String maNgonNgu, tenNgonNgu;
        System.out.println("======================================");
        System.out.println("\tThêm thông tin Ngôn ngữ:");
        System.out.print("Nhập mã Ngôn ngữ: ");
        do {
            maNgonNgu = new Scanner(System.in).nextLine();
            if (Main.dsnn.contains(maNgonNgu) != -1) {
                System.out.println("Mã bị trùng!");
            }
        } while (maNgonNgu.length() == 0 && Main.dsnn.contains(maNgonNgu) != -1);
        System.out.print("Nhập tên Ngôn ngữ: ");
        do {
            tenNgonNgu = new Scanner(System.in).nextLine();
        } while (tenNgonNgu.length() == 0);
        System.out.println("======================================");
        this.maNgonNgu = maNgonNgu.toUpperCase();
        this.tenNgonNgu = chuanHoa(tenNgonNgu);
    }

    public void nhap(String maNgonNgu) {
        String tenNgonNgu;
        System.out.println("======================================");
        System.out.print("Nhập tên Ngôn ngữ: ");
        do {
            tenNgonNgu = new Scanner(System.in).nextLine();
        } while (tenNgonNgu.length() == 0);
        System.out.println("======================================");
        this.maNgonNgu = maNgonNgu.toUpperCase();
        this.tenNgonNgu = chuanHoa(tenNgonNgu);
    }

    @Override
    public void xuat() {
        System.out.printf("\t%-15s %-15s\n", maNgonNgu, tenNgonNgu);
        System.out.println("--------------------------------------------");
    }
}
