package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 아샬에게 Hello라고 인사하는 말을 출력한다.
        // System.out.println("Hello, 아샬!");

        // 아샬 부분을 따로 분리해서 같은 걸 한다.
        // System.out.println("Hello, " + "아샬" + "!");

        // <이름>에게 Hello라고 인사하는 말을 출력한다.
        // String name = "아샬";
        // System.out.println("Hello, " + name + "!");

        // I am a boy.
        // I am a ___.
        // I am a girl.
        // ___ (be) a boy.
        // You are a girl.

        // How do you do?

        // String name;  // --> 타입 선언
        // name = "아샬"; // --> 값 대입/할당 (assignment)
        // System.out.println("Hello, " + name + "!");

        // String text = new String(new char[] { 'c', 'a', 'r' });
        // System.out.println(text);

        // 0. 도구 준비
        Scanner scanner = new Scanner(System.in);

        // 1. 입력을 위한 출력
        // System.out.println("What's your name?");
        // 1. 진짜 입력
        // String name = scanner.nextLine();
        // 2. processing
        // String text = "Hello, " + name + "!";
        // 3. 출력
        // System.out.println(text);

        System.out.println("How old are you?");

        // camelCase
        // 다른 표현들은 PascalCase, snake_case, SCREAMING_SNAKE_CASE
        String ageAsString = scanner.nextLine();
        int age = Integer.parseInt(ageAsString); // 이건 그냥 외워야 합니다 ㅠㅠ

        // processing
        int seconds = age + 365 * 24 * 60 * 60;

        System.out.println("You spent " + seconds + " seconds.");
    }
}

// 1. 조건 분기
// 2. 반복 (조건)
// 3. 상태 관리

// Python
//   [1, 2, 3] + [4, 5, 6] => [1, 2, 3, 4, 5, 6]
// NumPy
//   <1, 2, 3> + <4, 5, 6> => <5, 7, 9>

// 1. 모양 -> 내용의 의미
// 2. 기본 개념 -> 설명을 놓치는 것 -> 지식의 저주 <= <<<Feedback>>>
// 3. 수학적 접근 <= <<<Feedback>>>
// 4. 영어 타자 속도 => 연습

// Type
//   -> 무엇을 할 수 있는가, 연산...
// 타입(추상적) -> 실체/객체(구체적)
// Class - Instance
//   사람 - 소크라테스 => 선생님
//       - 플라톤
//       - 아리스토텔레스
// String - "Hello"
//        - "World"
//        - "아샬" => name

// Input -> Processing -> Output
// 키, 몸무게 -> BMI
// 100개의 문항 답 -> MBTI
