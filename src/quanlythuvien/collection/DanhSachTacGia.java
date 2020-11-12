/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.collection;

import java.util.Scanner;
import quanlythuvien.model.TacGia;

/**
 *
 * @author Admin
 */
public class DanhSachTacGia implements DanhSach {

    private TacGia listTacGia[] = new TacGia[1000];
    private int length;

    public DanhSachTacGia() {
    }

    public DanhSachTacGia(TacGia[] listTacGia, int length) {
        this.listTacGia = listTacGia;
        this.length = length;
    }

    public TacGia[] getListTacGia() {
        return listTacGia;
    }

    public void setListTacGia(TacGia[] listTacGia) {
        this.listTacGia = listTacGia;
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
        System.out.println("============================================================");
        System.out.printf("\t%-15s %-20s %-10s\n",
                "Mã Tác giả", "Họ tên", "Năm sinh");
        System.out.println("------------------------------------------------------------");
        for (int i = 0; i < length; i++) {
            listTacGia[i].xuat();
        }
    }

    @Override
    public void them(Object o) {
        resetLength();
        TacGia tg = (TacGia) o;
        listTacGia[length++] = tg;
    }

    @Override
    public void sua() {
        resetLength();
        System.out.println("============================================");
        System.out.println("\t   SỬA THÔNG TIN TÁC GIẢ");
        xuat();
        System.out.print("Nhập mã Tác giả cần sửa: ");
        String maTacGia = new Scanner(System.in).nextLine();
        int index = contains(maTacGia);
        if (index > -1) {
            listTacGia[index].nhap(maTacGia);
        } else {
            System.err.println("Mã không tồn tại! Enter để tiếp tục...");
            new Scanner(System.in).nextLine();
        }
    }

    @Override
    public void xoa() {
        resetLength();
        System.out.println("============================================");
        System.out.println("\t   XOÁ THÔNG TIN TÁC GIẢ");
        xuat();
        System.out.print("Nhập mã Tác giả cần xoá: ");
        String maTacGia = new Scanner(System.in).nextLine();
        if (contains(maTacGia) > -1) {
            int pos = contains(maTacGia);
            for (int i = pos; i <= length - 2; i++) {
                listTacGia[i] = listTacGia[i + 1];
            }
            length--;
            listTacGia[length] = null;
            System.out.println("Xoá thành công! Enter để tiếp tục...");
            new Scanner(System.in).nextLine();
            return;
        }
        System.out.println("Mã Tác giả không tồn tại! Enter để tiếp tục...");
        new Scanner(System.in).nextLine();
    }

    @Override
    public void timKiem() {
    }

    @Override
    public void sapXep() {
        resetLength();
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (listTacGia[i].getMaTacGia().compareToIgnoreCase(listTacGia[j].getMaTacGia()) > 0) {
                    TacGia temp = listTacGia[i];
                    listTacGia[i] = listTacGia[j];
                    listTacGia[j] = temp;
                }
            }
        }
    }

    public void resetLength() {
        length = 0;
        if (listTacGia[0] == null) {
            length = 0;
            return;
        }
        while (listTacGia[length] != null) {
            length++;
        }
    }

    public int contains(String maTacGia) {
        resetLength();
        if (length == 0) {
            return -1;
        }
        for (int i = 0; i < length; i++) {
            if (listTacGia[i].getMaTacGia().equalsIgnoreCase(maTacGia)) {
                return i;
            }
        }
        return -1;
    }
}
