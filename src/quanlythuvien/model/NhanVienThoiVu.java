/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.model;

import java.text.DecimalFormat;
import quanlythuvien.service.CheckError;

/**
 *
 * @author Admin
 */
public class NhanVienThoiVu extends NhanVien {

    private static final long serialVersionUID = -6778530219900320801L;
    private int soGioLam;

    public NhanVienThoiVu() {
    }

    @Override
    public void tinhLuong() {
        System.out.print("Nhập số giờ làm: ");
        int soGioLam;
        do {
            soGioLam = CheckError.ChuoiThanhSo();
            if (soGioLam <= 0) {
                System.err.println("Nhập lại!");
            }
        } while (soGioLam <= 0);
        this.soGioLam = soGioLam;
        DecimalFormat dcf = new DecimalFormat("###,###,###");
        System.out.println("Lương tháng này của [" + getHoTen() + "]: "
                + dcf.format(soGioLam * 16000) + " (VNĐ)");
    }

    public NhanVienThoiVu(int soGioLam, String maNhanVien, String hoTen, String gioiTinh, int namSinh) {
        super(maNhanVien, hoTen, gioiTinh, namSinh);
        this.soGioLam = soGioLam;
    }

    public int getSoGioLam() {
        return soGioLam;
    }

    public void setSoGioLam(int soGioLam) {
        this.soGioLam = soGioLam;
    }

    @Override
    public void nhap() {
        super.nhap();
    }
}
