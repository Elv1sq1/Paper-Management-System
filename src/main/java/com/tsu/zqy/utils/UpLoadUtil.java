package com.tsu.zqy.utils;

import java.io.File;
import java.io.FileOutputStream;

public class UpLoadUtil {
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        String name = filePath + fileName;
        FileOutputStream out = new FileOutputStream(name);
        out.write(file);
        out.flush();
        out.close();
    }

}
