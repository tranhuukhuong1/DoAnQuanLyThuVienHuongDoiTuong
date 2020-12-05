/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.collection;

import java.util.Scanner;
import quanlythuvien.main.Main;
import quanlythuvien.model.Sach;
import quanlythuvien.service.CheckError;

/**
 *
 * @author Admin
 */
public class DanhSachSach implements DanhSach {

    private Sach listSach[] = new Sach[1000];
    private int length;

    public DanhSachSach() {
        listSach = new Sach[1000];
    }

    public DanhSachSach(Sach[] listSach, int length) {
        this.listSach = listSach;
        this.length = length;
    }

    public Sach[] getListSach() {
        return listSach;
    }

    public void setListSach(Sach[] listSach) {
        this.listSach = listSach;
    }

    public int getLength() {
        resetLength();
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public void xuat() {
        sapXep();
        System.out.println("==================================================================================================");
        System.out.printf("%-10s %-10s %-30s %-15s %-10s %-7s %-10s\n",
                "Mã sách", "Danh mục", "Tên sách", "Tác giả", "NXB", "Năm XB", "Ngôn ngữ");
        System.out.println("--------------------------------------------------------------------------------------------------");
        int i = 0;
        while (listSach[i] != null) {
            listSach[i].xuat();
            i++;
        }
    }

    @Override
    public void them(Object o) {
        resetLength();
        Sach s = (Sach) o;
        listSach[length++] = s;
    }

    @Override
    public void sua() {
        resetLength();
        System.out.println("============================================");
        System.out.println("\t   SỬA THÔNG TIN SÁCH");
        xuat();
        System.out.print("Nhập mã Sách cần sửa: ");
        String maSach = CheckError.checkMa("MS-");
        int index = contains(maSach);
        if (index > -1) {
            listSach[index].nhap(maSach);
        } else {
            System.err.println("Mã không tồn tại! Enter để tiếp tục...");
            new Scanner(System.in).nextLine();
        }
    }

    @Override
    public void xoa() {
        resetLength();
        System.out.println("============================================");
        System.out.println("\t   XOÁ THÔNG TIN SÁCH");
        xuat();
        System.out.print("Nhập mã Sách cần xoá: ");
        String maSach = CheckError.checkMa("MS-");
        if (contains(maSach) > -1) {
            int pos = contains(maSach);
            for (int i = pos; i <= length - 2; i++) {
                listSach[i] = listSach[i + 1];
            }
            length--;
            listSach[length] = null;
            System.out.println("Xoá thành công! Enter để tiếp tục...");
            new Scanner(System.in).nextLine();
            return;
        }
        System.out.println("Mã sách không tồn tại! Enter để tiếp tục...");
        new Scanner(System.in).nextLine();
    }

    public void xoa(String maSach) {
        resetLength();
        if (contains(maSach) > -1) {
            int pos = contains(maSach);
            for (int i = pos; i <= length - 2; i++) {
                listSach[i] = listSach[i + 1];
            }
            length--;
            listSach[length] = null;
            System.out.println("Xoá thành công! Enter để tiếp tục...");
            new Scanner(System.in).nextLine();
            return;
        }
        System.out.println("Mã sách không tồn tại! Enter để tiếp tục...");
        new Scanner(System.in).nextLine();
    }

    @Override
    public void timKiem() {
        resetLength();
        System.out.println("============================================");
        System.out.println("||             TÌM KIẾM SÁCH              ||");
        System.out.println("============================================");
        System.out.println("||  1  ||   Tìm theo Tên                  ||");
        System.out.println("||  2  ||   Tìm theo Tác giả              ||");
        System.out.println("||  3  ||   Tìm theo Danh mục             ||");
        System.out.println("============================================");
        System.out.print("Mời chọn chức năng: ");
        int cp = CheckError.ChuoiThanhSo();
        switch (cp) {
            case 1:
                timTheoTen();
                break;
            case 2:
                timTheoTacGia();
                break;
            case 3:
                timTheoDanhMuc();
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
                if (listSach[i].getMaSach().compareToIgnoreCase(listSach[j].getMaSach()) > 0) {
                    Sach temp = listSach[i];
                    listSach[i] = listSach[j];
                    listSach[j] = temp;
                }
            }
        }
    }

    public int contains(String maSach) {
        resetLength();
        if (length == 0) {
            return -1;
        }
        for (int i = 0; i < length; i++) {
            if (listSach[i].getMaSach().equalsIgnoreCase(maSach)) {
                return i;
            }
        }
        return -1;
    }

    public void resetLength() {
        length = 0;
        if (listSach[0] == null) {
            length = 0;
            return;
        }
        while (listSach[length] != null) {
            length++;
        }
    }

    private void timTheoTen() {
        System.out.println("Nhập từ khoá: ");
        String key = new Scanner(System.in).nextLine();
        System.out.println("==================================================================================================");
        System.out.printf("%-10s %-10s %-30s %-15s %-10s %-7s %-10s\n",
                "Mã Sách", "Danh mục", "Tên sách", "Tác giả", "NXB", "Năm XB", "Ngôn ngữ");
        System.out.println("--------------------------------------------------------------------------------------------------");
        for (int i = 0; i < length; i++) {
            if (listSach[i].getTenSach().toLowerCase().contains(key.toLowerCase())) {
                listSach[i].xuat();
            }
        }
        System.out.println("Enter để tiếp tục...");
        new Scanner(System.in).nextLine();
    }

    private void timTheoTacGia() {
        Main.dstg.xuat();
        String key;
        System.out.println("Nhập mã Tác giả cần lọc: ");
        do {
            key = new Scanner(System.in).nextLine();
            if (Main.dstg.contains(key) == -1) {
                System.out.println("Mã không tồn tại! Nhập lại:");
            }
        } while (key.length() == 0 || Main.dstg.contains(key) == -1);
        System.out.println("==================================================================================================");
        System.out.printf("%-10s %-10s %-30s %-15s %-10s %-7s %-10s\n",
                "Mã Sách", "Danh mục", "Tên sách", "Tác giả", "NXB", "Năm XB", "Ngôn ngữ");
        System.out.println("--------------------------------------------------------------------------------------------------");
        for (int i = 0; i < length; i++) {
            if (listSach[i].getMaTacGia().equalsIgnoreCase(key)) {
                listSach[i].xuat();
            }
        }
        System.out.println("Enter để tiếp tục...");
        new Scanner(System.in).nextLine();
    }

    private void timTheoDanhMuc() {
        Main.dsdm.xuat();
        String key;
        System.out.println("Nhập mã Danh mục cần lọc: ");
        do {
            key = new Scanner(System.in).nextLine();
            if (Main.dsdm.contains(key) == -1) {
                System.out.println("Mã không tồn tại! Nhập lại:");
            }
        } while (key.length() == 0 || Main.dsdm.contains(key) == -1);
        System.out.println("==================================================================================================");
        System.out.printf("%-10s %-10s %-30s %-15s %-10s %-7s %-10s\n",
                "Mã Sách", "Danh mục", "Tên sách", "Tác giả", "NXB", "Năm XB", "Ngôn ngữ");
        System.out.println("--------------------------------------------------------------------------------------------------");
        for (int i = 0; i < length; i++) {
            if (listSach[i].getMaDanhMuc().equalsIgnoreCase(key)) {
                listSach[i].xuat();
            }
        }
        System.out.println("Enter để tiếp tục...");
        new Scanner(System.in).nextLine();
    }

}
