// 1. 영속화 (Persistence)
//    -> 게임의 Save/Load와 유사하게, 객체들을 휘발성 메모리가 아닌 곳에 보관.
//    -> Disk(File), Database 등을 활용할 수 있다.
// 2. H2 Database -> In-memory => 휘발성 메모리.
//                -> File에도 기록 가능.
//    -> H2 Database Console로 확인 가능. http://localhost:3000/h2-console
// 3. JPA (Java Persistence API)
//    -> Spring Data JPA란 더 쓰기 쉽게 도와주는 게 있다.
//    => Entity (Data Model에 속함. 하지만 Domain Model과 관련이 깊음)
//       + Repository (DAO와 유사)
// 4. Repository는 구현하지 않아도 됨.
//    단, 인터페이스(interface)는 준비해야 함.
//    -> 이 인터페이스는 JpaRepository(ID의 타입과 Entity의 타입 포함)를 상속.
//    => 이렇게 하면 테스트가 불가능한데? => Mock Object로 처리!
// 5. Mock Object -> Mockito
//    (Mockito는 Spring Boot 프로젝트 만들 때 들어온 spring-boot-starter-test에 포함됨)
//    타입 instance = mock(타입.class)
//    -> 가짜로 진짜를 흉내내서(mocking) 뭔가 처리되는 것처럼 할 수 있다.
//       given(목_객체.메서드()).willReturn(리턴_값);
//    -> 특정 메서드가 호출됐는지 확인할 수 있다. (Spy)
//       verify(목_객체).메서드();
// 6. Entity로 어떻게 지정? => @Entity 어노테이션을 붙여주면 됨.
//    -> ID 필드에 @Id 어노테이션 붙여주기.
//    -> ID 필드가 비어 있지 않도록 @GeneratedValue 어노테이션 붙여서 *자동으로 ID 발급*하기!
//    => Java Bean의 특징 중 하나를 공유: 기본 생성자
// 7. H2 DB의 저장소를 메모리가 아니라 파일로 만들자!
//    -> src/main/resources/application.properties 설정 파일에서 변경 가능.
//    => 파일은 두 프로세스에서 동시에 접근할 수 없다.
//    1) 서로 다른 파일을 쓰거나
//    2) 파일이 안 중요하면 한 쪽은 메모리를 쓰거나
//    -> 서로 다르다는 건 어떻게 알지?
// 8. Spring은 Profile이란 걸 지원.
//    -> 어떤 Profile은 H2 Database를 메모리로 쓰고, => 설정 파일에서...
//    -> 그 어떤 Profile을 테스트에서 사용하면?! => @ActiveProfiles
// 9. CRUD -> R(list)와 C만 처리함. => homework
// 10. DB의 Transaction
//     -> Atomic하게 작동하도록 범위를 설정. (동시에 접근하는 경우나 장애 등에 대응)
//     => Application Layer가 이 범위에 해당.
//     -> @Transactional 어노테이션을 붙이면 됨.
//     => Repository는 Collection처럼 작동. add는 있지만 update는 없다.
//        save는 하지만, 객체를 수정한 경우엔 아무 것도 안 해도 된다.

package com.ahastudio.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class, args);
    }

}
