# Feature Specification: 월별 달력 표시 및 탐색

<!--
제품 요구사항을 정의하는 문서입니다.
PRD(Product Requirements Document) 수준으로 작성하며, 기술적인 세부사항은
포함하지 않습니다.

포함하지 않는 내용 (plan.md, findings.md에서 다룸):
- 도메인 모델 (Aggregate, Entity, Value Object, Exception 등)
- 동작 방식의 코드 예시
- 기술적 구현 세부사항
-->

## Overview

오늘 날짜를 기준으로 월별 달력을 표시하고, 이전/다음 달로 이동하며,
언제든 오늘로 돌아올 수 있는 달력 화면을 제공한다.

## User Scenarios & Testing (mandatory)

### User Story 1: 오늘 날짜 확인

- As: 사용자는
- I: 앱을 열면 오늘 날짜가 강조된 이번 달 달력을 볼 수 있다
- So: 오늘이 며칠인지 빠르게 파악하기 위해

#### Acceptance Scenarios

Scenario 1: **앱 첫 진입 시 이번 달 달력 표시**

- Given: 사용자가 앱에 처음 진입했을 때
- When: 화면이 로드되면
- Then: 오늘 날짜가 포함된 월의 달력이 표시되고, 오늘 날짜가 강조된다

Scenario 2: **연월 표시 확인**

- Given: 달력이 표시되었을 때
- When: 달력 상단을 보면
- Then: 현재 보고 있는 연도와 월이 표시된다

### User Story 2: 이전/다음 달 이동

- As: 사용자는
- I: 이전 달 또는 다음 달로 이동할 수 있다
- So: 다른 월의 날짜를 확인하기 위해

#### Acceptance Scenarios

Scenario 1: **이전 달로 이동**

- Given: 2026년 2월 달력이 표시되어 있을 때
- When: 왼쪽 이전 달 버튼을 누르면
- Then: 2026년 1월 달력이 표시된다

Scenario 2: **다음 달로 이동**

- Given: 2026년 2월 달력이 표시되어 있을 때
- When: 오른쪽 다음 달 버튼을 누르면
- Then: 2026년 3월 달력이 표시된다

Scenario 3: **연도 경계 이동**

- Given: 2026년 1월 달력이 표시되어 있을 때
- When: 이전 달 버튼을 누르면
- Then: 2025년 12월 달력이 표시된다

### User Story 3: 오늘로 돌아오기

- As: 사용자는
- I: 다른 달을 보고 있더라도 오늘 버튼을 눌러 오늘이 있는 달로 돌아올 수 있다
- So: 현재 날짜를 다시 확인하기 위해

#### Acceptance Scenarios

Scenario 1: **다른 달에서 오늘로 복귀**

- Given: 2026년 5월 달력을 보고 있을 때
- When: "오늘" 버튼을 누르면
- Then: 오늘 날짜가 포함된 월의 달력이 표시되고, 오늘 날짜가 강조된다

Scenario 2: **이번 달에서 오늘 버튼 누름**

- Given: 이미 오늘이 포함된 달을 보고 있을 때
- When: "오늘" 버튼을 누르면
- Then: 달력이 그대로 유지되고, 오늘 날짜가 강조된 상태를 유지한다

## Functional Requirements (mandatory)

- FR-1: MUST 오늘 날짜 기준으로 해당 월의 달력을 그리드 형태로 표시한다
- FR-2: MUST 달력 위에 현재 보고 있는 연도와 월을 표시한다
- FR-3: MUST 오늘 날짜를 시각적으로 눈에 띄게 강조 표시한다
- FR-4: MUST 일요일부터 토요일까지 요일 헤더를 표시한다
- FR-5: MUST 연월 왼쪽에 이전 달 버튼을 배치하고, 누르면 이전 달로 이동한다
- FR-6: MUST 연월 오른쪽에 다음 달 버튼을 배치하고, 누르면 다음 달로 이동한다
- FR-7: MUST "오늘" 버튼을 항상 표시하고, 누르면 오늘 날짜가 포함된 월로 돌아온다

## Constraints (mandatory)

- CON-1: MUST Vite, React, TypeScript, Tailwind CSS를 사용한다
- CON-2: MUST Outside-In TDD 방식으로 구현한다

## Success Criteria (mandatory)

- SC-1: 앱 진입 시 오늘 날짜가 강조된 이번 달 달력이 표시된다
- SC-2: 이전/다음 달 버튼으로 월 이동이 정상 동작한다 (연도 경계 포함)
- SC-3: "오늘" 버튼을 누르면 오늘이 포함된 달로 돌아온다
- SC-4: 모든 기능이 TDD 테스트를 통과한다
