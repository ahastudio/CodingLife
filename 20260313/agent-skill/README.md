# Agent Skill 실험

## paper-infographic — 논문 인포그래픽 생성

논문 요약 마크다운을 읽고 Gemini로 교육용 인포그래픽 PNG를 생성하는 CLI 도구.

### 준비

`.env` 파일에 Gemini API 키를 설정한다.

```bash
cp .env.example .env

# GEMINI_API_KEY=... 입력
```

### 사용법

```bash
node scripts/paper-infographic.js <input.md> [옵션]
```

| 옵션                  | 설명           | 기본값                       |
| --------------------- | -------------- | ---------------------------- |
| `--output, -o <path>` | 출력 PNG 경로  | `output/<입력파일명>.png`    |
| `--model, -m <id>`    | Gemini 모델 ID | `gemini-3-pro-image-preview` |
| `--help, -h`          | 도움말 출력    |                              |

### 예시

```bash
# 기본 실행 (output/ 폴더에 자동 저장)
node scripts/paper-infographic.js files/my-paper.md

# 출력 경로 지정
node scripts/paper-infographic.js files/my-paper.md --output output/my-infographic.png

# 모델 변경
node scripts/paper-infographic.js files/my-paper.md --model gemini-2.5-flash-image
```
