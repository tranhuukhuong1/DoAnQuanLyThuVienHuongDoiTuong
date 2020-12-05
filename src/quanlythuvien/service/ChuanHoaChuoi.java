/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.service;

/**
 *
 * @author Admin
 */
public class ChuanHoaChuoi {

    public static String chuanHoa(String name) {
        while (name.indexOf("  ") != -1) {
            name = name.replace("  ", " ");
        }
        name = name.toLowerCase();
        char a[] = name.toCharArray();
        for (int i = 0; i < a.length; i++) {
            if ((i == 0 || a[i - 1] == 32) && a[i] >= 97) {
                a[i] -= 32;
            }
        }
        name = "";
        for (int i = 0; i < a.length; i++) {
            name += a[i];
        }
        return name.trim();
    }
}
