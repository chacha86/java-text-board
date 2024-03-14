package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class ArticleRepository {

    CommonUtil commonUtil = new CommonUtil();
    ArrayList<Article> articleList = new ArrayList<>(); // 인스턴스 변수
    ArticleView articleView = new ArticleView();
    Scanner scan = commonUtil.getScanner();
    int WRONG_VALUE = -1; // 값의 의미를 부여
    int latestArticleId = 4; // 테스트 데이터 3개 있으므로 시작 번호를 4로 지정

    public void makeTestData() {
        Article a1 = new Article(1, "안녕하세요 반갑습니다. 자바 공부중이에요.", "냉무", 0, commonUtil.getCurrentDateTime());
        Article a2 = new Article(2, "자바 질문좀 할게요~", "냉무", 0, commonUtil.getCurrentDateTime());
        Article a3 = new Article(3, "정처기 따야되나요?", "냉무", 0, commonUtil.getCurrentDateTime());
        articleList.add(a1);
        articleList.add(a2);
        articleList.add(a3);
    }

    public void search() {
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

        articleView.printArticleList(searchedList);
    }

    public void detail() {
        System.out.print("상세보기 할 게시물 번호를 입력해주세요 : ");

        int inputId = getParamAsInt(scan.nextLine(), WRONG_VALUE);
        if(inputId == WRONG_VALUE) {
            return;
        }

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
    public void delete() {

        System.out.print("삭제할 게시물 번호를 입력해주세요 : ");

        int inputId = getParamAsInt(scan.nextLine(), WRONG_VALUE);
        if(inputId == WRONG_VALUE) {
            return;
        }

        Article article = findArticleById(inputId);

        if (article == null) {
            System.out.println("없는 게시물입니다.");
            return;
        }

        articleList.remove(article); // arrayList remove는 값을 직접 찾아서 지워주기도 함
        System.out.printf("%d 게시물이 삭제되었습니다.\n", inputId);
    }

    public void update() {
        System.out.print("수정할 게시물 번호를 입력해주세요 : ");

        int inputId = getParamAsInt(scan.nextLine(), WRONG_VALUE);
        if(inputId == WRONG_VALUE) {
            return;
        }

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

    public void list() {
        articleView.printArticleList(this.articleList); // 전체 출력 -> 전체 저장소 넘기기
    }

    public void add() {

        System.out.print("게시물 제목을 입력해주세요 : ");
        String title = scan.nextLine();

        System.out.print("게시물 내용을 입력해주세요 : ");
        String body = scan.nextLine();

        // 모든 매개변수를 받는 생성자 이용
        Article article = new Article(latestArticleId, title, body, 0, commonUtil.getCurrentDateTime());

        articleList.add(article);
        System.out.println("게시물이 등록되었습니다.");

        latestArticleId++; // 게시물이 생성될 때마다 번호를 증가
    }

    public Article findArticleById(int id) {

        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);

            if (article.getId() == id) {
                return article;
            }
        }

        return null; // null -> 없다. 객체 타입에서만 사용 가능
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
