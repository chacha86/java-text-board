package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardApp {
    ArrayList<Article> articleList = new ArrayList<>(); // 인스턴스 변수


    public void run() {

        Scanner scan = new Scanner(System.in);
        int latestArticleId = 4; // 테스트 데이터 3개 있으므로 시작 번호를 4로 지정

        Article a1 = new Article(1, "안녕하세요 반갑습니다. 자바 공부중이에요.", "냉무", 0, getCurrentDateTime());
        Article a2 = new Article(2, "자바 질문좀 할게요~", "냉무", 0, getCurrentDateTime());
        Article a3 = new Article(3, "정처기 따야되나요?", "냉무", 0, getCurrentDateTime());
        articleList.add(a1);
        articleList.add(a2);
        articleList.add(a3);

        while (true) { // 반복 조건이 true이기 때문에 무한 반복

            System.out.print("명령어 : ");
            String cmd = scan.nextLine();

            if (cmd.equals("exit")) { // 숫자가 아닌 경우 같다라는 표현을 할 때 == 이 아닌 .equals()를 사용해야 한다.
                System.out.println("프로그램을 종료합니다.");
                break; // 반복문 탈출

            } else if (cmd.equals("add")) {

                System.out.print("게시물 제목을 입력해주세요 : ");
                String title = scan.nextLine();

                System.out.print("게시물 내용을 입력해주세요 : ");
                String body = scan.nextLine();
//
//
//                // 기본 생성자 이용
////                Article article = new Article();
////                article.setId(latestArticleId);
////                article.setTitle(title);
////                article.setBody(body);

                // 모든 매개변수를 받는 생성자 이용
                Article article = new Article(latestArticleId, title, body, 0, getCurrentDateTime());

                articleList.add(article);
                System.out.println("게시물이 등록되었습니다.");

                latestArticleId++; // 게시물이 생성될 때마다 번호를 증가

            } else if (cmd.equals("list")) {
                System.out.println("===================");
                for (int i = 0; i < articleList.size(); i++) {
                    Article article = articleList.get(i);

                    System.out.println("번호 : " + article.getId());
                    System.out.printf("제목 : %s\n", article.getTitle());
                    System.out.println("===================");
                }
            } else if (cmd.equals("update")) {
                System.out.print("수정할 게시물 번호를 입력해주세요 : ");
                int inputId = Integer.parseInt(scan.nextLine());

                int index = findIndexById(inputId);
                if (index == -1) {
                    System.out.println("없는 게시물입니다.");
                    continue;
                }

                System.out.print("새로운 제목을 입력해주세요 : ");
                String newTitle = scan.nextLine();

                System.out.print("새로운 내용을 입력해주세요 : ");
                String newBody = scan.nextLine();

                // 변하지 않는 것에서 변하는 것을 분리
                // 변하는 것에서 변하지 않는 것을 분리

                Article target = articleList.get(index);
                target.setTitle(newTitle); // target은 참조값이므로 직접 객체를 접근하여 수정 가능
                target.setBody(newBody);

                System.out.printf("%d번 게시물이 수정되었습니다.\n", inputId);

            } else if (cmd.equals("delete")) {
                System.out.print("삭제할 게시물 번호를 입력해주세요 : ");
                int inputId = Integer.parseInt(scan.nextLine());

                int index = findIndexById(inputId);

                if (index == -1) {
                    System.out.println("없는 게시물입니다.");
                    continue;
                }

                articleList.remove(index);
                System.out.printf("%d 게시물이 삭제되었습니다.\n", inputId);
            } else if (cmd.equals("detail")) {

                System.out.print("상세보기 할 게시물 번호를 입력해주세요 : ");
                int inputId = Integer.parseInt(scan.nextLine());

                int index = findIndexById(inputId);

                if (index == -1) {
                    System.out.println("없는 게시물입니다.");
                    continue;
                }

                Article article = articleList.get(index);

                // 조회수 증가 v1
//                article.setHit(article.getHit() + 1); // 기존의 조회수 가져와서 1 증가.

                // 조회수 증가 v2
                article.increaseHit(); // 각 객체들이 알아서 조회수를 증가시킴. 관련 로직은 한 곳에 집중시켜서 관리를 편하게 함

                // alt + ctrl + L : 코드 정리. 자주 사용할 것
                System.out.println("===================");
                System.out.println("번호 : " + article.getId());
                System.out.println("제목 : " + article.getTitle());
                System.out.println("내용 : " + article.getBody());
                System.out.println("등록날짜 : " + article.getRegDate());
                System.out.println("조회수 : " + article.getHit());
                System.out.println("===================");


            } else if(cmd.equals("search")) {
                // 검색어를 입력
                System.out.println("검색 키워드를 입력해주세요 :");
                String keyword = scan.nextLine();

                for(int i = 0; i < articleList.size(); i++) {
                    Article article = articleList.get(i);

                    if(article.getTitle().contains(keyword)) { // 검색에 keyword가 포함된 경우
                        System.out.println("번호 : " + article.getId());
                        System.out.printf("제목 : %s\n", article.getTitle());
                        System.out.println("===================");
                    }
                }

            }
        }
    }

    // 입력 : 찾고자 하는 게시물 번호
    // 출력 : 게시물 번호에 해당하는 인덱스
    public int findIndexById(int id) {

        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);

            if (article.getId() == id) {
                return i; // 원하는 것은 찾은 즉시 종료.
            }
        }

        return -1;
    }

    public String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();

        // 날짜와 시간의 형식을 지정합니다. 여기서는 연-월-일 시:분:초 형식을 사용합니다.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

        // 지정한 형식으로 날짜와 시간을 출력합니다.
        String formattedDate = now.format(formatter);

        return formattedDate;
    }
}
