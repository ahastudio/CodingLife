// 이건 한 줄 주석.

/*
 * 여러 줄.
 * 이렇게 하면 뭔가 서식이 맞는 것 같음.
 */

package com.ahastudio

// fun -> function (함수)
fun main() {
    // 리터럴(literal) -> 코드에서 값을 표현.
    // 변수 -> 값에 이름을 붙인 것. => 식별자(identifier)
    // val -> value => 키워드(keyword, 예약어)
    // 식(expression) -> 평가해서 값이 될 수 있는 것.
    //               - 대개는 연산자를 사용
    //               => e.g. 1 + 2
    // 문(statement) -> 상태를 다루는 단위. 다른 언어에서는 세미콜론 등으로 분리.
    // 타입 -> 값의 종류. 어떤 연산을 할 수 있는가? 어떻게 되는가?
    //     - Int (정수형)
    //     - Double (부동소수점)
    //     - String (문자열)
    //     -> 전부 객체 -> method

    // 1. Input
    println("What's your name?")
    val name = readLine()

    // 2. Process
    val message = "Hello, $name"

    // 3. Output
    println(message)
}
