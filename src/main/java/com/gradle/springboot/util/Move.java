package com.gradle.springboot.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Move {

    public static void copy(File sourceF, File targetF){
        File[] target_file = sourceF.listFiles();
        for (File file : target_file) {
            System.out.println("targetF.getAbsolutePath()::"+targetF.getAbsolutePath());
            // File temp = new File(targetF.getAbsolutePath() + File.separator + file.getName());
            File temp = new File(targetF.getAbsolutePath() + File.separator + file.getName());
            if(file.isDirectory()){
                temp.mkdir();
                copy(file, temp);
            } else {
                try (
                    FileInputStream fis = new FileInputStream(file);
                    FileOutputStream fos = new FileOutputStream(temp);
                ){
                    byte[] b = new byte[4096];
                    int cnt = 0;
                    while((cnt=fis.read(b)) != -1){
                        fos.write(b, 0, cnt);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void delete(String path) {

        File folder = new File(path);
        try {
            if(folder.exists()){
                File[] folder_list = folder.listFiles();

                for (int i = 0; i < folder_list.length; i++) {
                    if(folder_list[i].isFile()) {
                        folder_list[i].delete();
                    }else {
                        delete(folder_list[i].getPath());
                    }
                    folder_list[i].delete();
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
