package org.example.base.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static void deleteFile(String path) {
        try {
            Files.delete(Paths.get(path));
        } catch (IOException e) {
            System.out.println("파일 삭제 실패");
            // 로그 남기기
        }
    }

    public static boolean isExistFile(String path) {
        File file = new File(path);
        return file.exists();
    }


    public static List<File> getFiles(String path) {
        File folder = new File(path);
        File[] files = folder.listFiles();
        List<File> result = new ArrayList<>();

        if(files == null) {
            return result;
        }

        for(File file : files) {
            if(!file.isDirectory()) {
                result.add(file);
            }
        }
        return result;
    }
}
