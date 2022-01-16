// 1. 간단한 health check 경로 확보 -> GET / => Hello, world!
//    -> WelcomeController => home (action)
// 2. 테스트 코드 작성 (가능하면 먼저 하자!)
// 3. 인터넷 통신 -> 서로를 구분하는 주소 체계 (IP address 1.2.3.4)
//                Process를 구분하는 주소 -> port: 8080
//                내 컴퓨터에 떠있는 서버 -> <1.2.3.4:8080>
//    => API에서는 3000 포트 사용.
// 4. Gradle의 파일/폴더 구조
//    - src/main/java -> 자바로 작성된 프로그램 코드
//    - src/test/java -> 자바로 작성된 테스트 코드
//    - src/main/resource -> 프로그램에서 쓸 자원(설정, 이미지, 기타 등등)
//    -> src/main/resources/application.propertices => 스프링 앱 설정 파일
//       => server.port를 이용해 포트 번호 변경 가능.
// 5. 게시물 목록 -> GET /posts
//    -> PostController -> list (action)
// 6. CORS -> @CrossOrigin 어노테이션을 컨트롤러에 추가.
// 7. REST API로 응답할 제대로 된 데이터?
//    -> List로 관리할 수 있으면 좋겠다.
//    -> 어쨌든 객체 형태면 좋겠다. -> 데이터라서... 제대로 된 객체는 아닌데...
//       Java Bean -> 기본 생성자가 있고, getter가 전통적인 모양(getXXX)으로 있는 객체.
//    -> Data Transfer Object (DTO) => 사실은 제대로 된 객체는 아님. 그냥 Bean임.
//                                  => 무기력한 객체.
//    -> PostDto라는 클래스를 사용.
//       필드는 null을 포함할 수 있도록 primitive type을 바로 쓰지 않음.
// 8. 게시물 작성 -> POST /posts
//    -> PostController 그대로 사용 -> create (Action)
//    -> HTTP Status가 다르다는 점 주의!
// 9. 공통으로 의존하는 무언가...
//    -> 하위 계층(layer) -> Application Layer (service)
//                      -> Domain Layer (repository)
//    => PostService
// 10. 뭔가 관리하는 게 엉상한데?
//     도메인 객체를 따로 관리하자!
//     -> Post 도메인 객체
//        PostRepository (Collection 같은 객체)
// 11. 도메인 객체와 DTO 사이의 변환이 필요함.
//     1) Object Mapper (Bean Mapper, Model Mapper) 등 사용
//     2) 1번을 만들어서 쓴다 😉
//     3) 살이 있는 객체(도메인 객체)가 이 책임을 진다.
// 12. Layered Architecture
//     - UI Layer -> Controller (JSON <- DTO)
//     - Application Layer -> Service (fat하게 시작 -> thin하게 마무리)
//     - Domain Layer -> Post 도메인 객체, Post Repository 객체(Collection처럼 사용)
// 13. ID 발급! -> Repository가 담당.
//     -> 정말로 유일하게 만드는 건 어려운 일. (이건 별도로 다뤄야 할 정도)

package com.ahastudio.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class, args);
    }

}
