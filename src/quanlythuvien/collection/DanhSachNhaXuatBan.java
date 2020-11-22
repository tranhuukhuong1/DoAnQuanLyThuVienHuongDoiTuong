/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.collection;

import java.util.Scanner;
import quanlythuvien.model.NhaXuatBan;
import quanlythuvien.service.CheckError;

/**
 *
 * @author Admin
 */
public class DanhSachNhaXuatBan implements DanhSach {

    private NhaXuatBan listNXB[];
    private int length;

    public DanhSachNhaXuatBan() {
        listNXB = new NhaXuatBan[100];
        length = 0;
    }

    public DanhSachNhaXuatBan(NhaXuatBan[] listNXB) {
        this.listNXB = listNXB;
    }

    public NhaXuatBan[] getListNXB() {
        return listNXB;
    }

    public void setListNXB(NhaXuatBan[] listNXB) {
        this.listNXB = listNXB;
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
        System.out.printf("\t%-15s %-15s\n", "Mã NXB", "Tên NXB");
        System.out.println("--------------------------------------------");
        int index = 0;
        while (listNXB[index] != null) {
            listNXB[index].xuat();
            index++;
        }
    }

    @Override
    public void them(Object o) {
        resetLength();
        NhaXuatBan newNXB = (NhaXuatBan) o;
        if (contains(newNXB.getMaNXB()) != -1) {
            System.err.println("Lỗi: NXB này đã được tạo từ trước!");
            new Scanner(System.in).nextLine();
            return;
        }
        listNXB[length++] = newNXB;
    }

    @Override
    public void sua() {
        resetLength();
        System.out.println("============================================");
        System.out.println("\t   SỬA THÔNG TIN NXB");
        xuat();
        System.out.print("Nhập mã NXB cần sửa: ");
        String maNXB = CheckError.checkMa("NXB-");
        int index = 0;
        while (listNXB[index] != null) {
            if (listNXB[index].getMaNXB().equalsIgnoreCase(maNXB)) {
                NhaXuatBan nxb = new NhaXuatBan();
                nxb.nhap(maNXB);
                listNXB[index] = nxb;
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
        System.out.println("\tXOÁ THÔNG TIN NXB");
        xuat();
        System.out.print("Nhập mã NXB muốn xoá: ");
        String maNXB = CheckError.checkMa("NXB-");

        if (contains(maNXB) > -1) {
            int pos = contains(maNXB);
            for (int i = pos; i <= length - 2; i++) {
                listNXB[i] = listNXB[i + 1];
            }
            length--;
            listNXB[length] = null;
            System.out.println("Xoá thành công! Enter để tiếp tục...");
            new Scanner(System.in).nextLine();
            return;
        }

        if (contains(maNXB) == -1) {
            System.err.println("Mã không tồn tại! Enter để tiếp tục...");
            new Scanner(System.in).nextLine();
        }

    }

    public void xoa(String maNXB) {
        resetLength();
        if (contains(maNXB) > -1) {
            int pos = contains(maNXB);
            for (int i = pos; i <= length - 2; i++) {
                listNXB[i] = listNXB[i + 1];
            }
            length--;
            listNXB[length] = null;
            System.out.println("Xoá thành công! Enter để tiếp tục...");
            new Scanner(System.in).nextLine();
            return;
        }

        if (contains(maNXB) == -1) {
            System.err.println("Mã không tồn tại! Enter để tiếp tục...");
            new Scanner(System.in).nextLine();
        }

    }

    @Override
    public void timKiem() {
        resetLength();
        System.out.println("============================================");
        System.out.println("\tTÌM THÔNG TIN NXB");
        System.out.print("Nhập mã NXB cần tìm: ");
        String maNXB = CheckError.checkMa("NXB-");
        int pos = contains(maNXB);
        if (pos == -1) {
            System.out.println("Không tìm thấy!");
        } else {
            System.out.print("Tìm thấy: ");
            System.out.printf("\t%-15s %-15s\n", listNXB[pos].getMaNXB(), listNXB[pos].getTenNXB());
        }
        System.out.println("Enter để tiếp tục...");
        new Scanner(System.in).nextLine();
    }

    @Override
    public void sapXep() {
        resetLength();
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (listNXB[i].getMaNXB().compareToIgnoreCase(listNXB[j].getMaNXB()) > 0) {
                    NhaXuatBan temp = listNXB[i];
                    listNXB[i] = listNXB[j];
                    listNXB[j] = temp;
                }
            }
        }
    }

    public int contains(String tenNXB) {
        resetLength();
        for (int i = 0; i < length; i++) {
            if (listNXB[i].getMaNXB().equalsIgnoreCase(tenNXB)) {
                return i;
            }
        }
        return -1;
    }

    public void resetLength() {
        if (length == 0) {
            while (listNXB[length] != null) {
                length++;
            }
        }
    }

}
