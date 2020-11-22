/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.service;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class CheckError {

    public static int ChuoiThanhSo() {
        boolean flag = false;
        String inp;
        do {
            String pattern = "\\d*";
            inp = new Scanner(System.in).next();
            flag = inp.matches(pattern);
            if (!flag) {
                System.err.println("Không hợp lệ! Nhập lại...");
            }
        } while (!flag);
        return Integer.parseInt(inp);
    }

    public static String checkMa(String pattern) {
        boolean flag = false;
        String inp;
        do {
            String form = pattern + "\\d*";
            inp = new Scanner(System.in).next().toUpperCase();
            flag = inp.matches(form);
            if (!flag) {
                System.err.println("Không hợp lệ! Nhập lại...");
            }
        } while (!flag);
        return inp;
    }
}
