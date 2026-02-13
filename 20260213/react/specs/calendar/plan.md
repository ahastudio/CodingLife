# Implementation Plan: 월별 달력 표시 및 탐색

## Summary

React 컴포넌트로 월별 달력 UI를 구현하고, 달력 날짜 계산 로직을 순수 함수로
분리하여 Outside-In TDD로 개발한다.

## Requirements

1. FR-1: 오늘 날짜 기준 월별 달력을 그리드 형태로 표시
2. FR-2: 달력 위에 연도와 월 표시
3. FR-3: 오늘 날짜 강조 표시
4. FR-4: 일요일~토요일 요일 헤더 표시
5. FR-5: 연월 왼쪽에 이전 달 버튼
6. FR-6: 연월 오른쪽에 다음 달 버튼
7. FR-7: "오늘" 버튼으로 오늘이 포함된 월로 복귀

## Critical Files

### New Files

- `src/App.tsx` — 루트 컴포넌트
- `src/App.test.tsx` — App 컴포넌트 테스트
- `src/components/Calendar.tsx` — 달력 컴포넌트 (헤더, 그리드 조합)
- `src/components/Calendar.test.tsx` — Calendar 컴포넌트 테스트
- `src/components/CalendarHeader.tsx` — 연월 표시, 이전/다음/오늘 버튼
- `src/components/CalendarGrid.tsx` — 요일 헤더 + 날짜 그리드
- `src/helpers/calendar.ts` — 달력 날짜 계산 순수 함수
- `src/helpers/calendar.test.ts` — 날짜 계산 함수 테스트

### Reference Files

- `specs/calendar/spec.md` — 요구사항 정의

## Architecture

### User Flow

```text
사용자 → App
           ↓
        Calendar
        ├── CalendarHeader (연월 표시, ◀ ▶ 오늘 버튼)
        └── CalendarGrid (요일 헤더 + 날짜 셀)
```

### State Flow

```text
1. App: currentDate 상태 관리 (useState)
       ↓
2. Calendar: currentDate, today를 props로 받음
       ↓
3. CalendarHeader: 연월 표시 + onPrev/onNext/onToday 콜백
   CalendarGrid: calendarDates 계산 → 날짜 셀 렌더링
```

### Data Model

```text
calendarDates (helpers/calendar.ts)
├── getCalendarDates(year, month) → Date[]
│   └── 해당 월의 첫 주 일요일 ~ 마지막 주 토요일
├── isSameDay(a, b) → boolean
└── isSameMonth(date, year, month) → boolean
```

## Implementation Steps

### Step 1: App 컴포넌트 — 달력이 렌더링되는지 확인

**Red:** `src/App.test.tsx`

```tsx
// 앱을 렌더링하면 오늘 연월이 표시되는지 확인
```

**Green:** `src/App.tsx`

```tsx
// Calendar 컴포넌트를 렌더링하는 최소 구현
```

**Refactoring:** 필요시 리팩토링

### Step 2: Calendar 컴포넌트 — 헤더와 그리드 통합

**Red:** `src/components/Calendar.test.tsx`

```tsx
// 연월 표시, 요일 헤더, 오늘 날짜 강조, 이전/다음/오늘 버튼 동작 테스트
```

**Green:** `src/components/Calendar.tsx`, `CalendarHeader.tsx`, `CalendarGrid.tsx`

```tsx
// 테스트를 통과하는 최소 구현
```

**Refactoring:** CalendarHeader, CalendarGrid로 분리

### Step 3: 달력 날짜 계산 — 순수 함수

**Red:** `src/helpers/calendar.test.ts`

```ts
// getCalendarDates: 특정 월의 날짜 배열 반환 검증
// isSameDay: 같은 날짜 비교 검증
// isSameMonth: 같은 월 비교 검증
```

**Green:** `src/helpers/calendar.ts`

```ts
// 테스트를 통과하는 최소 구현
```

**Refactoring:** 필요시 리팩토링

## Verification

### Build

```bash
npm run build
```

### Test

```bash
npm test
```

### Manual Test

1. 앱 진입 시 오늘 날짜가 강조된 이번 달 달력 표시 확인
2. 이전/다음 달 버튼으로 월 이동 확인 (연도 경계 포함)
3. "오늘" 버튼으로 오늘이 포함된 달 복귀 확인

## Considerations

### 테스트 환경

Vitest + React Testing Library를 사용하여 컴포넌트 테스트를 작성한다.
날짜 계산 순수 함수는 Vitest 단독으로 테스트한다.

### 오늘 날짜 주입

테스트에서 오늘 날짜를 고정하기 위해 today를 props로 주입 가능하게 설계한다.
기본값은 `new Date()`로 한다.
