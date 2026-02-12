# Findings & Decisions

> **기술적 발견, 중요한 결정이 있을 때마다 이 파일을 즉시 업데이트하세요.**

## Requirements

- [ ] FR-1: 오늘 날짜 기준 월별 달력을 그리드 형태로 표시
- [ ] FR-2: 달력 위에 연도와 월 표시
- [ ] FR-3: 오늘 날짜 강조 표시
- [ ] FR-4: 일요일~토요일 요일 헤더 표시
- [ ] FR-5: 연월 왼쪽에 이전 달 버튼
- [ ] FR-6: 연월 오른쪽에 다음 달 버튼
- [ ] FR-7: "오늘" 버튼으로 오늘이 포함된 월로 복귀

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

- 아직 구현 전이므로 해당 없음

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

아직 발생한 이슈 없음.

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
