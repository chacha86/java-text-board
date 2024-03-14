package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardApp {
    ArticleRepository articleRepository = new ArticleRepository();
    Scanner scan = new Scanner(System.in);
    public void run() {
        articleRepository.makeTestData();
        while (true) { // 반복 조건이 true이기 때문에 무한 반복

            System.out.print("명령어 : ");
            String cmd = scan.nextLine();
            if(cmd.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            switch (cmd) {
                case "add" -> articleRepository.add();
                case "list" -> articleRepository.list();
                case "update" -> articleRepository.update();
                case "delete" -> articleRepository.delete();
                case "detail" -> articleRepository.detail();
                case "search" -> articleRepository.search();
                default -> System.out.println("올바른 명령어가 아닙니다.");
            }
        }
    }





    // 입력 : 찾고자 하는 게시물 번호
    // 출력 : 해당 게시물 번호의 게시물


}
