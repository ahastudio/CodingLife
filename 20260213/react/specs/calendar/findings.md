# Findings & Decisions

> **기술적 발견, 중요한 결정이 있을 때마다 이 파일을 즉시 업데이트하세요.**

## Requirements

- [ ] 오늘 날짜 기준 월별 달력 표시
- [ ] 오늘 날짜 강조 표시
- [ ] 달력 위에 연월 표시
- [ ] 이전 달 이동 버튼 (왼쪽)
- [ ] 다음 달 이동 버튼 (오른쪽)
- [ ] 오늘로 돌아오기 버튼

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

## Issues Encountered

아직 발생한 이슈 없음.

## Learnings

### 스펙 기반 워크플로우 도입 (2026-02-13)

file-based-planning-workflow 템플릿을 활용하여 구현 전에 스펙을 먼저
정의하는 방식을 적용했다. README.md로 전체 개요를 잡고, spec.md로
상세 요구사항을 정의한 뒤, plan.md로 구현 계획을 세우는 단계적 접근을
따른다. 구현은 스펙 리뷰가 완료된 후에만 진행한다.
