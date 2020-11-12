/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.collection;

import java.util.Scanner;
import quanlythuvien.model.NgonNgu;

/**
 *
 * @author Admin
 */
public class DanhSachNgonNgu implements DanhSach {

    private NgonNgu listNgonNgu[];
    private int length;

    public DanhSachNgonNgu() {
        listNgonNgu = new NgonNgu[100];
        length = 0;
    }

    public DanhSachNgonNgu(NgonNgu[] listNgonNgu) {
        this.listNgonNgu = listNgonNgu;
    }

    public NgonNgu[] getListNgonNgu() {
        return listNgonNgu;
    }

    public void setListNgonNgu(NgonNgu[] listNgonNgu) {
        this.listNgonNgu = listNgonNgu;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public void xuat() {
        resetLength();
        System.out.println("============================================");
        System.out.printf("\t%-15s %-15s\n", "Mã Ngôn ngữ", "Tên Ngôn ngữ");
        System.out.println("--------------------------------------------");
        int index = 0;
        while (listNgonNgu[index] != null) {
            listNgonNgu[index].xuat();
            index++;
        }
    }

    @Override
    public void them(Object o) {
        resetLength();
        NgonNgu newNN = (NgonNgu) o;
        if (contains(newNN.getMaNgonNgu()) != -1) {
            System.err.println("Lỗi: Ngôn ngữ này đã được tạo từ trước!");
            new Scanner(System.in).nextLine();
            return;
        }
        listNgonNgu[length++] = newNN;
    }

    @Override
    public void sua() {
        resetLength();
        System.out.println("============================================");
        System.out.println("\t   SỬA THÔNG TIN NGÔN NGỮ");
        xuat();
        System.out.print("Nhập mã Ngôn ngữ cần sửa: ");
        String maNgonNgu = new Scanner(System.in).nextLine();
        int index = 0;
        while (listNgonNgu[index] != null) {
            if (listNgonNgu[index].getMaNgonNgu().equalsIgnoreCase(maNgonNgu)) {
                NgonNgu nn = new NgonNgu();
                nn.nhap();
                listNgonNgu[index] = nn;
                System.out.println("Sửa thành công! Enter để tiếp tục...");
                new Scanner(System.in).nextLine();
                return;
            }
            index++;
        }
        System.err.println("Mã không tồn tại! Enter để tiếp tục...");
        new Scanner(System.in).nextLine();
    }

    @Override
    public void xoa() {
        resetLength();
        System.out.println("============================================");
        System.out.println("\tXOÁ THÔNG TIN NGÔN NGỮ");
        xuat();
        System.out.print("Nhập mã Ngôn ngữ muốn xoá: ");
        String maNgonNgu = new Scanner(System.in).nextLine();

        if (contains(maNgonNgu) > -1) {
            int pos = contains(maNgonNgu);
            for (int i = pos; i <= length - 2; i++) {
                listNgonNgu[i] = listNgonNgu[i + 1];
            }
            length--;
            listNgonNgu[length] = null;
            System.out.println("Xoá thành công! Enter để tiếp tục...");
            new Scanner(System.in).nextLine();
            return;
        }

        if (contains(maNgonNgu) == -1) {
            System.err.println("Mã không tồn tại! Enter để tiếp tục...");
            new Scanner(System.in).nextLine();
        }

    }

    @Override
    public void timKiem() {
        resetLength();
        System.out.println("============================================");
        System.out.println("\tTÌM THÔNG TIN NGÔN NGỮ");
        System.out.print("Nhập mã Ngôn ngữ cần tìm: ");
        String maNgonNgu = new Scanner(System.in).nextLine();
        int pos = contains(maNgonNgu);
        if (pos == -1) {
            System.out.println("Không tìm thấy!");
        } else {
            System.out.print("Tìm thấy: ");
            System.out.printf("\t%-15s %-15s\n", listNgonNgu[pos].getMaNgonNgu(), listNgonNgu[pos].getTenNgonNgu());
        }
        System.out.println("Enter để tiếp tục...");
        new Scanner(System.in).nextLine();
    }

    @Override
    public void sapXep() {
    }

    public int contains(String maNgonNgu) {
        resetLength();
        for (int i = 0; i < length; i++) {
            if (listNgonNgu[i].getMaNgonNgu().equalsIgnoreCase(maNgonNgu)) {
                return i;
            }
        }
        return -1;
    }

    public void resetLength() {
        if (length == 0) {
            while (listNgonNgu[length] != null) {
                length++;
            }
        }
    }
}
