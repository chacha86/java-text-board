package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardApp {
    ArticleRepository articleRepository = new ArticleRepository();
    Scanner scan = new Scanner(System.in);
    int latestArticleId = 4; // 테스트 데이터 3개 있으므로 시작 번호를 4로 지정
    int WRONG_VALUE = -1; // 값의 의미를 부여

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




    public void printArticleList(ArrayList<Article> targetList) {

        System.out.println("===================");
        for (int i = 0; i < targetList.size(); i++) {

            Article article = targetList.get(i);

            System.out.println("번호 : " + article.getId());
            System.out.printf("제목 : %s\n", article.getTitle());
            System.out.println("===================");
        }
    }

    // 입력 : 찾고자 하는 게시물 번호
    // 출력 : 해당 게시물 번호의 게시물

    public String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();

        // 날짜와 시간의 형식을 지정합니다. 여기서는 연-월-일 시:분:초 형식을 사용합니다.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

        // 지정한 형식으로 날짜와 시간을 출력합니다.
        String formattedDate = now.format(formatter);

        return formattedDate;
    }

    private int getParamAsInt(String param, int defaultValue) {
        try {
            return Integer.parseInt(param);
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return defaultValue;
        }
    }
}
