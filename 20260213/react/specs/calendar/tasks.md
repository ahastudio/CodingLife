# Tasks: 월별 달력 표시 및 탐색

## Goal

오늘 날짜 기준 월별 달력을 표시하고, 이전/다음 달 이동 및 오늘 복귀 기능을
Outside-In TDD로 구현한다.

## Current Phase

✅ Phase 4: Testing

## Phases

### Phase 1: Requirements & Discovery ✅

- [x] 요구사항 정의 (README.md)
- [x] 스펙 문서 작성 (spec.md)
- [x] 스펙 리뷰 및 승인

### Phase 2: Planning & Structure ✅

- [x] 구현 계획 작성 (plan.md)
- [x] 컴포넌트 구조 설계 (App → Calendar → Header + Grid)
- [x] 순수 함수 분리 설계 (helpers/calendar.ts)
- [x] 계획 리뷰 및 승인

### Phase 3: Implementation ✅

- [x] 프로젝트 초기 설정 (Vite + React + TypeScript + Tailwind + Vitest)
- [x] Step 1: App 컴포넌트 (테스트 → 구현)
- [x] Step 2: Calendar 컴포넌트 (테스트 → 구현)
- [x] Step 2a: CalendarHeader 분리
- [x] Step 2b: CalendarGrid 분리
- [x] Step 3: 날짜 계산 순수 함수 (테스트 → 구현)
- [x] 컴포넌트에서 순수 함수 연결
- [x] Tailwind 스타일링

### Phase 4: Testing ✅

- [x] 전체 빌드 확인 (npm run build)
- [x] 전체 테스트 통과 확인 (npm test) — 27개 전부 통과
- [x] ~~수동 테스트~~ → 자동 테스트로 대체: 오늘 날짜 강조 확인
- [x] ~~수동 테스트~~ → 자동 테스트로 대체: 이전/다음 달 이동 (연도 경계 포함)
- [x] ~~수동 테스트~~ → 자동 테스트로 대체: 오늘 버튼 복귀

## Key Questions

해결 완료. findings.md 참고.

## Decisions Made

| Decision | Rationale |
| -------- | --------- |
| App → Calendar → Header + Grid 구조 | 관심사 분리. 각 컴포넌트가 단일 책임 |
| helpers/calendar.ts 순수 함수 분리 | 컴포넌트와 로직 분리. 테스트 용이 |
| today props 주입 | 테스트에서 날짜 고정 가능 |

## Errors Encountered

| Error | Attempt | Resolution |
| ----- | ------- | ---------- |
| TS2769: test not in UserConfigExport | 1 | `/// <reference types="vitest/config" />` 사용 |
| TS2593: Cannot find name 'describe' (빌드 시) | 1 | tsconfig.app.json에서 test 파일 exclude |
| @testing-library/user-event 미설치 | 1 | npm install --save-dev @testing-library/user-event |

## Notes

- 진행할 때마다 Phase 상태를 업데이트하세요: ⏸️ 대기 → 🔄 진행 중 → ✅ 완료
- 중요한 결정을 내리기 전에 이 계획을 다시 읽어보세요. (attention manipulation)
- 모든 오류를 기록하세요. 삽질을 반복하는 걸 막을 수 있습니다.
- Outside-In TDD: 반드시 Red → Green → Refactoring 순서를 지킨다.
- tasks.md, progress.md, findings.md를 수시로 업데이트한다.
