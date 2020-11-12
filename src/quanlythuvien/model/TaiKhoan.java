/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.model;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class TaiKhoan implements ThuVien, Serializable {

    private static final long serialVersionUID = -6778530219900320801L;
    private String tenDangNhap;
    private String matKhau;
    private int loaiTaiKhoan;

    public TaiKhoan() {
    }

    public TaiKhoan(String tenDangNhap, String matKhau, int loaiTaiKhoan) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getLoaiTaiKhoan() {
        return loaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(int loaiTaiKhoan) {
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    @Override
    public void nhap() {
    }

    @Override
    public void xuat() {
    }
}
