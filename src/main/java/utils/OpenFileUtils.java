package utils;

import com.sun.javafx.tools.packager.Main;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Author:  andy.xwt
 * Date:    2017/12/4 14:35
 * Description: 打开文件帮助类
 */


class OpenFileUtils {


    private static Desktop sDesktop;

    static {
        sDesktop = Desktop.getDesktop();
    }


    /**
     * 打开文件
     *
     * @param file 文件对象
     */
    public static void openFile(File file) {
        EventQueue.invokeLater(() -> {
            try {
                sDesktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(Main.
                        class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        });
    }
}
