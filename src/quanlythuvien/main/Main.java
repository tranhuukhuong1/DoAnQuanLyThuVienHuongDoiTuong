/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import quanlythuvien.collection.DanhSachDanhMuc;
import quanlythuvien.collection.DanhSachDocGia;
import quanlythuvien.collection.DanhSachNgonNgu;
import quanlythuvien.collection.DanhSachNhaXuatBan;
import quanlythuvien.collection.DanhSachSach;
import quanlythuvien.collection.DanhSachTacGia;
import quanlythuvien.collection.DanhSachTaiKhoan;
import quanlythuvien.model.CTMuonTra;
import quanlythuvien.model.DanhMuc;
import quanlythuvien.model.DocGia;
import quanlythuvien.model.DocGiaNguoiLon;
import quanlythuvien.model.DocGiaTreEm;
import quanlythuvien.model.KhoSach;
import quanlythuvien.model.MuonTra;
import quanlythuvien.model.NgonNgu;
import quanlythuvien.model.NhaXuatBan;
import quanlythuvien.model.NhanVien;
import quanlythuvien.model.Sach;
import quanlythuvien.model.TacGia;
import quanlythuvien.model.TaiKhoan;
import quanlythuvien.service.CheckError;
import static quanlythuvien.service.DocGhiFile.*;

/**
 *
 * @author Admin
 */
public class Main {

    public static DanhSachDanhMuc dsdm = new DanhSachDanhMuc();
    public static DanhSachTaiKhoan dstk = new DanhSachTaiKhoan();
    public static DanhSachNgonNgu dsnn = new DanhSachNgonNgu();
    public static DanhSachNhaXuatBan dsnxb = new DanhSachNhaXuatBan();
    public static DanhSachSach dss = new DanhSachSach();
    public static DanhSachTacGia dstg = new DanhSachTacGia();
    public static DanhSachDocGia dsdg = new DanhSachDocGia();
    public static ArrayList<NhanVien> dsnv = new ArrayList<>();
    public static ArrayList<MuonTra> dsmt = new ArrayList<>();
    public static ArrayList<KhoSach> dsk = new ArrayList<>();
    public static ArrayList<CTMuonTra> dsctmt = new ArrayList<>();

    public static void main(String[] args) {
        docFile();
        dangNhap();
        ghiFile();
    }

    private static void menuAdmin() {
        System.out.println("===================================================");
        System.out.println("||                    MENU CHÍNH                 ||");
        System.out.println("||-----------------------------------------------||");
        System.out.println("||   1   ||        Quản lý Sách                  ||");
        System.out.println("||-----------------------------------------------||");
        System.out.println("||   2   ||        Quản lý Độc giả               ||");
        System.out.println("||-----------------------------------------------||");
        System.out.println("||   3   ||        Quản lý Nhân viên             ||");
        System.out.println("||-----------------------------------------------||");
        System.out.println("||   4   ||        Quản lý Mượn - trả            ||");
        System.out.println("||-----------------------------------------------||");
        System.out.println("||   5   ||        Quản lý Kho sách              ||");
        System.out.println("||-----------------------------------------------||");
        System.out.println("||   6   ||        Thoát chương trình            ||");
        System.out.println("===================================================");
        System.out.print("Mời chọn chức năng: ");
        int cp = CheckError.ChuoiThanhSo();
        switch (cp) {
            case 1:
                quanLySach();
                break;
            case 2:
                quanLyDocGia();
                break;
            case 3:
                new QuanLyNhanVien().Running();
                break;
            case 4:
                new QuanLyMuonTra().Running();
                break;
            case 5:
                QuanLyKho.Running();
                break;
            case 6:
                System.out.println("Cám ơn đã sử dụng chương trình!");
                ghiFile();
                System.exit(0);
                break;
            default:
                System.err.println("Sai cú pháp! Enter để tiếp tục...");
                new Scanner(System.in).nextLine();
                break;
        }
    }

