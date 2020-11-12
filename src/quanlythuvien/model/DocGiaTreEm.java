package quanlythuvien.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Date;

/**
 *
 * @author Admin
 */
public class DocGiaTreEm extends DocGia {

    private static final long serialVersionUID = -1305514586928259185L;
    private String nguoiDaiDien;

    public DocGiaTreEm() {
    }

    public DocGiaTreEm(String nguoiDaiDien) {
        this.nguoiDaiDien = nguoiDaiDien;
    }

    public DocGiaTreEm(String nguoiDaiDien, String maDocGia, Date ngayLamThe, int hanThe, String hoTen, String gioiTinh, int namSinh) {
        super(maDocGia, ngayLamThe, hanThe, hoTen, gioiTinh, namSinh);
        this.nguoiDaiDien = nguoiDaiDien;
    }

    public String getNguoiDaiDien() {
        return nguoiDaiDien;
    }

    public void setNguoiDaiDien(String nguoiDaiDien) {
        this.nguoiDaiDien = nguoiDaiDien;
    }

    @Override
    public int tienLamThe() {
        return 300 * this.getHanThe();
    }

}
