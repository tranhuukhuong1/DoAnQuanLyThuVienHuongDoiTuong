/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 *
<<<<<<< HEAD
 * @author Admin
=======
 * @author huukhuong
>>>>>>> origin/main
 */
public class DocGhiTextFile {

    public static boolean ghiFile(ArrayList<String> dsData, String path) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            for (String data : dsData) {
                if (data.length() > 0) {
                    bw.write(data);
                }
                bw.newLine();
            }
            bw.close();
            osw.close();
            fos.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<String> docFile(String path) {
        ArrayList<String> dsData = new ArrayList<String>();
        try {
            FileInputStream fin = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fin, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String next = br.readLine();
            while (next != null) {
                if (next.length() > 0) {
                    dsData.add(next);
                    next = br.readLine();
                }
            }
            br.close();
            isr.close();
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsData;
    }
}
