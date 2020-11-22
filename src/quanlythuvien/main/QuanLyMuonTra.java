/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import quanlythuvien.model.CTMuonTra;
import quanlythuvien.model.DocGia;
import quanlythuvien.model.KhoSach;
import quanlythuvien.model.MuonTra;
import quanlythuvien.model.Sach;
import quanlythuvien.service.CheckError;

/**
 *
 * @author Admin
 */
public class QuanLyMuonTra {

    public static void Running() {
        boolean flag = true;
        while (flag) {
            System.out.println("===================================================");
            System.out.println("||                 QUẢN LÝ MƯỢN - TRẢ            ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   1   ||        Thực hiện cho mượn            ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   2   ||        Thực hiện trả sách            ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   3   ||        Xuất thông tin mượn trả       ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   4   ||        Thoát chức năng               ||");
            System.out.println("===================================================");
            System.out.print("Mời chọn chức năng: ");
            int cp = CheckError.ChuoiThanhSo();
            switch (cp) {
                case 1:
                    thucHienMuonSach();
                    break;
                case 2:
                    thucHienTraSach();
                    break;
                case 3:
                    xuatThongTin();
                    System.out.println("Xuất thành công! Enter để tiếp tục...");
                    new Scanner(System.in).nextLine();
                    break;
                case 4:
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

    private static void thucHienTraSach() {
        xuatThongTin();
        System.out.print("Chọn STT: ");
        int stt;
        do {
            stt = CheckError.ChuoiThanhSo();
            if (!contains(stt) || stt < 1) {
                System.out.println("Không tồn tại! Nhập lại...");
            }
        } while (stt < 1 || !contains(stt));
        for (CTMuonTra ct : Main.dsctmt) {
            if (ct.getStt() == stt) {
                // Xử lý lỗi mượn quá hạn
                Date ngayTra = getDayNow();
                int soNgayMuon = (int) (ngayTra.getTime() - ct.getNgayMuon().getTime());
                soNgayMuon /= (1000 * 60 * 60 * 24);
                if (soNgayMuon > 15) {
                    System.out.println("Hạn mượn 15 ngày!");
                    System.out.println("Đã quá hạn " + (soNgayMuon - 15) + " ngày");
                    System.out.println("Yêu cầu phạt: " + ((soNgayMuon - 15) * 150) + " VNĐ");
                }
                //Xử lý lỗi về thiệt hại sách
                System.out.print("Sách có bị thiệt hại không(0/1)? ");
                int ghiChu;
                do {
                    ghiChu = CheckError.ChuoiThanhSo();
                } while (ghiChu != 0 && ghiChu != 1);
                if (ghiChu == 1) {
                    xuLyLoiSach();
                }
                // Lưu lại thông tin trả
                ct.setNgayTra(ngayTra);
                ct.setGhiChu(ghiChu);
                ct.setTinhTrang(1);
                KhoSach kho = getKho(ct.getSach().getMaSach());
                kho.setSoLuong(kho.getSoLuong() + 1);
                System.out.println("Enter để tiếp tục...");
                break;
            }
        }
    }

    private static void xuatThongTin() {
        System.out.println("====================================================================================================================");
        System.out.printf("%-8s %-25s %-25s %-15s %-15s %-15s %-10s\n",
                "STT", "Họ tên", "Tên sách", "Ngày mượn", "Ngày trả",
                "Tình trạng", "Ghi chú");
        for (CTMuonTra mt : Main.dsctmt) {
            mt.xuat();
        }
        System.out.println("====================================================================================================================");
    }

    private static void thucHienMuonSach() {
        System.out.println("===============================================");
        System.out.printf("\t%-15s %-25s\n", "Mã thẻ mượn", "Chủ thẻ");
        for (MuonTra mt : Main.dsmt) {
            mt.xuat();
        }
        CTMuonTra ctmt = new CTMuonTra();
        System.out.println("===============================================");
        System.out.print("Nhập mã Thẻ mượn: ");
        String maMuonTra;
        MuonTra thongTin;
        do {
            maMuonTra = new Scanner(System.in).nextLine();
            thongTin = thongTinThe(maMuonTra);
            if (thongTin == null) {
                System.out.println("Mã Thẻ không tồn tại! Nhập lại...");
            }
        } while (maMuonTra.length() == 0 || thongTin == null);
        Main.dss.xuat();
        System.out.print("Nhập mã Sách muốn mượn: ");
        String maSach;
        Sach thongTinSach;
        do {
            maSach = new Scanner(System.in).nextLine();
            thongTinSach = thongTinSach(maSach);
            if (thongTinSach == null) {
                System.out.println("Mã Sách không tồn tại! Nhập lại...");
            } else if (hetSach(maSach)) {
                System.out.println("Sách đã cho mượn hết. Vui lòng đến vào hôm khác!");
                System.out.println("Enter để tiếp tục...");
                new Scanner(System.in).nextLine();
                return;
            }
        } while (maSach.length() == 0 || thongTinSach == null);

        Date ngayMuon = getDayNow();
        ctmt.setStt(Main.dsctmt.size() + 1);
        ctmt.setMuonTra(thongTin);
        ctmt.setSach(thongTinSach);
        ctmt.setGhiChu(0);
        ctmt.setNgayMuon(ngayMuon);
        ctmt.setNgayTra(null);
        ctmt.setTinhTrang(0);
        Main.dsctmt.add(ctmt);
    }

    private static MuonTra thongTinThe(String maMuonTra) {
        for (MuonTra mt : Main.dsmt) {
            if (mt.getMaMuonTra().equalsIgnoreCase(maMuonTra)) {
                return mt;
            }
        }
        return null;
    }

    private static Sach thongTinSach(String maSach) {
        for (int i = 0; i < Main.dss.getLength(); i++) {
            if (Main.dss.getListSach()[i].getMaSach().equalsIgnoreCase(maSach)) {
                return Main.dss.getListSach()[i];
            }
        }
        return null;
    }

    private static boolean hetSach(String maSach) {
        KhoSach ks = getKho(maSach);
        if (ks.getSoLuong() == 0) {
            return true;
        }
        for (KhoSach k : Main.dsk) {
            if (k.getMaSach().equalsIgnoreCase(maSach)) {
                k.setSoLuong(ks.getSoLuong() - 1);
                break;
            }
        }
        return false;
    }

    private static KhoSach getKho(String maSach) {
        for (KhoSach ks : Main.dsk) {
            if (ks.getMaSach().equalsIgnoreCase(maSach)) {
                return ks;
            }
        }
        return null;
    }

    private static boolean contains(int stt) {
        for (CTMuonTra ct : Main.dsctmt) {
            if (ct.getStt() == stt) {
                return true;
            }
        }
        return false;
    }

    private static Date getDayNow() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String s = sdf.format(d.getTime());
        try {
            return sdf.parse(s);
        } catch (ParseException ex) {
            Logger.getLogger(DocGia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static void xuLyLoiSach() {
        System.out.println("Các trường hợp xử lý");
        System.out.println("1. Mất sách.");
        System.out.println("2. Làm bẩn sách.");
        System.out.println("3. Làm rách sách.");
        System.out.println("4. Khác");
        System.out.print("Chọn loại lỗi: ");
        int cp = CheckError.ChuoiThanhSo();
        switch (cp) {
            case 1:
                System.out.println("Yêu cầu mua sách mới trả lại thư viện!");
                break;
            case 2:
                System.out.print("Nhập số trang bị bẩn: ");
                int soTrangBan = CheckError.ChuoiThanhSo();
                if (soTrangBan < 0) {
                    System.out.println("Số trang không hợp lệ!");
                    return;
                }
                System.out.println("Phạt " + (soTrangBan * 200) + " VNĐ");
                break;
            case 3:
                System.out.print("Nhập số trang bị rách: ");
                int soTrangRach = CheckError.ChuoiThanhSo();
                if (soTrangRach < 0) {
                    System.out.println("Số trang không hợp lệ!");
                    return;
                }
                System.out.println("Phạt " + (soTrangRach * 500) + " VNĐ");
                break;
            case 4:
                System.out.println("Tước thẻ 5 ngày!");
                break;
            default:
                break;
        }
    }
}
