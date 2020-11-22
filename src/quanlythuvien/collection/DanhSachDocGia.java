/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.collection;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import quanlythuvien.main.Main;
import quanlythuvien.model.DocGia;
import quanlythuvien.model.DocGiaNguoiLon;
import quanlythuvien.model.DocGiaTreEm;
import quanlythuvien.model.MuonTra;
import quanlythuvien.service.CheckError;
import quanlythuvien.service.ChuanHoaChuoi;

/**
 *
 * @author Admin
 */
public class DanhSachDocGia implements DanhSach {

    private DocGia listDocGia[] = new DocGia[1000];
    private int length;

    public DanhSachDocGia() {
    }

    public DocGia[] getListDocGia() {
        return listDocGia;
    }

    public void setListDocGia(DocGia[] listDocGia) {
        this.listDocGia = listDocGia;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public void xuat() {
        sapXep();
        System.out.println("==================================================================================================");
        System.out.printf("%-10s %-25s %-17s %-10s %-20s %-15s\n",
                "Mã ĐG", "Họ tên", "Ngày làm thẻ", "Hạn thẻ", "Người đại diện", "CMND");
        System.out.println("--------------------------------------------------------------------------------------------------");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int index = 0;
        while (listDocGia[index] != null) {
            {
                if (listDocGia[index] instanceof DocGiaNguoiLon) {
                    DocGiaNguoiLon dgn = (DocGiaNguoiLon) listDocGia[index];
                    System.out.printf("%-10s %-25s %-17s %-10s %-20s %-15s\n",
                            dgn.getMaDocGia(),
                            dgn.getHoTen(),
                            sdf.format(dgn.getNgayLamThe()),
                            dgn.getHanThe(),
                            "", dgn.getCMND());
                    System.out.println("--------------------------------------------------------------------------------------------------");
                } else {
                    DocGiaTreEm dgt = (DocGiaTreEm) listDocGia[index];
                    System.out.printf("%-10s %-25s %-17s %-10s %-20s %-15s\n",
                            dgt.getMaDocGia(),
                            dgt.getHoTen(),
                            sdf.format(dgt.getNgayLamThe()),
                            dgt.getHanThe(),
                            dgt.getNguoiDaiDien(), "");
                    System.out.println("--------------------------------------------------------------------------------------------------");
                }
            }
            index++;
        }
    }

    @Override
    public void them(Object o) {
        resetLength();
        DocGia dg = (DocGia) o;
        if (2020 - dg.getNamSinh() < 15) {
            DocGiaTreEm dgt = new DocGiaTreEm();
            System.out.println("Đây là độc giả trẻ em");
            System.out.print("Hãy nhập tên người đại diện: ");
            String nguoiDaiDien;
            do {
                nguoiDaiDien = new Scanner(System.in).nextLine();
            } while (nguoiDaiDien.length() == 0);
            dgt.setMaDocGia(dg.getMaDocGia());
            dgt.setHoTen(dg.getHoTen());
            dgt.setGioiTinh(dg.getGioiTinh());
            dgt.setNamSinh(dg.getNamSinh());
            dgt.setNgayLamThe(dg.getNgayLamThe());
            dgt.setHanThe(dg.getHanThe());
            dgt.setNguoiDaiDien(nguoiDaiDien);
            DecimalFormat dcf = new DecimalFormat("###,###,###");
            String tien = dcf.format(dgt.tienLamThe());
            System.out.println("Xin thanh toán tiền làm thẻ: " + tien + " (VNĐ)");
            listDocGia[length++] = dgt;
            MuonTra mt = new MuonTra();
            mt.setDocGia(dgt);
            mt.nhap();
            Main.dsmt.add(mt);
        } else {
            DocGiaNguoiLon dgt = new DocGiaNguoiLon();
            System.out.println("Đây là độc giả người lớn");
            System.out.print("Hãy nhập số CMND (9-12 số): ");
            String CMND;
            do {
                CMND = new Scanner(System.in).nextLine();
                if (CMND.length() < 9 || CMND.length() > 12) {
                    System.err.println("Số CMND không hợp lệ! Nhập lại!");
                }
            } while (CMND.length() < 9 || CMND.length() > 12);
            dgt.setMaDocGia(dg.getMaDocGia());
            dgt.setHoTen(dg.getHoTen());
            dgt.setGioiTinh(dg.getGioiTinh());
            dgt.setNamSinh(dg.getNamSinh());
            dgt.setNgayLamThe(dg.getNgayLamThe());
            dgt.setHanThe(dg.getHanThe());
            dgt.setCMND(CMND);
            DecimalFormat dcf = new DecimalFormat("###,###,###");
            String tien = dcf.format(dgt.tienLamThe());
            System.out.println("Xin thanh toán tiền làm thẻ: " + tien + " (VNĐ)");
            listDocGia[length++] = dgt;
            MuonTra mt = new MuonTra();
            mt.setDocGia(dgt);
            mt.nhap();
            Main.dsmt.add(mt);
        }
    }

    public void themNL(DocGiaNguoiLon dgt) {
        resetLength();
        listDocGia[length++] = dgt;
    }

    public void themTE(DocGiaTreEm dgt) {
        resetLength();
        listDocGia[length++] = dgt;
    }

    @Override
    public void sua() {
        resetLength();
        System.out.println("============================================");
        System.out.println("\t   SỬA THÔNG TIN ĐỘC GIẢ");
        xuat();
        System.out.print("Nhập mã Độc giả cần sửa: ");
        String maDocGia = CheckError.checkMa("DG-");
        int index = contains(maDocGia);
        if (index > -1) {
            nhapThongTin(listDocGia[index]);
        } else {
            System.err.println("Mã không tồn tại! Enter để tiếp tục...");
            new Scanner(System.in).nextLine();
        }
    }

    @Override
    public void xoa() {
        resetLength();
        System.out.println("============================================");
        System.out.println("\t   XOÁ THÔNG TIN ĐỘC GIẢ");
        xuat();
        System.out.print("Nhập mã Độc giả cần xoá: ");
        String maDG = CheckError.checkMa("DG-");
        if (contains(maDG) > -1) {
            int pos = contains(maDG);
            for (int i = pos; i <= length - 2; i++) {
                listDocGia[i] = listDocGia[i + 1];
            }
            length--;
            listDocGia[length] = null;
            String number[] = maDG.split("-");
            // Xoá thẻ mượn - trả
            for (MuonTra mt : Main.dsmt) {
                String temp[] = mt.getMaMuonTra().split("-");
                if (temp[1].equalsIgnoreCase(number[1])) {
                    Main.dsmt.remove(mt);
                    break;
                }
            }
            System.out.println("Xoá thành công! Enter để tiếp tục...");
            new Scanner(System.in).nextLine();
            return;
        }
        System.out.println("Mã không tồn tại! Enter để tiếp tục...");
        new Scanner(System.in).nextLine();
    }

    @Override
    public void timKiem() {
        resetLength();
        System.out.println("============================================");
        System.out.println("||            TÌM KIẾM ĐỘC GIẢ            ||");
        System.out.println("============================================");
        System.out.println("||  1  ||   Tìm theo Tên                  ||");
        System.out.println("||  2  ||   Tìm theo Năm sinh             ||");
        System.out.println("||  3  ||   Tìm theo Giới tính            ||");
        System.out.println("============================================");
        System.out.print("Mời chọn chức năng: ");
        int cp = CheckError.ChuoiThanhSo();
        switch (cp) {
            case 1:
                timTheoTen();
                System.out.println("Tìm hoàn tất! Enter để tiếp tục...");
                new Scanner(System.in).nextLine();
                break;
            case 2:
                timTheoNamSinh();
                System.out.println("Tìm hoàn tất! Enter để tiếp tục...");
                new Scanner(System.in).nextLine();
                break;
            case 3:
                timTheoGioiTinh();
                System.out.println("Tìm hoàn tất! Enter để tiếp tục...");
                new Scanner(System.in).nextLine();
                break;
            default:
                System.err.println("Sai cú pháp! Enter để tiếp tục...");
                new Scanner(System.in).nextLine();
                break;
        }
    }

    @Override
    public void sapXep() {
        resetLength();
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (listDocGia[i].getMaDocGia().compareToIgnoreCase(listDocGia[j].getMaDocGia()) > 0) {
                    DocGia temp = listDocGia[i];
                    listDocGia[i] = listDocGia[j];
                    listDocGia[j] = temp;
                }
            }
        }
    }

    @Override
    public int contains(String ma) {
        resetLength();
        for (int i = 0; i < length; i++) {
            if (listDocGia[i].getMaDocGia().equalsIgnoreCase(ma)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void resetLength() {
        length = 0;
        if (listDocGia[0] == null) {
            length = 0;
            return;
        }
        while (listDocGia[length] != null) {
            length++;
        }
    }

    private void nhapThongTin(DocGia dg) {
        DecimalFormat dcf = new DecimalFormat("###,###,###");
        String hoTen, gioiTinh;
        int namSinh;
        System.out.println("======================================");
        System.out.print("Nhập họ tên: ");
        do {
            hoTen = new Scanner(System.in).nextLine();
        } while (hoTen.length() == 0);
        System.out.print("Nhập giới tính: ");
        do {
            gioiTinh = new Scanner(System.in).nextLine();
            if(!gioiTinh.equalsIgnoreCase("nam")
                &&!gioiTinh.equalsIgnoreCase("nu"))
            System.err.println("Không hợp lệ! Nhập lại...");
        } while (gioiTinh.length() == 0
                ||(!gioiTinh.equalsIgnoreCase("nam")
                &&!gioiTinh.equalsIgnoreCase("nu")));
        System.out.print("Nhập năm sinh: ");
        do {
            namSinh = CheckError.ChuoiThanhSo();
            if (namSinh > 2015) {
                System.out.println("Tuổi quá nhỏ! Nhập lại!");
            }
        } while (namSinh > 2015);
        dg.setHoTen(ChuanHoaChuoi.chuanHoa(hoTen));
        dg.setGioiTinh(ChuanHoaChuoi.chuanHoa(gioiTinh));
        dg.setNamSinh(namSinh);
        if (2020 - dg.getNamSinh() < 15) {
            DocGiaTreEm dgt = new DocGiaTreEm();
            dgt.setMaDocGia(dg.getMaDocGia());
            dgt.setHoTen(dg.getHoTen());
            dgt.setGioiTinh(dg.getGioiTinh());
            dgt.setNamSinh(dg.getNamSinh());
            dgt.setNgayLamThe(dg.getNgayLamThe());
            dgt.setHanThe(dg.getHanThe());
            System.out.println("Đây là độc giả trẻ em");
            System.out.print("Hãy nhập tên người đại diện: ");
            String nguoiDaiDien;
            do {
                nguoiDaiDien = new Scanner(System.in).nextLine();
            } while (nguoiDaiDien.length() == 0);
            dgt.setNguoiDaiDien(nguoiDaiDien);
            if (dgt.tienLamThe() != dg.tienLamThe()) {
                System.out.println("Xin thanh toán phí làm thẻ: " + dcf.format(dgt.tienLamThe()) + " (VNĐ)");
            }
        } else {
            DocGiaNguoiLon dgt = new DocGiaNguoiLon();
            dgt.setMaDocGia(dg.getMaDocGia());
            dgt.setHoTen(dg.getHoTen());
            dgt.setGioiTinh(dg.getGioiTinh());
            dgt.setNamSinh(dg.getNamSinh());
            dgt.setNgayLamThe(dg.getNgayLamThe());
            dgt.setHanThe(dg.getHanThe());
            System.out.println("Đây là độc giả người lớn");
            System.out.print("Hãy nhập số CMND (9-12 số): ");
            String CMND;
            do {
                CMND = new Scanner(System.in).nextLine();
                if (CMND.length() < 9 || CMND.length() > 12 || !kiemTraCMND(CMND)) {
                    System.err.println("Số CMND không hợp lệ! Nhập lại!");
                }
            } while (CMND.length() < 9 || CMND.length() > 12 || !kiemTraCMND(CMND));
            dgt.setCMND(CMND);
            if (dgt.tienLamThe() != dg.tienLamThe()) {
                System.out.println("Xin thanh toán phí làm thẻ: " + dcf.format(dgt.tienLamThe()) + " (VNĐ)");
            }
        }
    }

    private void timTheoTen() {
        String key;
        System.out.print("Nhập từ khoá: ");
        key = new Scanner(System.in).nextLine();
        sapXep();
        System.out.println("==================================================================================================");
        System.out.printf("%-10s %-25s %-17s %-10s %-20s %-15s\n",
                "Mã ĐG", "Họ tên", "Ngày làm thẻ", "Hạn thẻ", "Người đại diện", "CMND");
        System.out.println("--------------------------------------------------------------------------------------------------");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int index = 0;
        while (listDocGia[index] != null) {
            if (listDocGia[index] instanceof DocGiaNguoiLon) {
                DocGiaNguoiLon dgn = (DocGiaNguoiLon) listDocGia[index];
                if (dgn.getHoTen().toLowerCase().contains(key.toLowerCase())) {
                    System.out.printf("%-10s %-25s %-17s %-10s %-20s %-15s\n",
                            dgn.getMaDocGia(),
                            dgn.getHoTen(),
                            sdf.format(dgn.getNgayLamThe()),
                            dgn.getHanThe(),
                            "", dgn.getCMND());
                    System.out.println("--------------------------------------------------------------------------------------------------");
                }
            } else {
                DocGiaTreEm dgt = (DocGiaTreEm) listDocGia[index];
                if (dgt.getHoTen().toLowerCase().contains(key.toLowerCase())) {
                    System.out.printf("%-10s %-25s %-17s %-10s %-20s %-15s\n",
                            dgt.getMaDocGia(),
                            dgt.getHoTen(),
                            sdf.format(dgt.getNgayLamThe()),
                            dgt.getHanThe(),
                            dgt.getNguoiDaiDien(), "");
                    System.out.println("--------------------------------------------------------------------------------------------------");
                }
            }
            index++;
        }
    }

    private void timTheoNamSinh() {
        System.out.print("Nhập năm sinh: ");
        int namSinh = CheckError.ChuoiThanhSo();
        sapXep();
        System.out.println("==================================================================================================");
        System.out.printf("%-10s %-25s %-17s %-10s %-20s %-15s\n",
                "Mã ĐG", "Họ tên", "Ngày làm thẻ", "Hạn thẻ", "Người đại diện", "CMND");
        System.out.println("--------------------------------------------------------------------------------------------------");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int index = 0;
        while (listDocGia[index] != null) {
            if (listDocGia[index] instanceof DocGiaNguoiLon) {
                DocGiaNguoiLon dgn = (DocGiaNguoiLon) listDocGia[index];
                if (dgn.getNamSinh() == namSinh) {
                    System.out.printf("%-10s %-25s %-17s %-10s %-20s %-15s\n",
                            dgn.getMaDocGia(),
                            dgn.getHoTen(),
                            sdf.format(dgn.getNgayLamThe()),
                            dgn.getHanThe(),
                            "", dgn.getCMND());
                    System.out.println("--------------------------------------------------------------------------------------------------");
                }
            } else {
                DocGiaTreEm dgt = (DocGiaTreEm) listDocGia[index];
                if (dgt.getNamSinh() == namSinh) {
                    System.out.printf("%-10s %-25s %-17s %-10s %-20s %-15s\n",
                            dgt.getMaDocGia(),
                            dgt.getHoTen(),
                            sdf.format(dgt.getNgayLamThe()),
                            dgt.getHanThe(),
                            dgt.getNguoiDaiDien(), "");
                    System.out.println("--------------------------------------------------------------------------------------------------");
                }
            }
            index++;
        }
    }

    private void timTheoGioiTinh() {
        String key;
        System.out.print("Nhập giới tính: ");
        key = new Scanner(System.in).nextLine();
        sapXep();
        System.out.println("==================================================================================================");
        System.out.printf("%-10s %-25s %-17s %-10s %-20s %-15s\n",
                "Mã ĐG", "Họ tên", "Ngày làm thẻ", "Hạn thẻ", "Người đại diện", "CMND");
        System.out.println("--------------------------------------------------------------------------------------------------");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int index = 0;
        while (listDocGia[index] != null) {
            if (listDocGia[index] instanceof DocGiaNguoiLon) {
                DocGiaNguoiLon dgn = (DocGiaNguoiLon) listDocGia[index];
                if (dgn.getGioiTinh().toLowerCase().contains(key.toLowerCase())) {
                    System.out.printf("%-10s %-25s %-17s %-10s %-20s %-15s\n",
                            dgn.getMaDocGia(),
                            dgn.getHoTen(),
                            sdf.format(dgn.getNgayLamThe()),
                            dgn.getHanThe(),
                            "", dgn.getCMND());
                    System.out.println("--------------------------------------------------------------------------------------------------");
                }
            } else {
                DocGiaTreEm dgt = (DocGiaTreEm) listDocGia[index];
                if (dgt.getGioiTinh().toLowerCase().contains(key.toLowerCase())) {
                    System.out.printf("%-10s %-25s %-17s %-10s %-20s %-15s\n",
                            dgt.getMaDocGia(),
                            dgt.getHoTen(),
                            sdf.format(dgt.getNgayLamThe()),
                            dgt.getHanThe(),
                            dgt.getNguoiDaiDien(), "");
                    System.out.println("--------------------------------------------------------------------------------------------------");
                }
            }
            index++;
        }
    }

    private boolean kiemTraCMND(String CMND) {
        for (int i = 0; i < CMND.length(); i++) {
            if (CMND.charAt(i) < '0' || CMND.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

}