    private static void menuNhanVien() {
        System.out.println("===================================================");
        System.out.println("||                    MENU CHÍNH                 ||");
        System.out.println("||-----------------------------------------------||");
        System.out.println("||   1   ||        Quản lý Độc giả               ||");
        System.out.println("||-----------------------------------------------||");
        System.out.println("||   2   ||        Quản lý Mượn - trả            ||");
        System.out.println("||-----------------------------------------------||");
        System.out.println("||   3   ||        Quản lý Kho sách              ||");
        System.out.println("||-----------------------------------------------||");
        System.out.println("||   4   ||        Thoát chương trình            ||");
        System.out.println("===================================================");
        System.out.print("Mời chọn chức năng: ");
        int cp = CheckError.ChuoiThanhSo();
        switch (cp) {
            case 1:
                quanLyDocGia();
                break;
            case 2:
                new QuanLyMuonTra().Running();
                break;
            case 3:
                QuanLyKho.Running();
                break;
            case 4:
                System.out.println("Cám ơn đã sử dụng chương trình!");
                ghiFile();
                System.exit(0);
                break;
            default:
                System.err.println("Sai cú pháp! Enter để tiếp tục...");
                new Scanner(System.in).nextLine();
                break;
        }
    }

    private static void quanLySach() {
        boolean flag = true;
        while (flag) {
            System.out.println("===================================================");
            System.out.println("||                   QUẢN LÝ SÁCH                ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   1   ||        Thêm sách mới                 ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   2   ||        Sửa thông tin sách            ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   3   ||        Xuất danh sách                ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   4   ||        Tìm kiếm sách                 ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   5   ||        Xoá sách                      ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   6   ||        Thoát chức năng               ||");
            System.out.println("===================================================");
            System.out.print("Mời chọn chức năng: ");
            int cp = CheckError.ChuoiThanhSo();
            switch (cp) {
                case 1:
                    Sach s = new Sach();
                    s.nhap();
                    dss.them(s);
                    break;
                case 2:
                    dss.sua();
                    break;
                case 3:
                    dss.xuat();
                    System.out.println();
                    System.out.println("\tXuất thành công! Enter để tiếp tục...");
                    new Scanner(System.in).nextLine();
                    break;
                case 4:
                    dss.timKiem();
                    break;
                case 5:
                    dss.xoa();
                    break;
                case 6:
                    ghiFile();
                    flag = false;
                    break;
                default:
                    System.err.println("Sai cú pháp! Enter để tiếp tục...");
                    new Scanner(System.in).nextLine();
                    break;
            }
        }
    }

    private static void quanLyDocGia() {
        boolean flag = true;
        while (flag) {
            System.out.println("===================================================");
            System.out.println("||                  QUẢN LÝ ĐỘC GIẢ              ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   1   ||        Thêm Độc giả                  ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   2   ||        Sửa thông tin Độc giả         ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   3   ||        Xuất danh sách                ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   4   ||        Tìm kiếm (lọc)                ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   5   ||        Xoá Độc giả                   ||");
            System.out.println("||-----------------------------------------------||");
            System.out.println("||   6   ||        Thoát chức năng               ||");
            System.out.println("===================================================");
            System.out.print("Mời chọn chức năng: ");
            int cp = CheckError.ChuoiThanhSo();
            switch (cp) {
                case 1:
                    dsdg.xuat();
                    DocGia dg = new DocGia() {
                        @Override
                        public int tienLamThe() {
                            return 0;
                        }
                    };
                    dg.nhap();
                    dsdg.them(dg);
                    break;
                case 2:
                    dsdg.sua();
                    break;
                case 3:
                    dsdg.xuat();
                    System.out.println();
                    System.out.println("\tXuất thành công! Enter để tiếp tục...");
                    new Scanner(System.in).nextLine();
                    break;
                case 4:
                    dsdg.timKiem();
                    break;
                case 5:
                    dsdg.xoa();
                    break;
                case 6:
                    ghiFile();
                    flag = false;
                    break;
                default:
                    System.err.println("Sai cú pháp!");
                    break;
            }
        }
    }

