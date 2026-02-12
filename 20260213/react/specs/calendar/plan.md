# Implementation Plan: 기능명

<!-- 기능의 한국어 이름을 제목에 포함합니다. -->

## Summary

<!-- 주요 구현 방식을 1-2문장으로 요약합니다. -->

## Requirements

<!-- spec.md의 요구사항을 간단히 정리합니다. -->

1. 요구사항 1
2. 요구사항 2

## Critical Files

<!-- 작업 범위를 파악하기 위한 파일 목록입니다. -->

### New Files

- `path/to/NewFile.kt`

### Modified Files

- `path/to/ExistingFile.kt`

### Reference Files

- `path/to/ReferenceFile.kt`

## Architecture

<!-- Outside-In 순서로 다이어그램을 표현합니다. -->

### User Flow

```text
사용자 → [요청] → Controller
                      ↓
              Application Service
                      ↓
                  처리 완료
```

### Event Flow

```text
1. Controller: POST /endpoint
       ↓
2. ApplicationService.method()
       ↓
3. Domain 로직
       ↓
4. Event 발생
```

### Domain Model

```text
Entity (기존)
├── newMethod()  ← 새로운 메서드
└── events
    └── NewEvent  ← 새로운 이벤트
```

## Implementation Steps

<!-- Outside-In TDD 방식으로 구현 단계를 작성합니다. -->
<!-- 각 단계마다 Red-Green-Refactoring 사이클을 반복합니다. -->

### Step 1: UI Layer - Controller

**Red:** `src/test/kotlin/...ControllerTest.kt`

```kotlin
// 실패하는 테스트
```

**Green:** `src/main/kotlin/...Controller.kt`

```kotlin
// 테스트를 통과하는 최소 구현
```

**Refactoring:** 필요시 리팩토링

### Step 2: Application Layer - Application Service

**Red:** `src/test/kotlin/...ServiceTest.kt`

```kotlin
// 실패하는 테스트
```

**Green:** `src/main/kotlin/...Service.kt`

```kotlin
// 테스트를 통과하는 최소 구현
```

**Refactoring:** 필요시 리팩토링

### Step 3: Domain Layer - Entity/Event

**Red:** `src/test/kotlin/...Test.kt`

```kotlin
// 실패하는 테스트
```

**Green:** `src/main/kotlin/...`

```kotlin
// 테스트를 통과하는 최소 구현
```

**Refactoring:** 필요시 리팩토링

## Verification

<!-- 컴파일, 테스트, 수동 테스트 방법을 명시합니다. -->

### Build

```bash
./gradlew compileKotlin
```

### Test

```bash
./gradlew test
```

### Manual Test

1. 정상 케이스 테스트
2. 에러 케이스 테스트

## Considerations

<!-- 구현 시 고려해야 할 사항들을 명시합니다. -->

### 기존 코드 재사용

설명

### 호환성

설명
