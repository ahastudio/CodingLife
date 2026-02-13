# Findings & Decisions

> **기술적 발견, 중요한 결정이 있을 때마다 이 파일을 즉시 업데이트하세요.**

## Requirements

- [x] FR-1: 오늘 날짜 기준 월별 달력을 그리드 형태로 표시
- [x] FR-2: 달력 위에 연도와 월 표시
- [x] FR-3: 오늘 날짜 강조 표시
- [x] FR-4: 일요일~토요일 요일 헤더 표시
- [x] FR-5: 연월 왼쪽에 이전 달 버튼
- [x] FR-6: 연월 오른쪽에 다음 달 버튼
- [x] FR-7: "오늘" 버튼으로 오늘이 포함된 월로 복귀

## Research Findings

### 기술 스택

- Vite: 빌드 도구 및 개발 서버
- React: UI 라이브러리
- TypeScript: 타입 안전성
- Tailwind CSS: 유틸리티 기반 스타일링

### 프로젝트 구조

- 프로젝트 루트: `20260213/react`
- 스펙 문서: `specs/calendar/` 하위에 관리
- Outside-In TDD 방식으로 구현 예정

## Resources

### 문서

- [file-based-planning-workflow](https://github.com/ahastudio/file-based-planning-workflow): 스펙 템플릿 원본

### 코드 참조

- `src/App.tsx`: 루트 컴포넌트
- `src/components/Calendar.tsx`: 달력 컴포넌트 (상태 관리 + 하위 컴포넌트 조합)
- `src/components/CalendarHeader.tsx`: 연월 표시 + 이전/다음/오늘 버튼
- `src/components/CalendarGrid.tsx`: 요일 헤더 + 날짜 셀 그리드
- `src/helpers/calendar.ts`: 날짜 계산 순수 함수

## Technical Decisions

| Decision | Rationale |
| -------- | --------- |
| Vite + React + TypeScript + Tailwind | 사용자 요구사항. 빠른 개발 환경과 타입 안전성, 유틸리티 CSS 활용 |
| Outside-In TDD | 사용자 요구사항. UI에서 시작하여 내부 로직으로 진행 |
| specs/calendar 폴더 사용 | 달력 기능의 스펙 문서를 독립적으로 관리 |
| 날짜 계산을 순수 함수로 분리 | 컴포넌트와 로직 분리. 순수 함수는 테스트가 쉬움 |
| today를 props로 주입 | 테스트에서 오늘 날짜를 고정하기 위해. 기본값 new Date() |
| Vitest + React Testing Library | Vite 기반 프로젝트에 자연스럽게 통합되는 테스트 환경 |

## Issues Encountered

### 1. Vitest v4 타입 참조 변경

**문제**: `/// <reference types="vitest" />`로 설정 시 TS2769 에러 발생

**원인**: Vitest v4에서 타입 export 경로가 변경됨

**해결**: `/// <reference types="vitest/config" />`로 변경

**결과**: 빌드 성공

### 2. 빌드 시 테스트 파일 타입 에러

**문제**: `tsc -b` 실행 시 test 파일에서 describe/it/expect를 찾을 수 없음

**원인**: tsconfig.app.json이 src 전체를 포함하여 test 파일도 컴파일 대상

**해결**: tsconfig.app.json에서 `"exclude": ["src/**/*.test.ts", "src/**/*.test.tsx"]` 추가

**결과**: 빌드 성공

## Learnings

### 스펙 기반 워크플로우 도입 (2026-02-13)

file-based-planning-workflow 템플릿을 활용하여 구현 전에 스펙을 먼저
정의하는 방식을 적용했다. README.md로 전체 개요를 잡고, spec.md로
상세 요구사항을 정의한 뒤, plan.md로 구현 계획을 세우는 단계적 접근을
따른다. 구현은 스펙 리뷰가 완료된 후에만 진행한다.

### spec.md 작성 (2026-02-13)

프론트엔드 전용 앱이므로 OpenAPI Specification 섹션은 해당 없어 제외했다.
User Story를 3개로 나누어 정리했다: 오늘 날짜 확인, 이전/다음 달 이동,
오늘로 돌아오기. 각 스토리별로 Acceptance Scenario를 Given/When/Then
형식으로 작성하여 테스트 케이스와 직접 연결되도록 했다.

### plan.md 작성 (2026-02-13)

컴포넌트 구조를 App → Calendar → CalendarHeader + CalendarGrid로 설계했다.
Outside-In TDD에 맞춰 Step 1(App), Step 2(Calendar 컴포넌트),
Step 3(순수 함수) 순서로 바깥에서 안쪽으로 구현 단계를 구성했다.
테스트 가능성을 위해 today를 props로 주입하는 설계를 결정했다.

### 프로젝트 초기 설정 (2026-02-13)

Tailwind CSS v4는 @tailwindcss/vite 플러그인 방식으로 설정한다.
index.css에 `@import "tailwindcss";`만 넣으면 된다.
Vitest v4에서는 `/// <reference types="vitest/config" />`를 사용해야
vite.config.ts에서 test 속성을 인식한다.

### 코드 품질: 명확한 이름 짓기 (2026-02-13)

약어를 사용하지 않고 항상 명확한 전체 이름을 사용한다.
코드는 작성하는 시간보다 읽는 시간이 훨씬 길기 때문에,
읽는 사람이 의미를 즉시 파악할 수 있어야 한다.

**나쁜 예 → 좋은 예**:

- `i` → `dateIndex`, `startIndex` (루프 변수도 의미를 담는다)
- `a`, `b` → `dateA`, `dateB` (비교 대상이 무엇인지 드러낸다)
- `jan` → `january` (약어 대신 전체 단어)
- `weekIdx` → `weekIndex` (축약하지 않는다)
- `startDay` → `firstDayOfWeek` (무엇의 시작인지 명확히)
- `handlePrev` → `handlePrevMonth` (무엇을 이동하는지 명확히)

**원칙**: 변수명, 함수명, 매개변수명 모두 의미가 자명해야 한다.
이름만 보고 역할을 알 수 없다면 이름이 잘못된 것이다.

### 컴포넌트 분리: 단일 책임 원칙 (2026-02-13)

Calendar 컴포넌트를 CalendarHeader와 CalendarGrid로 분리했다.
각 컴포넌트가 하나의 관심사만 담당하도록 설계한다.

- **CalendarHeader**: 연월 표시와 탐색 버튼 (표시 + 상호작용)
- **CalendarGrid**: 요일 헤더와 날짜 셀 (데이터 렌더링)
- **Calendar**: 상태 관리와 하위 컴포넌트 조합 (조정자 역할)

리팩토링 시 기존 테스트가 변경 없이 통과해야 한다.
테스트가 구현 세부사항이 아닌 동작을 검증하고 있다면,
내부 구조를 자유롭게 개선할 수 있다.

### 수동 테스트를 자동 테스트로 대체 (2026-02-13)

브라우저 접근이 불가능한 환경(claude.ai/code 등)에서는 수동 테스트를
실행할 수 없다. 이 경우 수동 테스트 항목을 자동 테스트로 전환하면
동일한 검증을 CI/CD 환경에서도 재현 가능하게 만들 수 있다.

**전환 전략**:

- "눈으로 확인"하는 항목을 DOM 쿼리 + assertion으로 변환
- 시각적 강조 → CSS 클래스 존재 여부 검증 (`toHaveClass`)
- 유일성 확인 → 전체 셀 필터링 후 개수 검증 (`toHaveLength(1)`)
- 다른 달 날짜 흐림 → 패딩 셀의 클래스 검증

**얻은 것**: 수동 테스트 3개 → 자동 테스트 9개로 대체하면서
오히려 검증 범위가 넓어졌다 (연속 이동, 다른 연도 복귀 등).
자동 테스트는 반복 실행이 가능하고 회귀를 즉시 감지한다.
