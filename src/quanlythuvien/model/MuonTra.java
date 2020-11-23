/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.model;

import java.io.Serializable;
import quanlythuvien.main.Main;
import quanlythuvien.service.CheckError;

/**
 *
 * @author Admin
 */
public class MuonTra implements ThuVien, Serializable {

    private static final long serialVersionUID = -6778530219900320801L;
    private String maMuonTra;
    private DocGia DocGia;

    public MuonTra(String maMuonTra, DocGia DocGia) {
        this.maMuonTra = maMuonTra.toUpperCase();
        this.DocGia = DocGia;
    }

    public MuonTra() {
    }

    public String getMaMuonTra() {
        return maMuonTra;
    }

    public void setMaMuonTra(String maMuonTra) {
        this.maMuonTra = maMuonTra;
    }

    public DocGia getDocGia() {
        return DocGia;
    }

    public void setDocGia(DocGia DocGia) {
        this.DocGia = DocGia;
    }

    @Override
    public void nhap() {
        System.out.print("Nhập mã thẻ mượn (MT-(stt): ");
        String maMuonTra;
        do {
            maMuonTra = CheckError.checkMa("MT-");
            if (isContains(maMuonTra)) {
                System.out.println("Mã đã tồn tại! Nhập lại!");
            }
        } while (maMuonTra.length() == 0 || isContains(maMuonTra));
        this.maMuonTra = maMuonTra.toUpperCase();
    }

    @Override
    public void xuat() {
        System.out.println("-----------------------------------------------");
        System.out.printf("\t%-15s %-25s\n", maMuonTra, DocGia.getHoTen());
    }

    private boolean isContains(String maMuonTra) {
        for (MuonTra mt : Main.dsmt) {
            if (mt.getMaMuonTra().equalsIgnoreCase(maMuonTra)) {
                return true;
            }
        }
        return false;
    }

}
