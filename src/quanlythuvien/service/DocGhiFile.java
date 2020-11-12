/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.service;

import java.util.ArrayList;
import quanlythuvien.io.DocGhiSerializeFile;
import static quanlythuvien.main.Main.*;
import quanlythuvien.model.CTMuonTra;
import quanlythuvien.model.DanhMuc;
import quanlythuvien.model.DocGia;
import quanlythuvien.model.KhoSach;
import quanlythuvien.model.MuonTra;
import quanlythuvien.model.NgonNgu;
import quanlythuvien.model.NhaXuatBan;
import quanlythuvien.model.NhanVien;
import quanlythuvien.model.Sach;
import quanlythuvien.model.TacGia;
import quanlythuvien.model.TaiKhoan;

/**
 *
 * @author Admin
 */
public class DocGhiFile {

    public static void docFile() {
        dsdm.setListDanhMuc((DanhMuc[]) DocGhiSerializeFile.docFile("database/listDanhMuc.dat"));
        dstk.setListTaiKhoan((TaiKhoan[]) DocGhiSerializeFile.docFile("database/listTaiKhoan.dat"));
        dsnn.setListNgonNgu((NgonNgu[]) DocGhiSerializeFile.docFile("database/listNgonNgu.dat"));
        dsnxb.setListNXB((NhaXuatBan[]) DocGhiSerializeFile.docFile("database/listNXB.dat"));
        dss.setListSach((Sach[]) DocGhiSerializeFile.docFile("database/listSach.dat"));
        dstg.setListTacGia((TacGia[]) DocGhiSerializeFile.docFile("database/listTacGia.dat"));
        dsdg.setListDocGia((DocGia[]) DocGhiSerializeFile.docFile("database/listDocGia.dat"));
        dsnv = (ArrayList<NhanVien>) DocGhiSerializeFile.docFile("database/listNhanVien.dat");
        dsmt = (ArrayList<MuonTra>) DocGhiSerializeFile.docFile("database/listMuonTra.dat");
        dsk = (ArrayList<KhoSach>) DocGhiSerializeFile.docFile("database/listKhoSach.dat");
        dsctmt = (ArrayList<CTMuonTra>) DocGhiSerializeFile.docFile("database/listCTMuonTra.dat");
    }

    public static void ghiFile() {
        DocGhiSerializeFile.ghiFile(dsdm.getListDanhMuc(), "database/listDanhMuc.dat");
        DocGhiSerializeFile.ghiFile(dstk.getListTaiKhoan(), "database/listTaiKhoan.dat");
        DocGhiSerializeFile.ghiFile(dsnn.getListNgonNgu(), "database/listNgonNgu.dat");
        DocGhiSerializeFile.ghiFile(dsnxb.getListNXB(), "database/listNXB.dat");
        DocGhiSerializeFile.ghiFile(dss.getListSach(), "database/listSach.dat");
        DocGhiSerializeFile.ghiFile(dstg.getListTacGia(), "database/listTacGia.dat");
        DocGhiSerializeFile.ghiFile(dsdg.getListDocGia(), "database/listDocGia.dat");
        DocGhiSerializeFile.ghiFile(dsnv, "database/listNhanVien.dat");
        DocGhiSerializeFile.ghiFile(dsmt, "database/listMuonTra.dat");
        DocGhiSerializeFile.ghiFile(dsk, "database/listKhoSach.dat");
        DocGhiSerializeFile.ghiFile(dsctmt, "database/listCTMuonTra.dat");
    }
}