    private static void dangNhap() {
        String tenDangNhap, matKhau;
        System.out.print("Nhập tên đăng nhập: ");
        tenDangNhap = new Scanner(System.in).nextLine();
        System.out.print("Nhập mật khẩu: ");
        matKhau = new Scanner(System.in).nextLine();
        TaiKhoan tk = thongTinTaiKhoan(tenDangNhap, matKhau);
        if (tk == null) {
            System.err.println("Sai thông tin đăng nhập! Enter để thoát...");
            new Scanner(System.in).nextLine();
        } else if (tk.getLoaiTaiKhoan() == 1) {
            while (true) {
                menuAdmin();
            }
        } else if (tk.getLoaiTaiKhoan() == 0) {
            menuNhanVien();
        }
    }

    private static TaiKhoan thongTinTaiKhoan(String tenDangNhap, String matKhau) {
        for (int i = 0; i < dstk.getLength(); i++) {
            TaiKhoan tk[] = dstk.getListTaiKhoan();
            if (tk[i].getTenDangNhap().equals(tenDangNhap)
                    && tk[i].getMatKhau().equals(matKhau)) {
                return tk[i];
            }
        }
        return null;
    }

    
    /*
        ======================================
                IMPORT LẠI DTB KHI MẤT
        ======================================
    */
    private static void importData() {
        /*
        *===============================
        *	THÊM TÀI KHOẢN
        *===============================
         */
        dstk.them(new TaiKhoan("admin", "admin", 1));
        dstk.them(new TaiKhoan("nv1", "nv1", 0));
        dstk.them(new TaiKhoan("nv2", "nv2", 0));

        /*
        *===============================
        *	THÊM DANH MỤC
        *===============================
         */
        dsdm.them(new DanhMuc("DM-1", "Tin Học"));
        dsdm.them(new DanhMuc("DM-2", "Lịch Sử"));
        dsdm.them(new DanhMuc("DM-3", "Tiểu Thuyết"));

        /*
        *===============================
        *	THÊM NXB
        *===============================
         */
        dsnxb.them(new NhaXuatBan("NXB-1", "NXB Tri Thức", "Hà Nội"));
        dsnxb.them(new NhaXuatBan("NXB-2", "NXB Trẻ", "Hồ Chí Minh"));
        dsnxb.them(new NhaXuatBan("NXB-3", "NXB Giáo Dục", "Hà Nội"));
        dsnxb.them(new NhaXuatBan("NXB-4", "NXB Văn Học", "Hà Nội"));

        /*
        *===============================
        *	THÊM NGÔN NGỮ
        *===============================
         */
        dsnn.them(new NgonNgu("VI", "Tiếng Việt"));
        dsnn.them(new NgonNgu("EN", "Tiếng Anh"));
        dsnn.them(new NgonNgu("FR", "Tiếng Pháp"));

        /*
        *===============================
        *	THÊM ĐỘC GIẢ
        *===============================
         */
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String s = sdf.format(d);
        try {
            d = sdf.parse(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Random rd = new Random();
        dsdg.themNL(new DocGiaNguoiLon(
                "271798000", "DG-001", d, 1 + rd.nextInt(50), "Bui Tan Au", "Nam", 1950 + rd.nextInt(55)));
        dsdg.themNL(new DocGiaNguoiLon(
                "271798001", "DG-002", d, 1 + rd.nextInt(50), "Huynh Quoc Bao", "Nam", 1950 + rd.nextInt(55)));
        dsdg.themTE(new DocGiaTreEm(
                "Le Thuy Trang", "DG-003", d, 1 + rd.nextInt(50), "Nguyen Quang Dai", "Nam", 2006 + rd.nextInt()));
        dsdg.themNL(new DocGiaNguoiLon(
                "271798002", "DG-004", d, 1 + rd.nextInt(50), "Nguyen Thi Binh", "Nu", 1950 + rd.nextInt(55)));
        dsdg.themTE(new DocGiaTreEm(
                "Nguyen Quoc Hung", "DG-005", d, 1 + rd.nextInt(50), "Nguyen Thi Ha", "Nu", 2006 + rd.nextInt()));
        dsdg.themNL(new DocGiaNguoiLon(
                "271798003", "DG-006", d, 1 + rd.nextInt(50), "Le Thao An", "Nu", 1950 + rd.nextInt(55)));
        dsdg.themTE(new DocGiaTreEm(
                "Nguyen Thi Hoa", "DG-007", d, 1 + rd.nextInt(50), "Lai Tuyet Nhung", "Nu", 2006 + rd.nextInt()));
        dsdg.themNL(new DocGiaNguoiLon(
                "271798004", "DG-008", d, 1 + rd.nextInt(50), "Dang Khai Dung", "Nam", 1950 + rd.nextInt(55)));
        dsdg.themNL(new DocGiaNguoiLon(
                "271798005", "DG-009", d, 1 + rd.nextInt(50), "Do Thuy Chi", "Nu", 1950 + rd.nextInt(55)));
        dsdg.themTE(new DocGiaTreEm(
                "Nguyen Thu Hong", "DG-010", d, 1 + rd.nextInt(50), "Tran Anh Hao", "Nam", 2006 + rd.nextInt()));
        dsdg.themTE(new DocGiaTreEm(
                "Bui Van Dung", "DG-011", d, 1 + rd.nextInt(50), "Bui Duc Long", "Nam", 2006 + rd.nextInt()));
        dsdg.themNL(new DocGiaNguoiLon(
                "271798006", "DG-012", d, 1 + rd.nextInt(50), "Nguyen My Duyen", "Nu", 1950 + rd.nextInt(55)));
        dsdg.themTE(new DocGiaTreEm(
                "Tran Thien Khoa", "DG-013", d, 1 + rd.nextInt(50), "Tran Thi Ly", "Nu", 2006 + rd.nextInt()));
        dsdg.themNL(new DocGiaNguoiLon(
                "271798007", "DG-014", d, 1 + rd.nextInt(50), "Ly Thai Hung", "Nam", 1950 + rd.nextInt(55)));
        dsdg.themNL(new DocGiaNguoiLon(
                "271798008", "DG-015", d, 1 + rd.nextInt(50), "Dang Huong Giang", "Nu", 1950 + rd.nextInt(55)));
        dsdg.themTE(new DocGiaTreEm(
                "Pham Thuy Lieu", "DG-016", d, 1 + rd.nextInt(50), "Dang Thanh Hung", "Nam", 2006 + rd.nextInt()));
        dsdg.themTE(new DocGiaTreEm(
                "Le Duc Anh", "DG-017", d, 1 + rd.nextInt(50), "Le My Hue", "Nu", 2006 + rd.nextInt()));
        dsdg.themTE(new DocGiaTreEm(
                "Ly Anh Kiet", "DG-018", d, 1 + rd.nextInt(50), "Ly Ngoc Lien", "Nu", 2006 + rd.nextInt()));
        dsdg.themNL(new DocGiaNguoiLon(
                "271798009", "DG-019", d, 1 + rd.nextInt(50), "Nguyen Van Hau", "Nam", 1950 + rd.nextInt(55)));
        dsdg.themTE(new DocGiaTreEm(
                "Vo Thuy Duong", "DG-020", d, 1 + rd.nextInt(50), "Tran Hoang Dung", "Nam", 2006 + rd.nextInt()));
        /*
        *===============================
        *	   THÊM TÁC GIẢ
        *===============================
         */
        dstg.them(new TacGia("TG-001", "Nam Cao", "Nam", 1915));
        dstg.them(new TacGia("TG-002", "Vũ Trọng Phụng", "Nam", 1912));
        dstg.them(new TacGia("TG-003", "Tô Hoài", "Nam", 1920));
        dstg.them(new TacGia("TG-004", "Thuỵ Khuê", "Nữ", 1940));
        dstg.them(new TacGia("TG-005", "Brian Goetz", "Nam", 1962));
        dstg.them(new TacGia("TG-006", "Joshua Bloch", "Nam", 1974));
        /*
        *===============================
        *           THÊM SÁCH
        *===============================
         */
        dss.them(new Sach("DM-3", "MS-001", "Dieu Van", "TG-1", "NXB-4", 1986, "VI"));
        dss.them(new Sach("DM-3", "MS-002", "Lao Hac", "TG-1", "NXB-3", 1980, "VI"));
        dss.them(new Sach("DM-3", "MS-003", "Nui Cuu Quoc", "TG-3", "NXB-4", 1948, "VI"));
        dss.them(new Sach("DM-1", "MS-004", "Thinking in Java", "TG-6", "NXB-3", 1998, "EN"));
        dss.them(new Sach("DM-2", "MS-005", "Vua Gia Long&Nguoi Phap", "TG-4", "NXB-3", 2017, "VI"));
        dss.them(new Sach("DM-3", "MS-006", "Mua Nha", "TG-1", "NXB-4", 1992, "VI"));
        dss.them(new Sach("DM-3", "MS-007", "So Do", "TG-2", "NXB-4", 1987, "VI"));
        dss.them(new Sach("DM-2", "MS-008", "Hoa Kiet Lam Son", "TG-4", "NXB-3", 2003, "VI"));
        dss.them(new Sach("DM-3", "MS-009", "Di Hao", "TG-1", "NXB-4", 1983, "VI"));
        dss.them(new Sach("DM-3", "MS-010", "Chi Pheo", "TG-1", "NXB-3", 1995, "VI"));
        dss.them(new Sach("DM-3", "MS-011", "Vo Chong A Phu", "TG-3", "NXB-4", 1969, "VI"));
        dss.them(new Sach("DM-3", "MS-012", "Luc Si", "TG-2", "NXB-4", 1937, "VI"));
        dss.them(new Sach("DM-3", "MS-013", "Chong Nang Len Duong", "TG-2", "NXB-1", 2001, "VI"));
        dss.them(new Sach("DM-3", "MS-014", "Vo De", "TG-2", "NXB-3", 1936, "VI"));
        dss.them(new Sach("DM-3", "MS-015", "Nhat Ky Vung Cao", "TG-3", "NXB-4", 1969, "VI"));
        dss.them(new Sach("DM-2", "MS-016", "Bao Tap Trieu Tran", "TG-4", "NXB-3", 2010, "VI"));
        dss.them(new Sach("DM-2", "MS-017", "Viet Nam Su Luoc", "TG-4", "NXB-3", 2020, "VI"));
        dss.them(new Sach("DM-1", "MS-018", "The Clean Coder", "TG-5", "NXB-3", 2011, "EN"));
        dss.them(new Sach("DM-3", "MS-019", "Quy Phai", "TG-2", "NXB-4", 1937, "VI"));
        dss.them(new Sach("DM-3", "MS-020", "Doi Thua", "TG-1", "NXB-4", 1993, "VI"));
        dss.them(new Sach("DM-3", "MS-021", "Giong To", "TG-2", "NXB-3", 1936, "VI"));
        dss.them(new Sach("DM-3", "MS-022", "Dut Tinh", "TG-2", "NXB-3", 1934, "VI"));
        dss.them(new Sach("DM-3", "MS-023", "Mot Dam Cuoi", "TG-1", "NXB-4", 1988, "VI"));
        dss.them(new Sach("DM-3", "MS-024", "Muoi Nam", "TG-3", "NXB-4", 1957, "VI"));
        dss.them(new Sach("DM-3", "MS-025", "Cai Ghen Dan Ong", "TG-2", "NXB-3", 1988, "VI"));
        dss.them(new Sach("DM-3", "MS-026", "Trung So Doc Dat", "TG-2", "NXB-1", 1937, "VI"));
        dss.them(new Sach("DM-2", "MS-027", "Su Viet 12 Trang Ca", "TG-4", "NXB-3", 2017, "VI"));
        dss.them(new Sach("DM-1", "MS-028", "Pragmatic Programmer", "TG-5", "NXB-3", 1999, "EN"));
        dss.them(new Sach("DM-1", "MS-029", "Java Concurrency", "TG-5", "NXB-3", 2006, "EN"));
        dss.them(new Sach("DM-3", "MS-030", "Doi Mat", "TG-1", "NXB-3", 1989, "VI"));
        dss.them(new Sach("DM-3", "MS-031", "Nha Ngheo", "TG-3", "NXB-4", 1944, "VI"));
        dss.them(new Sach("DM-3", "MS-032", "Truyen Tay Bac", "TG-3", "NXB-3", 1953, "VI"));
        dss.them(new Sach("DM-3", "MS-033", "Truyen No Than ", "TG-3", "NXB-2", 2003, "VI"));
        dss.them(new Sach("DM-3", "MS-034", "Con Meo", "TG-1", "NXB-3", 1990, "VI"));
        dss.them(new Sach("DM-3", "MS-035", "De Men Phieu Luu Ky", "TG-3", "NXB-2", 1941, "VI"));
        dss.them(new Sach("DM-3", "MS-036", "Lam Di", "TG-2", "NXB-4", 1936, "VI"));
        dss.them(new Sach("DM-2", "MS-037", "Dai Viet Su Ky Toan Thu", "TG-4", "NXB-3", 1697, "VI"));
        dss.them(new Sach("DM-3", "MS-038", "Mot bua no", "TG-1", "NXB-3", 1991, "VI"));
        dss.them(new Sach("DM-1", "MS-039", "Effective Java", "TG-6", "NXB-3", 2001, "EN"));
        dss.them(new Sach("DM-3", "MS-040", "Trang Sang", "TG-1", "NXB-3", 1996, "VI"));
        dss.them(new Sach("DM-3", "MS-041", "O Chuot", "TG-3", "NXB-2", 1942, "VI"));
        /*
        *===============================
        *           THÊM KHO
        *===============================
         */
        dsk.add(new KhoSach("MS-001", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-002", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-003", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-004", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-005", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-006", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-007", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-008", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-009", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-010", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-011", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-012", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-013", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-014", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-015", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-016", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-017", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-018", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-019", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-020", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-021", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-022", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-023", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-024", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-025", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-026", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-027", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-028", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-029", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-030", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-031", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-032", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-033", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-034", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-035", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-036", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-037", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-038", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-039", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-040", new Random().nextInt(100) + 1));
        dsk.add(new KhoSach("MS-041", new Random().nextInt(100) + 1));
        /*
        *===============================
        *         THÊM THẺ MƯỢN
        *===============================
         */
        dsmt.add(new MuonTra("MT-001", dsdg.getListDocGia()[0]));
        dsmt.add(new MuonTra("MT-002", dsdg.getListDocGia()[1]));
        dsmt.add(new MuonTra("MT-003", dsdg.getListDocGia()[2]));
        dsmt.add(new MuonTra("MT-004", dsdg.getListDocGia()[3]));
        dsmt.add(new MuonTra("MT-005", dsdg.getListDocGia()[4]));
        dsmt.add(new MuonTra("MT-006", dsdg.getListDocGia()[5]));
        dsmt.add(new MuonTra("MT-007", dsdg.getListDocGia()[6]));
        dsmt.add(new MuonTra("MT-008", dsdg.getListDocGia()[7]));
        dsmt.add(new MuonTra("MT-009", dsdg.getListDocGia()[8]));
        dsmt.add(new MuonTra("MT-010", dsdg.getListDocGia()[9]));
        dsmt.add(new MuonTra("MT-011", dsdg.getListDocGia()[10]));
        dsmt.add(new MuonTra("MT-012", dsdg.getListDocGia()[11]));
        dsmt.add(new MuonTra("MT-013", dsdg.getListDocGia()[12]));
        dsmt.add(new MuonTra("MT-014", dsdg.getListDocGia()[13]));
        dsmt.add(new MuonTra("MT-015", dsdg.getListDocGia()[14]));
        dsmt.add(new MuonTra("MT-016", dsdg.getListDocGia()[15]));
        dsmt.add(new MuonTra("MT-017", dsdg.getListDocGia()[16]));
        dsmt.add(new MuonTra("MT-018", dsdg.getListDocGia()[17]));
        dsmt.add(new MuonTra("MT-019", dsdg.getListDocGia()[18]));
        dsmt.add(new MuonTra("MT-020", dsdg.getListDocGia()[19]));
    }
}
