package quanlythuvien.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Date;
import quanlythuvien.service.ChuanHoaChuoi;

/**
 *
 * @author Admin
 */
public class DocGiaNguoiLon extends DocGia {

    private static final long serialVersionUID = -938391790352608035L;
    private String CMND;

    public DocGiaNguoiLon() {
    }

    public DocGiaNguoiLon(String CMND) {
        this.CMND = CMND;
    }

    public DocGiaNguoiLon(String CMND, String maDocGia, Date ngayLamThe, int hanThe, String hoTen, String gioiTinh, int namSinh) {
        super(maDocGia, ngayLamThe, hanThe, ChuanHoaChuoi.chuanHoa(hoTen), gioiTinh, namSinh);
        this.CMND = CMND;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    @Override
    public int tienLamThe() {
        return 900 * this.getHanThe();
    }

}
