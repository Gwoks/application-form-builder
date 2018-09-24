package com.bermanfaat.formbuilder.util;

import java.io.File;

public class DirectoryUtil {

    public static void createDirectory(String path) {
        File createDir = new File(path);
        createDir.mkdirs();
    }

    public static void removeAllFilesinDirectory(String path, String fieldName) {
        File dir = new File(path);
        String[] myFiles;
        if (dir.isDirectory()) {
            myFiles = dir.list();
            for (int i = 0; i < myFiles.length; i++) {
                File myFile = new File(dir, myFiles[i]);
//                if (myFile.getName().startsWith(fieldName)) {
                    myFile.delete();
//                }
            }
        }
    }
}
