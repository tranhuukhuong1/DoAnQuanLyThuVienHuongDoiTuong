package quanlythuvien.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.util.Scanner;
import quanlythuvien.service.CheckError;
import quanlythuvien.service.ChuanHoaChuoi;

/**
 *
 * @author Admin
 */
public class Person implements ThuVien, Serializable {

    private static final long serialVersionUID = -6778530219900320801L;
    private String hoTen;
    private String gioiTinh;
    private int namSinh;

    public Person() {
    }

    public Person(String hoTen, String gioiTinh, int namSinh) {
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.namSinh = namSinh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    @Override
    public void nhap() {
        String hoTen, gioiTinh;
        int namSinh;
        System.out.println("======================================");
        System.out.print("Nhập họ tên: ");
        do {
            hoTen = new Scanner(System.in).nextLine();
        } while (hoTen.length() == 0);
        System.out.println("Chọn giới tính:");
        System.out.println("1. Nam");
        System.out.println("2. Nữ");
        System.out.println("3. Khác");
        System.out.print("Nhập giới tính: ");
        int gt = 0;
        do {
            gt = CheckError.ChuoiThanhSo();
            if (gt != 1 && gt != 2 && gt != 3) {
                System.out.println("Không hợp lệ! Nhập lại!");
            }
        } while (gt != 1 && gt != 2 && gt != 3);
        gioiTinh = "";
        switch (gt) {
            case 1:
                gioiTinh = "Nam";
                break;
            case 2:
                gioiTinh = "Nữ";
                break;
            case 3:
                gioiTinh = "Khác";
                break;
        }

        System.out.print("Nhập năm sinh: ");
        do {
            namSinh = CheckError.ChuoiThanhSo();
            if (namSinh < 2015) {
                System.out.println("Năm quá nhỏ! Nhập lại...");
            }
        } while (namSinh < 2015);
        
        this.hoTen = ChuanHoaChuoi.chuanHoa(hoTen);
        this.gioiTinh = ChuanHoaChuoi.chuanHoa(gioiTinh);
        this.namSinh = namSinh;
    }

    @Override
    public void xuat() {
    }

}
