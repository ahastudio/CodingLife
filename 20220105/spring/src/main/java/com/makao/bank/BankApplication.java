// 1. Spring Initializr로 Spring Boot 프로젝트 생성.
//    - Spring Web과 Spring Dev Tools 의존성 추가.
//    - Spring Test는 기본으로 추가됨.
// 2. main -> run... => Spring Boot가 비슷한 모양으로 알아서 뭔가 해준다.
// 3. 웹 서버 URL: http://localhost:8080/
// 4. 가장 기본적인 주소 -> home page = main page (/)
//    -> Spring Web / Spring MVC / Spring Web MVC
//    -> MVC 아키텍처 (J2EE 디자인 패턴 -> MVC 패턴)
//       웹은 아니고, “GUI 프로그램”을 만들 때 쓰던 설계 방식.
//       -> 관심사의 분리. Model / View / Controller
//                     (데이터)  (UI)   (Handler)
//                    도메인 모델        event 처리
//    -> Web에서는? => HTML 보여주는 쪽을 View로 간주.
//                => HTTP 요청 -> 응답: Controller
//                => 둘 다 아니면? Model (모호하다...)
//    -> Controller가 핵심이 됨. -> 특정 path(/)에 대한 처리 방식 결정 => 응답.
//    -> HomeController -> GET /
//       => Annotation (@...) => @Override, @Test 등에서 사용. 런타임에 적용되는 주석?
//       -> @Controller -> View에 대한 처리를 기본으로 가져감.
//       -> @RestController -> 이건 우리가 응답을 직접 처리할 수 있음.
//    -> Controller - Action -> action이 path+method와 매핑됨.
//                    -> @GetMapping("/") => GET / 요청에 응답하는 액션 메서드.
// 5. View에 대한 처리 => HTML generator?! => 직접 만들어서 쓸까?
//    -> views / PageGenerator (추상) -> html() -> content()는 구체에서 구현.
//               HomePageGenerator (구체) -> 추상을 상속/extends -> content() 구현.
// 6. 잔액 조회 페이지
//    -> GET /account => AccountController / AccountPageGenerator
//       => Controller에 대한 테스트 코드 만들기
//    -> Account 도메인 모델
//    -> Account 여러 개 관리 -> AccountRepository (Map 활용)
//    -> AccountService를 만들 수 있을까? => 송금하면서 다시 검토.
// 7. 송금하기
//    -> Spring을 왜 쓰는가? -> 더 고민!
//    - Get /transfer => TransferController - transferPage
//    -> TransferPageGenerator
//    => 메뉴에 링크 추가
//    - POST /transfer => TransferController - transfer
//      -> input으로 받은 2개의 값 얻기 (to, amount)
//    -> Account 2개 준비 -> account에 transfer 기능 추가!
//      -> account가 변해야 함.
//    -> TransferService 이용 (관심사 분리)
// 8. 공통된 의존성 주입(DI)
//    -> 대표적인 게 Repository -> 하나의 Repository에 둘 또는 여럿의 Controller가 의존.
//    -> Spring은 Application Context를 제공.
//       우리가 쓸 객체들을 Component(Bean)로 관리.
//    -> 관리 중인 객체를 얻어서 쓰는 법이 둘:
//       1) 생성자에 파라미터로 넣음.
//       2) 필드에 @Autowired 어노테이션 추가.
//    -> 관리할 객체 설정하는 방법이 둘:
//       1) 내 클래스를 관리해 달라고 부탁!
//          => @SpringBootApplication / @ComponentScan 어노테이션을 보고
//             @Component가 붙은 클래스를 이용해 객체를 만들어서 관리!
//       2) 직접 객체 전달해서 관리해 달라고 부탁!
//          => @Bean 어노테이션을 붙인 팩토리 메서드를 통해 객체 생성해서 전달.
//    -> DI를 위해서 Spring을 사용(가장 큰 이유 중 하나).
//       (제어 역전) IoC Container -> 이 중 특별한 형태: Application Context
//       => Bean의 생명 주기를 관리.
//    => 단 하나의 객체에 여러 객체가 의존.
//       ~~~~~ -> Singleton (GoF의 디자인 패턴에 있는 것과 다르게 구현됨)
// 9. 송금을 여러 번 하면 테스트가 실패하는 문제!
//    -> @BeforeEach 어노테이션으로 각 테스트 실행 전에 초기화 등 작업 가능.
//    -> AccountRepository에 reset 추가.
// 10. Layered Architecture (계층형 아키텍처)
//     -> 사용자에 가까운 부분: UI Layer (controller) => 어떻게 작동하는지 모름.
//                         Application Layer (service) => 기능 그 자체!
//                         Domain Layer (model, repository 인터페이스)
//                         Infrastructure Layer (repository 구현)
// 11. 입출금 내역
//     -> 마찬가지로 만들어 봅시다. (homework)
//        Application Layer (service) 잘 만들어서 쓸 수 있을까? (관심사의 분리)
// 12. 복잡한 프로그램을 만들려면 -> 단순한 접근을 하되 -> 복잡함을 다룰 수 있는 구조(원칙).

package com.makao.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }
}
