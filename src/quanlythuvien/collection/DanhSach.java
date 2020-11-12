/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.collection;

/**
 *
 * @author Admin
 */
public interface DanhSach {

    void xuat();

    void them(Object o);

    void sua();

    void xoa();

    void timKiem();

    void sapXep();

    int contains(String ma);
    
    void resetLength();
}
