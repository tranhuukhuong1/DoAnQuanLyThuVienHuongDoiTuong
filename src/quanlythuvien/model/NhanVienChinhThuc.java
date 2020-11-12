/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.model;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class NhanVienChinhThuc extends NhanVien {

    private static final long serialVersionUID = -6778530219900320801L;
    private int soNgayCong;

    public NhanVienChinhThuc() {
    }

    @Override
    public void tinhLuong() {
        System.out.print("Nhập số ngày công: ");
        int soNgayCong;
        do {
            soNgayCong = new Scanner(System.in).nextInt();
            if (soNgayCong <= 0) {
                System.err.println("Nhập lại!");
            }
        } while (soNgayCong <= 0);
        this.soNgayCong = soNgayCong;
        DecimalFormat dcf = new DecimalFormat("###,###,###");
        System.out.println("Lương tháng này của [" + getHoTen() + "]: "
                + dcf.format(soNgayCong * 180000) + " (VNĐ)");
    }

    public NhanVienChinhThuc(int soNgayCong, String maNhanVien, String hoTen, String gioiTinh, int namSinh) {
        super(maNhanVien, hoTen, gioiTinh, namSinh);
        this.soNgayCong = soNgayCong;
    }

    public int getSoNgayCong() {
        return soNgayCong;
    }

    public void setSoNgayCong(int soNgayCong) {
        this.soNgayCong = soNgayCong;
    }

    @Override
    public void nhap() {
        super.nhap();

    }

}
