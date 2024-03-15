package org.example.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.base.CommonUtil;
import org.example.domain.Article;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileTest {
    public static void main(String[] args) {

        writeWithJackson();
        readWithJackson();
    }

    public static void writeWithJackson() {
        ObjectMapper mapper = new ObjectMapper();

        Article a1 = new Article(1, "안녕하세요 반갑습니다. 자바 공부중이에요.", "냉무", 0, CommonUtil.getCurrentDateTime());

        try {
            String jsonString = mapper.writeValueAsString(a1);
            System.out.println(jsonString);
            // 파일에 쓰기
            mapper.writeValue(new File("myObject.json"), a1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readWithJackson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // JSON 파일에서 MyObject 객체로 읽기
            Article myObject = mapper.readValue(new File("myObject.json"), Article.class);
            // 읽은 객체 출력
            System.out.println(myObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write() {
        List<String> lines = Arrays.asList("Line 1", "Line 2", "Line 3");
        String filePath = "example.txt";

        try {
            Files.write(Paths.get(filePath), lines);
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void read() {
        String filePath = "example.txt";

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            System.out.println("File content:");
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
