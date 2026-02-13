# Progress Log

> **각 단계를 완료하거나 문제가 발생하면 업데이트하세요.**

## Session 2026-02-13

### Phase 1: Requirements & Discovery ✅

**작업 내역**:

1. file-based-planning-workflow 템플릿 6개 파일을 specs/calendar/에 복사
2. README.md 작성: 배경, 목표, 동작 방식 정의
3. spec.md 작성: User Story 3개, FR 7개, Constraints 2개, Success Criteria 4개
4. 프로젝트 루트 README.md 작성
5. 스펙 리뷰 및 승인 완료

**생성/수정 파일**:

- `20260213/react/README.md` (새로 생성)
- `specs/calendar/README.md` (수정)
- `specs/calendar/spec.md` (수정)
- `specs/calendar/findings.md` (수정)

### Phase 2: Planning & Structure ✅

**작업 내역**:

1. plan.md 작성: 컴포넌트 구조, Outside-In TDD 단계, 순수 함수 설계
2. tasks.md 작성: Phase별 체크리스트, 결정 사항 정리
3. progress.md 작성: 지금까지의 작업 기록
4. findings.md 업데이트: 기술 결정 및 배운 점 추가
5. 계획 리뷰 및 승인 완료

**생성/수정 파일**:

- `specs/calendar/plan.md` (수정)
- `specs/calendar/tasks.md` (수정)
- `specs/calendar/progress.md` (수정)
- `specs/calendar/findings.md` (수정)

### Phase 3: Implementation 🔄

**프로젝트 초기 설정 완료**:

1. Vite + React + TypeScript 프로젝트 스캐폴딩
2. Tailwind CSS v4 설치 및 설정 (@tailwindcss/vite 플러그인)
3. Vitest + React Testing Library + jsdom 설치 및 설정
4. 기본 보일러플레이트 정리 (App.css, 로고 등 제거)
5. 빌드 성공 확인

**에러 해결**: `vitest/config` 타입 참조 필요 (vitest v4에서 변경됨)

**생성/수정 파일**:

- `vite.config.ts` (수정: tailwind, vitest 설정)
- `package.json` (수정: test 스크립트 추가)
- `src/index.css` (수정: Tailwind import)
- `src/setupTests.ts` (새로 생성)
- `src/App.tsx` (수정: 빈 컴포넌트)

**Step 1: App 컴포넌트 TDD 완료**:

1. Red: App 렌더링 시 "2026년 2월" 텍스트 확인 테스트 → 실패
2. Green: today props 받아 연월 표시하는 최소 구현 → 통과

**생성/수정 파일**:

- `src/App.test.tsx` (새로 생성)
- `src/App.tsx` (수정: today props, 연월 표시)

**Step 2: Calendar 컴포넌트 TDD 완료**:

1. Red: Calendar 테스트 8개 작성 (연월, 요일, 오늘 강조, 이전/다음/오늘 버튼, 연도 경계) → 실패
2. Green: Calendar 컴포넌트 구현 (useState, 날짜 계산, 그리드 렌더링) → 8개 통과
3. Refactoring: year/month 개별 상태를 단일 currentDate로 통합
4. App에서 Calendar 연결 → 전체 9개 테스트 통과

**생성/수정 파일**:

- `src/components/Calendar.tsx` (새로 생성)
- `src/components/Calendar.test.tsx` (새로 생성)
- `src/App.tsx` (수정: Calendar 사용)

**Step 3: 순수 함수 TDD + 연결 + 스타일링 완료**:

1. Red: getCalendarDates, isSameDay, isSameMonth 테스트 9개 → 실패
2. Green: helpers/calendar.ts 순수 함수 구현 → 9개 통과
3. Calendar에서 순수 함수 사용하도록 리팩토링
4. Tailwind CSS 스타일 적용 (레이아웃, 오늘 강조, 다른 달 흐리게)
5. 빌드 시 test 파일 타입 에러 → tsconfig.app.json에서 exclude 처리
6. 전체 18개 테스트 통과, 빌드 성공

**생성/수정 파일**:

- `src/helpers/calendar.ts` (새로 생성)
- `src/helpers/calendar.test.ts` (새로 생성)
- `src/components/Calendar.tsx` (수정: 순수 함수 사용 + Tailwind)
- `tsconfig.app.json` (수정: test 파일 exclude)

