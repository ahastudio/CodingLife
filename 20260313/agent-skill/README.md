# Agent Skill 실험

## paper-infographic — 논문 인포그래픽 생성

논문 요약 마크다운을 읽고 Gemini로 교육용 인포그래픽 PNG를 생성하는 스킬.

### 준비

`.env` 파일에 Gemini API 키를 설정한다.

```bash
cp .env.example .env

# GEMINI_API_KEY=... 입력
```

### 사용법

```text
/paper-infographic files/my-paper.md
```

생성된 이미지는 `output/` 디렉토리에 저장된다.

출력 경로나 모델을 바꾸고 싶으면 자연어로 요청하면 된다.

- "files/my-paper.md 인포그래픽으로 만들어줘"
- "출력 파일은 output/my-infographic.png로 해줘"
- "gemini-2.5-flash-image 모델로 바꿔서 해줘"
