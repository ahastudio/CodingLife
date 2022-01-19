// 1. Spring Security를 사용하는 Spring Web 프로젝트 생성.
//    -> Spring Security의 기본 기능을 사용하지 않게 함.
//    -> REST API라서 기본 기능(로그인 폼 화면 등)은 불필요함.
// 2. 로그인
//    -> [E-mail Address]와 [Password]를 입력하면 사용자 인증(authentication) 처리.
//    -> Access Token 발급
//    => REST API로 어떻게 표현할까?
//       Resource(명사의 복수형) + HTTP Method(CRUD 구분)
//       1) Session(단수형) -> Create (V)
//       2) Token(단수형) -> Create
//       3) Auth(단수형) -> Create
//       4) ...
//    -> 로그인을 위한 입력값을 처리하는 DTO 필요함.
// 3. AuthenticationService (Application Layer)
//    -> authenticate -> E-mail과 Password를 받아서 잘 되면 토큰 발행.
//                    -> 잘못 되면 예외 발생 (LoginFailed)
// 4. JWT (JSON Web Tokens)
//    -> 정보를 담고 있는 토큰. (claims)
//    -> 내가 발급한 게 맞는지 확인 필요함. => 암호학의 서명. (verify)
//    -> 정보를 encode => Token / Token을 decode => 객체
// 5. Secret 관리
//    => 문자열. 코드에 넣지 않고, 따로 관리. => 설정 파일
//    -> @Value 어노테이션 사용
// 6. 사용자 정보 관리
//    -> 사용자를 관리하는 도메인 모델을 마련. => User, UserRepository
//    -> 패스워드를 안전하게 보관. (평문 저장 금지 => 해시된/암호화된 데이터를 사용)
//       -> BCrypt를 많이 썼는데, Argon2라는 비교적 최신 기법을 사용.
//    => 암호화된 패스워드를 어떻게 검사하지?
//       1) 입력한 암호를 해시로 만들어서 저장된 해시와 비교
//       2) 뭔가 알고리즘에 맞는 확인 방법이 있으면 좋겠다. -> Argon2
// 7. 회원 가입
//    -> User를 마련 -> 그래야 로그인할 수 있다.
//    - POST /users => E-mail, Password
//    => 숙제: 중복 체크. 맞지 않는 형식(이메일, 패스워드가 너무 짧거나).
// 8. 인가(Authorization)
//    -> 이미 만들어진 Access Token으로 실제 서비스에 대한 접근 권한 제어.
//    - 인증(Authentication): 나(사용자)는 누구인가? => 로그인 -> Token (계속 반복되는 일)
//    - 인가(Authorization): 나는 무얼 할 수 있는가? -> 인증 후 작업.
//    -> HTTP Headers => Authorization: Bearer 123.456.789
//    -> Token으로 사용자 찾기. => email 대신 userId 사용.
// 9. Controller에서 사용자 정보 얻어서 쓰기
//    -> 사용자 정보는 *이미* Headers를 분석해서 얻음.
//       (@RequestAttribute 어노테이션 사용)
//    -> Controller의 Action이 호출되기 전에 뭔가가 실행돼야 함.
//       => HandlerInterceptor
// 10. Java의 Optional
//     User라고 하면 User의 인스턴스 또는 null을 다룰 수 있음.
//     Optional<User>라고 하면 User 객체가 있거나 없거나를 표현할 수 있음(null 대신).
//     -> map과 orElse를 적절히 사용하면 값이 있는 경우와 없는 경우를 *쉽게* 다룰 수 있음.

package com.ahastudio.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