**코드 품질 개선: 명확한 이름 짓기**:

1. 모든 소스 파일에서 약어를 전체 이름으로 변경
2. 루프 변수 `i` → `dateIndex`, `startIndex`
3. 매개변수 `a`, `b` → `dateA`, `dateB`
4. 테스트 변수 `jan` → `january`
5. 내부 변수 `startDay`/`endDay` → `firstDayOfWeek`/`lastDayOfWeek`
6. 핸들러 `handlePrev`/`handleNext`/`handleToday` → `handlePrevMonth`/`handleNextMonth`/`handleGoToToday`
7. 전체 18개 테스트 통과 확인

**수정 파일**:

- `src/components/Calendar.tsx` (이름 개선)
- `src/components/Calendar.test.tsx` (이름 개선)
- `src/helpers/calendar.ts` (이름 개선)
- `src/helpers/calendar.test.ts` (이름 개선)
- `specs/calendar/findings.md` (배운 점 추가)

**Step 2a/2b: CalendarHeader + CalendarGrid 분리**:

1. CalendarHeader 컴포넌트 추출: 연월 표시 + 이전/다음/오늘 버튼
2. CalendarGrid 컴포넌트 추출: 요일 헤더 + 날짜 셀 그리드
3. Calendar에서 두 하위 컴포넌트 조합
4. 기존 18개 테스트 전부 통과 확인 (리팩토링이므로 동작 변경 없음)
5. 빌드 성공 확인

**새로 생성한 파일**:

- `src/components/CalendarHeader.tsx`
- `src/components/CalendarGrid.tsx`

**수정 파일**:

- `src/components/Calendar.tsx` (하위 컴포넌트 사용)

### Phase 4: Testing ✅

**자동 테스트**:

- `npm run build`: 빌드 성공 (tsc + vite build)
- `npm test`: 27개 테스트 전부 통과 (App 1 + Calendar 17 + helpers 9)
- 수동 테스트 3개 항목 → 자동 테스트 9개로 대체 완료

## Test Results

✅ **27 tests passed** (3 files) — vitest v4.0.18

| 검증 목적 | 무엇을 보장하는가 | 테스트 수 |
| --------- | ------------------ | :-------: |
| 달력 날짜 계산이 정확한가 | 시작/끝 요일, 이전 달 패딩, 항상 7의 배수 | 5 |
| 날짜 비교가 올바른가 | 같은 날/같은 월 판별 | 4 |
| 달력이 올바르게 그려지는가 | 연/월 제목, 요일 헤더, 날짜 셀 렌더링 | 3 |
| 오늘 날짜가 눈에 띄는가 | 하나만 강조, 파란 배경+흰 글씨+굵게, 다른 달 날짜는 흐리게 | 4 |
| 월 이동이 정상 동작하는가 | 이전/다음 달, 연도 경계(1↔12월), 연속 이동, 이동 후 날짜 갱신 | 6 |
| 오늘 버튼으로 돌아올 수 있는가 | 즉시 복귀, 여러 달/다른 연도에서 복귀, 강조 복원 | 5 |

## Error Log

| Timestamp | Error | Attempt | Resolution |
| --------- | ----- | ------- | ---------- |
| 2026-02-13 | TS2769: test not in UserConfigExport | 1 | `/// <reference types="vitest/config" />` 사용 |
| 2026-02-13 | TS2593: Cannot find name 'describe' (빌드 시) | 1 | tsconfig.app.json에서 test 파일 exclude |

## 5-Question Reboot Check

작업 재개 시 이 질문들로 컨텍스트 복구:

| Question               | Answer                                         |
| ---------------------- | ---------------------------------------------- |
| 1. 현재 어느 단계인가? | Phase 4 완료. 빌드/테스트 27개 통과             |
| 2. 다음에 할 일은?     | 전 Phase 완료. 수동 테스트도 자동 테스트로 대체 |
| 3. 목표는?             | 월별 달력 표시 및 탐색 기능 구현               |
| 4. 지금까지 배운 것?   | findings.md 참고                               |
| 5. 완료한 작업은?      | 전 Phase 완료. 컴포넌트 분리, 코드 품질 개선   |
