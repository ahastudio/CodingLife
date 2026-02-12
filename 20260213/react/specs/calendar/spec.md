# Feature Specification: 기능명 스펙

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

<!-- 기능의 핵심 내용을 설명합니다. -->

## User Scenarios & Testing (mandatory)

<!-- User Story 1, 2, ... 형식으로 작성합니다. -->

### User Story 1: 시나리오명

- As: ~는
- I: ~할 수 있다
- So: ~하기 위해

#### Acceptance Scenarios

Scenario 1: **시나리오명**

- Given: ~할 때
- When: ~하면
- Then: 결과

Scenario 2: **시나리오명**

- Given: ~할 때
- When: ~하면
- Then: 결과

## Functional Requirements (mandatory)

<!-- FR-1, FR-2, ... 형식으로 번호를 붙여 작성합니다. -->

- FR-1: MUST 요구사항 설명
- FR-2: SHOULD 요구사항 설명

## Constraints (mandatory)

<!-- CON-1, CON-2, ... 형식으로 번호를 붙여 작성합니다. -->

- CON-1: MUST 제약사항 설명
- CON-2: MUST 제약사항 설명

## Success Criteria (mandatory)

<!-- SC-1, SC-2, ... 형식으로 번호를 붙여 작성합니다. -->

- SC-1: 성공 기준 설명
- SC-2: 성공 기준 설명

## OpenAPI Specification

<!-- 반드시 OpenAPI 3.0 형식(YAML)으로만 정리합니다. -->

```yaml
openapi: 3.0.3
info:
  title: API 제목
  version: 1.0.0
  description: |
    API 설명

paths:
  /endpoint:
    post:
      summary: 요약
      description: |
        상세 설명
      operationId: operationId
      tags:
        - Tag
      security:
        - bearerAuth: []
      parameters:
        - name: id
          in: path
          required: true
          description: ID 설명
          schema:
            type: string
      responses:
        '200':
          description: 성공
        '400':
          description: 잘못된 요청
        '401':
          description: 인증 실패
        '403':
          description: 권한 없음
        '404':
          description: 리소스 없음

components:
  securitySchemes:
    bearerAuth:
      type: http
      scheme: bearer
      bearerFormat: JWT
```
