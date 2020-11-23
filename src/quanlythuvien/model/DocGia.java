/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import quanlythuvien.main.Main;
import quanlythuvien.service.CheckError;
import quanlythuvien.service.ChuanHoaChuoi;

/**
 *
 * @author Admin
 */
public abstract class DocGia extends Person {

    private static final long serialVersionUID = -2381078016822908848L;
    private String maDocGia;
    private Date ngayLamThe;
    private int hanThe;

    public DocGia() {
    }

    public DocGia(String maDocGia, Date ngayLamThe, int hanThe, String hoTen, String gioiTinh, int namSinh) {
        super(ChuanHoaChuoi.chuanHoa(hoTen), ChuanHoaChuoi.chuanHoa(gioiTinh), namSinh);
        this.maDocGia = maDocGia.toUpperCase();
        this.ngayLamThe = ngayLamThe;
        this.hanThe = hanThe;
    }

    public String getMaDocGia() {
        return maDocGia;
    }

    public void setMaDocGia(String maDocGia) {
        this.maDocGia = maDocGia;
    }

    public Date getNgayLamThe() {
        return ngayLamThe;
    }

    public void setNgayLamThe(Date ngayLamThe) {
        this.ngayLamThe = ngayLamThe;
    }

    public int getHanThe() {
        return hanThe;
    }

    public void setHanThe(int hanThe) {
        this.hanThe = hanThe;
    }

    public abstract int tienLamThe();

    @Override
    public void nhap() {
        super.nhap();
        String maDocGia;
        System.out.println("Cú pháp nhập mã: DG-(số_thứ_tự)");
        System.out.print("Nhập mã Độc giả: ");
        do {
            maDocGia = CheckError.checkMa("DG-");
            if (Main.dsdg.contains(maDocGia) != -1) {
                System.err.println("Mã độc giả đã tồn tại! Nhập lại...");
            }
        } while (maDocGia.length() == 0 || Main.dsdg.contains(maDocGia) != -1);

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String s = sdf.format(d.getTime());
        Date ngayLamThe = null;
        try {
            ngayLamThe = sdf.parse(s);
        } catch (ParseException ex) {
            Logger.getLogger(DocGia.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Ngày làm thẻ: " + sdf.format(ngayLamThe));
        System.out.print("Nhập hạn thẻ (số ngày): ");
        int hanThe;
        do {
            hanThe = CheckError.ChuoiThanhSo();
            if (hanThe <= 0) {
                System.out.println("Nhập số ngày lớn hơn 0!");
            }
        } while (hanThe <= 0);

        this.maDocGia = maDocGia.toUpperCase();
        this.ngayLamThe = ngayLamThe;
        this.hanThe = hanThe;
    }

    public void nhap(String maDocGia) {
        super.nhap();
        System.out.print("Nhập hạn thẻ (số ngày): ");
        int hanThe;
        do {
            hanThe = CheckError.ChuoiThanhSo();
            if (hanThe <= 0) {
                System.out.println("Nhập số ngày lớn hơn 0!");
            }
        } while (hanThe <= 0);

        this.maDocGia = maDocGia.toUpperCase();
        this.ngayLamThe = ngayLamThe;
        this.hanThe = hanThe;
    }

    @Override
    public void xuat() {
    }

}
