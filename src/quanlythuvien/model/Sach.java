/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.model;

import java.io.Serializable;
import java.util.Scanner;
import static quanlythuvien.main.Main.*;
import quanlythuvien.service.CheckError;
import quanlythuvien.service.ChuanHoaChuoi;

/**
 *
 * @author Admin
 */
public class Sach implements ThuVien, Serializable {

    private static final long serialVersionUID = -6778530219900320801L;
    private String maDanhMuc;
    private String maSach;
    private String tenSach;
    private String maTacGia;
    private String maNXB;
    private int namXuatBan;
    private String maNgonNgu;

    public Sach() {
    }

    public Sach(String maDanhMuc, String maSach, String tenSach, String maTacGia, String maNXB, int namXuatBan, String maNgonNgu) {
        this.maDanhMuc = maDanhMuc;
        this.maSach = maSach.toUpperCase();
        this.tenSach = ChuanHoaChuoi.chuanHoa(tenSach);
        this.maTacGia = maTacGia;
        this.maNXB = maNXB;
        this.namXuatBan = namXuatBan;
        this.maNgonNgu = maNgonNgu;
    }

    public String getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(String maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(String maTacGia) {
        this.maTacGia = maTacGia;
    }

    public String getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(String maNXB) {
        this.maNXB = maNXB;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public String getMaNgonNgu() {
        return maNgonNgu;
    }

    public void setMaNgonNgu(String maNgonNgu) {
        this.maNgonNgu = maNgonNgu;
    }

    @Override
    public void nhap() {
        System.out.println("======================================");
        System.out.println("\tThêm thông tin sách:");
        dsdm.xuat();
        String maDanhMuc, maSach, maTacGia, maNXB, maNgonNgu;
        System.out.print("Nhập mã Danh mục của sách (DM-[stt]): ");
        do {
            maDanhMuc = CheckError.checkMa("DM-");
        } while (maDanhMuc.length() == 0);
        if (dsdm.contains(maDanhMuc) == -1) {
            System.out.println("======================================");
            System.out.println("Danh mục chưa tồn tại! Thực hiện thêm mới");
            DanhMuc dm = new DanhMuc();
            dm.nhap(maDanhMuc);
            dsdm.them(dm);
        }
        System.out.println("=======Tiếp tục nhập thông tin sách=======");
        int n = dss.getLength() - 1;
        System.out.println("Sách mới thêm gần đây: "
                + dss.getListSach()[n].getMaSach() + " | " + dss.getListSach()[n].getTenSach());
        System.out.print("Nhập mã sách (MS-[stt]): ");
        do {
            maSach = CheckError.checkMa("MS-");
            if (dss.contains(maSach) != -1) {
                System.out.println("Mã sách bị trùng!");
            }
        } while (maSach.length() == 0 || dss.contains(maSach) != -1);
        System.out.print("Nhập tên sách: ");
        do {
            tenSach = new Scanner(System.in).nextLine();
        } while (tenSach.length() == 0);

        dstg.xuat();
        System.out.print("Nhập mã tác giả: ");
        do {
            maTacGia = CheckError.checkMa("TG-");
        } while (maTacGia.length() == 0);
        if (dstg.contains(maTacGia) == -1) {
            System.out.println("======================================");
            System.out.println("Tác giả chưa có thông tin! Thực hiện thêm thông tin:");
            TacGia tg = new TacGia();
            tg.nhap(maTacGia);
            dstg.them(tg);
        }
        dsnxb.xuat();
        System.out.print("Nhập mã NXB: ");
        do {
            maNXB = CheckError.checkMa("NXB-");
        } while (maNXB.length() == 0);
        if (dsnxb.contains(maNXB.toUpperCase()) == -1) {
            System.out.println("======================================");
            System.out.println("NXB chưa tồn tại! Thực hiện thêm mới");
            NhaXuatBan nxb = new NhaXuatBan();
            nxb.nhap(maNXB);
            dsnxb.them(nxb);
        }
        System.out.println("=======Tiếp tục nhập thông tin sách=======");
        System.out.print("Nhập năm xuất bản: ");
        do {
            namXuatBan = CheckError.ChuoiThanhSo();
        } while (namXuatBan > 2020);
        dsnn.xuat();
        System.out.print("Nhập mã ngôn ngữ: ");
        do {
            maNgonNgu = new Scanner(System.in).nextLine();
        } while (maNgonNgu.length() == 0);
        if (dsnn.contains(maNgonNgu) == -1) {
            System.out.println("Ngôn ngữ chưa tồn tại! Thực hiện thêm mới");
            NgonNgu nn = new NgonNgu();
            nn.nhap(maNgonNgu);
            dsnn.them(nn);
        }
        this.maDanhMuc = maDanhMuc.toUpperCase();
        this.maNXB = maNXB.toUpperCase();
        this.maNgonNgu = maNgonNgu.toUpperCase();
        this.maSach = maSach.toUpperCase();
        this.maTacGia = maTacGia.toUpperCase();
        this.namXuatBan = namXuatBan;
        this.tenSach = ChuanHoaChuoi.chuanHoa(tenSach);
        System.out.print("Nhập số lượng sách: ");
        int soLuong;
        do {
            soLuong = CheckError.ChuoiThanhSo();
            if (soLuong < 0) {
                System.out.println("Không hợp lệ! Nhập lại!");
            }
        } while (soLuong < 0);
        dsk.add(new KhoSach(maSach.toUpperCase(), soLuong));
    }

    public void nhap(String maSach) {
        System.out.println("======================================");
        System.out.println("\tThêm thông tin sách:");
        dsdm.xuat();
        String maDanhMuc, maTacGia, maNXB, maNgonNgu;
        System.out.print("Nhập mã Danh mục của sách: ");
        do {
            maDanhMuc = CheckError.checkMa("DM-");
        } while (maDanhMuc.length() == 0);
        if (dsdm.contains(maDanhMuc) == -1) {
            System.out.println("======================================");
            System.out.println("Danh mục chưa tồn tại! Thực hiện thêm mới");
            DanhMuc dm = new DanhMuc();
            dm.nhap(maDanhMuc);
            dsdm.them(dm);
        }
        System.out.println("=======Tiếp tục nhập thông tin sách=======");
        System.out.print("Nhập tên sách: ");
        do {
            tenSach = new Scanner(System.in).nextLine();
        } while (tenSach.length() == 0);

        dstg.xuat();
        System.out.print("Nhập mã tác giả: ");
        do {
            maTacGia = CheckError.checkMa("TG-");
        } while (maTacGia.length() == 0);
        if (dstg.contains(maTacGia) == -1) {
            System.out.println("======================================");
            System.out.println("Tác giả chưa có thông tin! Thực hiện thêm thông tin:");
            TacGia tg = new TacGia();
            tg.nhap(maTacGia);
            dstg.them(tg);
        }
        dsnxb.xuat();
        System.out.print("Nhập mã NXB: ");
        do {
            maNXB = CheckError.checkMa("NXB-");
        } while (maNXB.length() == 0);
        if (dsnxb.contains(maNXB.toUpperCase()) == -1) {
            System.out.println("======================================");
            System.out.println("NXB chưa tồn tại! Thực hiện thêm mới");
            NhaXuatBan nxb = new NhaXuatBan();
            nxb.nhap(maNXB);
            dsnxb.them(nxb);
        }
        System.out.println("=======Tiếp tục nhập thông tin sách=======");
        System.out.print("Nhập năm xuất bản: ");
        do {
            namXuatBan = CheckError.ChuoiThanhSo();
        } while (namXuatBan > 2020 || namXuatBan < 0);
        dsnn.xuat();
        System.out.print("Nhập mã ngôn ngữ: ");
        do {
            maNgonNgu = new Scanner(System.in).nextLine();
        } while (maNgonNgu.length() == 0);
        if (dsnn.contains(maNgonNgu) == -1) {
            System.out.println("Ngôn ngữ chưa tồn tại! Thực hiện thêm mới");
            NgonNgu nn = new NgonNgu();
            nn.nhap(maNgonNgu);
            dsnn.them(nn);
        }
        this.maDanhMuc = maDanhMuc.toUpperCase();
        this.maNXB = maNXB.toUpperCase();
        this.maNgonNgu = maNgonNgu.toUpperCase();
        this.maSach = maSach.toUpperCase();
        this.maTacGia = maTacGia.toUpperCase();
        this.namXuatBan = namXuatBan;
        this.tenSach = ChuanHoaChuoi.chuanHoa(tenSach);
    }

    @Override
    public void xuat() {
        System.out.printf("%-10s %-10s %-30s %-15s %-10s %-7s %-10s\n",
                maSach, maDanhMuc, tenSach, maTacGia, maNXB, namXuatBan, "   " + maNgonNgu);
        System.out.println("--------------------------------------------------------------------------------------------------");
    }
}
