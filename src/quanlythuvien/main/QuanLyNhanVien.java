/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.main;

import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import static quanlythuvien.main.Main.dsnv;
import quanlythuvien.model.NhanVien;
import quanlythuvien.model.NhanVienChinhThuc;
import quanlythuvien.model.NhanVienThoiVu;
import quanlythuvien.service.CheckError;
import quanlythuvien.service.DocGhiFile;

/**
 *
 * @author Admin
 */
public class QuanLyNhanVien {

    public static void Running() {
        boolean flag = true;
        while (flag) {
            System.out.println("===================================================");
            System.out.println("||                 QUẢN LÝ NHÂN VIÊN             ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   1   ||        Thêm Nhân viên                ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   2   ||        Sửa thông tin Nhân viên       ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   3   ||        Xuất danh sách                ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   4   ||        Tìm kiếm (lọc)                ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   5   ||        Xoá Nhân viên                 ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   6   ||        Tính lương Nhân viên          ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   7   ||        Thoát chức năng               ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("===================================================");
            System.out.print("Mời chọn chức năng: ");
            int cp = CheckError.ChuoiThanhSo();
            switch (cp) {
                case 1:
                    themNhanVien();
                    DocGhiFile.ghiFile();
                    break;
                case 2:
                    suaThongTin();
                    DocGhiFile.ghiFile();
                    break;
                case 3:
                    xuatDanhSach();
                    System.out.println("Xuất danh sách thành công! Enter để tiếp tục...");
                    new Scanner(System.in).nextLine();
                    break;
                case 4:
                    timKiem();
                    break;
                case 5:
                    xoaNhanVien();
                    DocGhiFile.ghiFile();
                    break;
                case 6:
                    tinhLuong();
                    System.out.println("Enter để tiếp tục...");
                    new Scanner(System.in).nextLine();
                    break;
                case 7:
                    DocGhiFile.ghiFile();
                    flag = false;
                    break;
                default:
                    System.err.println("Sai cú pháp!");
                    System.out.println("Enter để tiếp tục...");
                    new Scanner(System.in).nextLine();
                    break;
            }
        }
    }

    public static boolean contains(String maNhanVien) {
        for (NhanVien nv : dsnv) {
            if (nv.getMaNhanVien().equalsIgnoreCase(maNhanVien)) {
                return true;
            }
        }
        return false;
    }

    private static void themNhanVien() {
        xuatDanhSach();
        System.out.println("===================================================");
        System.out.println("||                  THÊM NHÂN VIÊN               ||");
        System.out.println("||-----------------------------------------------||");
        System.out.println("||   1   ||        Nhân viên chính thức          ||");
        System.out.println("||-----------------------------------------------||");
        System.out.println("||   2   ||        Nhân viên thời vụ             ||");
        System.out.println("===================================================");
        System.out.print("Mời chọn chức năng: ");
        int cp = CheckError.ChuoiThanhSo();
        switch (cp) {
            case 1:
                NhanVien nvct = new NhanVienChinhThuc();
                nvct.nhap();
                dsnv.add(nvct);
                break;
            case 2:
                NhanVien nvtv = new NhanVienThoiVu();
                nvtv.nhap();
                dsnv.add(nvtv);
                break;
            default:
                System.err.println("Sai cú pháp!");
                System.out.println("Enter để tiếp tục...");
                break;
        }
    }

    private static void xuatDanhSach() {
        sapXep();
        System.out.println("====================================================================");
        System.out.printf("%-15s %-25s %-15s %-12s\n",
                "Mã NV", "Họ tên", "Giới tính", "Năm sinh");
        System.out.println("--------------------------------------------------------------------");
        for (NhanVien nv : dsnv) {
            System.out.printf("%-15s %-25s %-15s %-12s\n",
                    nv.getMaNhanVien(), nv.getHoTen(),
                    nv.getGioiTinh(), nv.getNamSinh());
            System.out.println("--------------------------------------------------------------------");
        }
        System.out.println();
    }

    private static void suaThongTin() {
        xuatDanhSach();
        System.out.print("Nhập mã của Nhân viên cần sửa: ");
        String maNhanVien;
        do {
            maNhanVien = CheckError.checkMa("NV-");
        } while (maNhanVien.length() < 0);
        if (contains(maNhanVien)) {
            for (NhanVien nv : dsnv) {
                if (nv.getMaNhanVien().equalsIgnoreCase(maNhanVien)) {
                    System.out.println("Nhập thông tin mới");
                    nv.nhap(maNhanVien);
                    System.out.println("Sửa thành công!Enter để tiếp tục...");
                    new Scanner(System.in).nextLine();
                    return;
                }
            }
        }
        System.out.print("Mã không tồn tại! Enter để tiếp tục...");
        new Scanner(System.in).nextLine();
    }

    private static void timKiem() {
        System.out.println("============================================");
        System.out.println("||           TÌM KIẾM NHÂN VIÊN           ||");
        System.out.println("============================================");
        System.out.println("||  1  ||   Lọc nhân viên thời vụ         ||");
        System.out.println("||  2  ||   Lọc nhân viên chính thức      ||");
        System.out.println("||  3  ||   Tìm theo tên                  ||");
        System.out.println("============================================");
        System.out.print("Mời chọn chức năng: ");
        int cp = CheckError.ChuoiThanhSo();
        switch (cp) {
            case 1:
                xuatThoiVu();
                break;
            case 2:
                xuatChinhThuc();
                break;
            case 3:
                timTheoTen();
                break;
            default:
                System.err.println("Sai cú pháp!");
                System.out.print("Enter để tiếp tục...");
                new Scanner(System.in).nextLine();
                break;
        }
    }

    private static void xuatThoiVu() {
        sapXep();
        System.out.println("===================================================================================");
        System.out.printf("%-15s %-25s %-15s %-12s %-15s\n",
                "Mã NV", "Họ tên", "Giới tính", "Năm sinh", "Số giờ làm");
        System.out.println("-----------------------------------------------------------------------------------");
        for (NhanVien nv : dsnv) {
            if (nv instanceof NhanVienThoiVu) {
                System.out.printf("%-15s %-25s %-15s %-12s %-15s\n",
                        nv.getMaNhanVien(), nv.getHoTen(),
                        nv.getGioiTinh(), nv.getNamSinh(), ((NhanVienThoiVu) nv).getSoGioLam());

                System.out.println("-----------------------------------------------------------------------------------");
            }
        }
        System.out.print("Xuất thành công! Enter để tiếp tục...");
        new Scanner(System.in).nextLine();
    }

    private static void xuatChinhThuc() {
        sapXep();
        System.out.println("===================================================================================");
        System.out.printf("%-15s %-25s %-15s %-12s %-15s\n",
                "Mã NV", "Họ tên", "Giới tính", "Năm sinh", "Số ngày công");
        System.out.println("-----------------------------------------------------------------------------------");
        for (NhanVien nv : dsnv) {
            if (nv instanceof NhanVienChinhThuc) {
                System.out.printf("%-15s %-25s %-15s %-12s %-15s\n",
                        nv.getMaNhanVien(), nv.getHoTen(),
                        nv.getGioiTinh(), nv.getNamSinh(), ((NhanVienChinhThuc) nv).getSoNgayCong());

                System.out.println("-----------------------------------------------------------------------------------");
            }
        }
        System.out.print("Xuất thành công! Enter để tiếp tục...");
        new Scanner(System.in).nextLine();
    }

    private static void timTheoTen() {
        sapXep();
        System.out.print("Nhập từ khoá: ");
        String key;
        do {
            key = new Scanner(System.in).nextLine();
        } while (key.length() < 0);
        System.out.println("====================================================================");
        System.out.printf("%-15s %-25s %-15s %-12s\n",
                "Mã NV", "Họ tên", "Giới tính", "Năm sinh");
        System.out.println("--------------------------------------------------------------------");
        for (NhanVien nv : dsnv) {
            if (nv.getHoTen().toLowerCase().contains(key.toLowerCase())) {
                System.out.printf("%-15s %-25s %-15s %-12s\n",
                        nv.getMaNhanVien(), nv.getHoTen(),
                        nv.getGioiTinh(), nv.getNamSinh());
                System.out.println("--------------------------------------------------------------------");
            }
        }
        System.out.print("Xuất thành công! Enter để tiếp tục...");
        new Scanner(System.in).nextLine();
    }

    private static void xoaNhanVien() {
        xuatDanhSach();
        System.out.print("Nhập mã Nhân viên muốn xoá: ");
        String maNhanVien;
        do {
            maNhanVien = CheckError.checkMa("NV-");
        } while (maNhanVien.length() < 0);
        if (contains(maNhanVien)) {
            for (NhanVien nv : dsnv) {
                if (nv.getMaNhanVien().equalsIgnoreCase(maNhanVien)) {
                    dsnv.remove(nv);
                    System.out.println("Sửa thành công!Enter để tiếp tục...");
                    new Scanner(System.in).nextLine();
                    return;
                }
            }
        }
        System.out.print("Mã không tồn tại! Enter để tiếp tục...");
        new Scanner(System.in).nextLine();
    }

    private static void tinhLuong() {
        xuatDanhSach();
        System.out.print("Nhập mã Nhân viên để tính lương: ");
        String maNhanVien;
        do {
            maNhanVien = CheckError.checkMa("NV-");
        } while (maNhanVien.length() < 0);
        if (contains(maNhanVien)) {
            for (NhanVien nv : dsnv) {
                if (nv.getMaNhanVien().equalsIgnoreCase(maNhanVien)) {
                    nv.tinhLuong();
                    return;
                }
            }
        }
        System.out.print("Mã không tồn tại! Enter để tiếp tục...");
        new Scanner(System.in).nextLine();
    }

    private static void sapXep() {
        Collections.sort(dsnv, new Comparator<NhanVien>() {
            @Override
            public int compare(NhanVien o1, NhanVien o2) {
                return o1.getMaNhanVien().compareTo(o2.getMaNhanVien());
            }
        });
    }
}
