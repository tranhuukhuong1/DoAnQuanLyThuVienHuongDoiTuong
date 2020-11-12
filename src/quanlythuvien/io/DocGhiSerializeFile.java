/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
<<<<<<< HEAD
 * @author Admin
=======
 * @author huukhuong
>>>>>>> origin/main
 */
public class DocGhiSerializeFile {

    // lưu file
    public static boolean ghiFile(Object data, String path) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
            fos.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // đọc file
    public static Object docFile(String path) {
        try {
            FileInputStream fin = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fin);
            Object data = ois.readObject();
            ois.close();
            fin.close();
            return data;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
