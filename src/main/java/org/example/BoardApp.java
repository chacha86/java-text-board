package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class BoardApp {
    public void run() {
        Scanner scan = new Scanner(System.in);
        // 반복 횟수 정할 수 없음 => 무한 반복 구조

        // 변수에는 하나의 값만 저장 가능하므로 여러개의 게시물을 저장하려면 ArrayList를 사용한다.
        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<String> bodyList = new ArrayList<>();
        ArrayList<Integer> idList = new ArrayList<>();
        int latestArticleId = 1; // 시작 번호를 1로 지정

//        String title = ""; // 딱히 처음에 값이 필요 없음. 추후에 입력을 통해 넣을 것임.
//        String body = "";

        while (true) { // 반복 조건이 true이기 때문에 무한 반복

            System.out.print("명령어 : ");
            String cmd = scan.nextLine();

            if (cmd.equals("exit")) { // 숫자가 아닌 경우 같다라는 표현을 할 때 == 이 아닌 .equals()를 사용해야 한다.
                System.out.println("프로그램을 종료합니다.");
                break; // 반복문 탈출

            } else if (cmd.equals("add")) {

                System.out.print("게시물 제목을 입력해주세요 : ");
                String title = scan.nextLine();
                titleList.add(title); // titleList 배열에 title을 추가

                System.out.print("게시물 내용을 입력해주세요 : ");
                String body = scan.nextLine();
                bodyList.add(body);

                idList.add(latestArticleId); // 게시물이 생성될 때마다 번호를 생성해서 저장
                latestArticleId++; // 게시물이 생성될 때마다 번호를 증가

                System.out.println("게시물이 등록되었습니다.");

            } else if (cmd.equals("list")) {
                System.out.println("===================");
                for (int i = 0; i < titleList.size(); i++) {
                    String title = titleList.get(i);
                    int id = idList.get(i);

                    System.out.println("번호 : " + id);
                    System.out.printf("제목 : %s\n", title);
                    System.out.println("===================");
                }
            } else if (cmd.equals("update")) {
                System.out.print("수정할 게시물 번호를 입력해주세요 : ");
                int id = Integer.parseInt(scan.nextLine()); // 1 -> index : 0
                // 2 -> index : 1

                System.out.print("새로운 제목을 입력해주세요 : ");
                String newTitle = scan.nextLine();

                System.out.print("새로운 내용을 입력해주세요 : ");
                String newBody = scan.nextLine();

                // 인덱스로 찾아서 수정
                titleList.set(id - 1, newTitle);
                bodyList.set(id - 1, newBody);

                System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
            } else if(cmd.equals("delete")) {
                System.out.print("삭제할 게시물 번호를 입력해주세요 : ");
                int inputId = Integer.parseInt(scan.nextLine()); // 1 -> index : 0

                for(int i = 0; i < idList.size(); i++) {
                    int targetId = idList.get(i);

                    if(targetId == inputId) { // 삭제하고자 하는 id와 i번째 id가 같다면
                        idList.remove(i); // 삭제해라
                    }
                }


                System.out.printf("%d 게시물이 삭제되었습니다.\n", inputId);

            }
        }
    }
}
