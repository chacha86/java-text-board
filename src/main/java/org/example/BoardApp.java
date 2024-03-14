package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardApp {
    ArrayList<Article> articleList = new ArrayList<>(); // 인스턴스 변수
    Scanner scan = new Scanner(System.in);
    int latestArticleId = 4; // 테스트 데이터 3개 있으므로 시작 번호를 4로 지정

    public void run() {
        makeTestData();
        while (true) { // 반복 조건이 true이기 때문에 무한 반복

            System.out.print("명령어 : ");
            String cmd = scan.nextLine();
            if(cmd.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            switch (cmd) {
                case "add" -> add();
                case "list" -> list();
                case "update" -> update();
                case "delete" -> delete();
                case "detail" -> detail();
                case "search" -> search();
                default -> System.out.println("올바른 명령어가 아닙니다.");
            }
        }
    }

    private void makeTestData() {
        Article a1 = new Article(1, "안녕하세요 반갑습니다. 자바 공부중이에요.", "냉무", 0, getCurrentDateTime());
        Article a2 = new Article(2, "자바 질문좀 할게요~", "냉무", 0, getCurrentDateTime());
        Article a3 = new Article(3, "정처기 따야되나요?", "냉무", 0, getCurrentDateTime());
        articleList.add(a1);
        articleList.add(a2);
        articleList.add(a3);
    }

    private void search() {
        // 검색어를 입력
        System.out.println("검색 키워드를 입력해주세요 :");
        String keyword = scan.nextLine();

        ArrayList<Article> searchedList = new ArrayList<>();

        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);
            if (article.getTitle().contains(keyword)) {
                searchedList.add(article);
            }
        }

        printArticleList(searchedList);
    }

    private void detail() {
        System.out.print("상세보기 할 게시물 번호를 입력해주세요 : ");
        int inputId = Integer.parseInt(scan.nextLine());

        Article article = findArticleById(inputId);

        if (article == null) {
            System.out.println("없는 게시물입니다.");
            return;
        }

        article.increaseHit();

        System.out.println("===================");
        System.out.println("번호 : " + article.getId());
        System.out.println("제목 : " + article.getTitle());
        System.out.println("내용 : " + article.getBody());
        System.out.println("등록날짜 : " + article.getRegDate());
        System.out.println("조회수 : " + article.getHit());
        System.out.println("===================");
    }

    private void delete() {
        System.out.print("삭제할 게시물 번호를 입력해주세요 : ");
        int inputId = Integer.parseInt(scan.nextLine());

        Article article = findArticleById(inputId);

        if (article == null) {
            System.out.println("없는 게시물입니다.");
            return;
        }

        articleList.remove(article); // arrayList remove는 값을 직접 찾아서 지워주기도 함
        System.out.printf("%d 게시물이 삭제되었습니다.\n", inputId);
    }

    private void update() {
        System.out.print("수정할 게시물 번호를 입력해주세요 : ");
        int inputId = Integer.parseInt(scan.nextLine());

        Article article = findArticleById(inputId);

        if (article == null) {
            System.out.println("없는 게시물입니다.");
            return;
        }

        System.out.print("새로운 제목을 입력해주세요 : ");
        String newTitle = scan.nextLine();

        System.out.print("새로운 내용을 입력해주세요 : ");
        String newBody = scan.nextLine();

        // 변하지 않는 것에서 변하는 것을 분리
        // 변하는 것에서 변하지 않는 것을 분리
        article.setTitle(newTitle); // target은 참조값이므로 직접 객체를 접근하여 수정 가능
        article.setBody(newBody);

        System.out.printf("%d번 게시물이 수정되었습니다.\n", inputId);
    }

    private void list() {
        printArticleList(this.articleList); // 전체 출력 -> 전체 저장소 넘기기
    }

    private void add() {

        System.out.print("게시물 제목을 입력해주세요 : ");
        String title = scan.nextLine();

        System.out.print("게시물 내용을 입력해주세요 : ");
        String body = scan.nextLine();

        // 모든 매개변수를 받는 생성자 이용
        Article article = new Article(latestArticleId, title, body, 0, getCurrentDateTime());

        articleList.add(article);
        System.out.println("게시물이 등록되었습니다.");

        latestArticleId++; // 게시물이 생성될 때마다 번호를 증가
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
    public Article findArticleById(int id) {

        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);

            if (article.getId() == id) {
                return article;
            }
        }

        return null; // null -> 없다. 객체 타입에서만 사용 가능
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
