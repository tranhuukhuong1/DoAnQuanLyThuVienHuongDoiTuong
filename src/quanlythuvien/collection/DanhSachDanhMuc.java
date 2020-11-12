/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.collection;

import java.util.Scanner;
import quanlythuvien.model.DanhMuc;

/**
 *
 * @author Admin
 */
public class DanhSachDanhMuc implements DanhSach {

    private DanhMuc listDanhMuc[];
    private int length;

    public DanhSachDanhMuc() {
        listDanhMuc = new DanhMuc[100];
        length = 0;
    }

    public DanhSachDanhMuc(DanhMuc[] listDanhMuc) {
        this.listDanhMuc = listDanhMuc;
    }

    public DanhMuc[] getListDanhMuc() {
        return listDanhMuc;
    }

    public void setListDanhMuc(DanhMuc[] listDanhMuc) {
        this.listDanhMuc = listDanhMuc;
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
        System.out.println("============================================");
        System.out.printf("\t%-15s %-15s\n", "Mã Danh mục", "Tên Danh mục");
        System.out.println("--------------------------------------------");
        int index = 0;
        while (listDanhMuc[index] != null) {
            listDanhMuc[index].xuat();
            index++;
        }
    }

    @Override
    public void them(Object o) {
        resetLength();
        DanhMuc newDM = (DanhMuc) o;
        listDanhMuc[length++] = newDM;
    }

    @Override
    public void sua() {
        resetLength();
        System.out.println("============================================");
        System.out.println("\t   SỬA THÔNG TIN DANH MỤC");
        xuat();
        System.out.print("Nhập mã Danh mục cần sửa: ");
        String maDanhMuc = new Scanner(System.in).nextLine();
        int index = 0;
        while (listDanhMuc[index] != null) {
            if (listDanhMuc[index].getMaDanhMuc().equalsIgnoreCase(maDanhMuc)) {
                DanhMuc dm = new DanhMuc();
                dm.nhap(maDanhMuc);
                listDanhMuc[index] = dm;
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
        System.out.println("\tXOÁ THÔNG TIN DANH MỤC");
        xuat();
        System.out.print("Nhập mã Danh mục muốn xoá: ");
        String maDanhMuc = new Scanner(System.in).nextLine();

        if (contains(maDanhMuc) > -1) {
            int pos = contains(maDanhMuc);
            for (int i = pos; i <= length - 2; i++) {
                listDanhMuc[i] = listDanhMuc[i + 1];
            }
            length--;
            listDanhMuc[length] = null;
            System.out.println("Xoá thành công! Enter để tiếp tục...");
            new Scanner(System.in).nextLine();
            return;
        }

        if (contains(maDanhMuc) == -1) {
            System.err.println("Mã không tồn tại! Enter để tiếp tục...");
            new Scanner(System.in).nextLine();
        }

    }

    public void xoa(String maDanhMuc) {
        if (contains(maDanhMuc) > -1) {
            int pos = contains(maDanhMuc);
            for (int i = pos; i <= length - 2; i++) {
                listDanhMuc[i] = listDanhMuc[i + 1];
            }
            length--;
            listDanhMuc[length] = null;
            System.out.println("Xoá thành công [" + maDanhMuc.toUpperCase() + "]! Enter để tiếp tục...");
            new Scanner(System.in).nextLine();
            return;
        }
        if (contains(maDanhMuc) == -1) {
            System.err.println("Mã không tồn tại! Enter để tiếp tục...");
            new Scanner(System.in).nextLine();
        }
    }

    @Override
    public void timKiem() {
        resetLength();
        System.out.println("============================================");
        System.out.println("\tTÌM THÔNG TIN DANH MỤC");
        System.out.print("Nhập mã Danh mục cần tìm: ");
        String maDanhMuc = new Scanner(System.in).nextLine();
        int pos = contains(maDanhMuc);
        if (pos == -1) {
            System.out.println("Không tìm thấy!");
        } else {
            System.out.print("Tìm thấy: ");
            System.out.printf("\t%-15s %-15s\n", listDanhMuc[pos].getMaDanhMuc(), listDanhMuc[pos].getTenDanhMuc());
        }
        System.out.println("Enter để tiếp tục...");
        new Scanner(System.in).nextLine();
    }

    @Override
    public void sapXep() {
        resetLength();
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (listDanhMuc[i].getMaDanhMuc().compareToIgnoreCase(listDanhMuc[j].getMaDanhMuc()) > 0) {
                    DanhMuc temp = listDanhMuc[i];
                    listDanhMuc[i] = listDanhMuc[j];
                    listDanhMuc[j] = temp;
                }
            }
        }
    }

    public int contains(String maDanhMuc) {
        resetLength();
        for (int i = 0; i < length; i++) {
            if (listDanhMuc[i].getMaDanhMuc().equalsIgnoreCase(maDanhMuc)) {
                return i;
            }
        }
        return -1;
    }

    public void resetLength() {
        length = 0;
        if (listDanhMuc[length] == null) {
            return;
        }
        while (listDanhMuc[length] != null) {
            length++;
        }
    }

}
