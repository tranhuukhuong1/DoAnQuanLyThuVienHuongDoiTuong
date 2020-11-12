/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import quanlythuvien.main.Main;

/**
 *
 * @author Admin
 */
public class CTMuonTra implements ThuVien, Serializable {

    private static final long serialVersionUID = -6778530219900320801L;
    private int stt;
    private MuonTra muonTra;
    private Sach sach;
    private int tinhTrang;
    private int ghiChu;
    private Date ngayMuon;
    private Date ngayTra;

    public CTMuonTra() {
    }

    public CTMuonTra(MuonTra muonTra, Sach sach, int tinhTrang, int ghiChu, Date ngayMuon, Date ngayTra) {
        this.muonTra = muonTra;
        this.sach = sach;
        this.tinhTrang = tinhTrang;
        this.ghiChu = ghiChu;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public MuonTra getMuonTra() {
        return muonTra;
    }

    public void setMuonTra(MuonTra muonTra) {
        this.muonTra = muonTra;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(int ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    @Override
    public void nhap() {
    }

    @Override
    public void xuat() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String tinhTrang = "Chưa trả";
        if (this.tinhTrang != 0) {
            tinhTrang = "Đã trả";
        }
        String ngayTra = "";
        if (this.ngayTra == null) {
            ngayTra = "x";
        } else {
            ngayTra = sdf.format(this.ngayTra);
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-8s %-25s %-25s %-15s %-15s %-15s %-10s\n",
                stt, muonTra.getDocGia().getHoTen(),
                sach.getTenSach(),
                sdf.format(ngayMuon), ngayTra,
                tinhTrang, ghiChu);
    }
}
