// 1. 사용자의 입력 검증 -> Validation
// 2. 입력을 if 문으로 검사
//    -> PostController / create 메서드에서 확인.
//    -> 틀리면 Bad Request(400)로 HTTP Status 응답.
//    => 어떻게 다른 HTTP Status를 전달할까?
//       -> Exception을 이용한 처리 가능.
// 3. Exception (예외)
//    - 프로그램이 정상적이지 않은 상황에 직면.
//    -> 어떻게 대응할까?
//       1) 프로그램을 정지. (-끗-)
//       2) 프로그램을 복구. (e.g. 사용자에게 문제가 발생했음을 알리고, 다시 시도 요청)
//          -> 예외가 발생한 지점과 복구하는 지점이 다를 수 있다.
// 4. 예외를 직접 일으키기
//    - 예외는 두 종류
//      1) checked -> 메서드에 throws란 걸 붙여야 함. => Exception 타입. (Java의 바람)
//      2) unchecked -> 그냥 쓰면 됨. => RuntimeException 타입. (사람들의 선호)
//                   -> Spring에서도 채택.
//    -> 예외를 직접 만들어야 함. => extends RuntimeException
//    => 예외를 어떻게 일으킬까? <- 예외 객체를 어떻게 던질까?
//       -> return과 유사하게 throw 사용.
// 5. 예외를 어떻게 받지?
//    - try - catch란 걸 쓸 수 있음.
//    -> Spring Web에서는 @ExceptionHandler 어노테이션으로 처리 가능.
// 6. 검사 기준을 어디에 둘까?
//    -> Controller (vs) DTO
//    => DTO에 둘 경우
//       1) DTO에 메서드로 둔다.
//       2) DTO에 적절한 Validation용 어노테이션을 붙여준다. (아래 기법 참고)
// 7. Spring의 Validation을 써보자!
//    -> @Validated 어노테이션을 붙여주면 끝!
//    -> 검사 기준은? => 필드에 @NotBlank 등의 어노테이션을 붙여주면 됨.

package com.ahastudio.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class, args);
    }

}
