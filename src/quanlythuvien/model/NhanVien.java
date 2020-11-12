package quanlythuvien.model;

import java.util.Scanner;
import quanlythuvien.main.QuanLyNhanVien;

public abstract class NhanVien extends Person{

    private static final long serialVersionUID = -6778530219900320801L;
    private String maNhanVien;

    public abstract void tinhLuong();

    public NhanVien() {
    }

    public NhanVien(String maNhanVien, String hoTen, String gioiTinh, int namSinh) {
        super(hoTen, gioiTinh, namSinh);
        this.maNhanVien = maNhanVien;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    @Override
    public void nhap() {
        super.nhap();
        System.out.print("Nhập mã Nhân viên: ");
        String maNhanVien;
        do {
            maNhanVien = new Scanner(System.in).nextLine();
            if (QuanLyNhanVien.contains(maNhanVien)) {
                System.out.println("Mã đã tồn tại! Nhập lại...");
            }
        } while (maNhanVien.length() < 0 || QuanLyNhanVien.contains(maNhanVien));
        this.maNhanVien = maNhanVien.toUpperCase();
    }
    public void nhap(String maNhanVien) {
        super.nhap();
        this.maNhanVien = maNhanVien.toUpperCase();
    }

    @Override
    public void xuat() {
    }

}
