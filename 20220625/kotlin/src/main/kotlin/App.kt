// 여러 자료 중 원하는 것 찾기!
// Binary Search Tree (BST)
// -> Node 표현 방법 = class
//    -> 갖춰야 할 것들: value, left, right
// Class를 이용해서 만든 무언가 => Instance
// Instance가 표현하려는 것 => 객체(object) = 무언가... = 데이터 + 연산(프로시저, 메서드, 함수)
// toString
// 없다 -> null => 대단히 위험하다. -> Kotlin에서는 null을 기본으론 못 쓰게 함.
// -> nullable이란 개념으로 신중히 다루게 함.
// 확인 -> 테스트 => 자동화
// 단정문 -> 같은가? 1 + 1 = 2 (assertEquals 기대값과 실행 결과를 비교)
// null을 null이 아니라고 확신하기 => nullable!!
// null이 아닐 때만 메서드 ㅇㅇ하기 => nullbale?.
// null일 때 뭔가 다른 걸로 대체하기 => ?: (evlis operator)
// Test Driven Development (테스트 주도 개발)
// -> Refactoring (결과는 바뀌지 않는데, 구현이 바뀜)
//    => 설계 개선
// 하나의 객체(e.g. Node)는 하나의 책임만 가지고 있어야 한다.
// < 역할(e.g. 담임 선생님), 책임(e.g. 가르친다, 출석 확인, 상담, ...) >
// 이 클래스를 고치는 이유는 하나여야 한다.
// 두 가지 책임: 1) 화면에 출력 2) add하고 search하기.
// 위임 -> 다른 객체에 [의존]!

fun main() {
    val numbers = intArrayOf(5, 3, 6, 4, 1, 8, 2, 7, 9, 10)

    val value = 10
    var found = 0

    for (number in numbers) {
        println(number)

        if (number == value) {
            found = number
            break
        }
    }

    when (found) {
        0 -> println("Not found!")
        else -> println("Found: $found")
    }
}
