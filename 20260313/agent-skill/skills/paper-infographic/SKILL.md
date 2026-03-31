---
name: paper-infographic
description: 논문 마크다운 파일을 인포그래픽 이미지로 변환한다. 논문 시각화 요청 시 사용.
metadata:
  argument-hint: "[input.md] [--output output/name.png] [--model model-id]"
---

`scripts/paper-infographic.js` 스크립트를 실행해서 논문 마크다운을 인포그래픽 이미지로 변환한다.

## 사용법

```bash
node skills/paper-infographic/scripts/paper-infographic.js $ARGUMENTS
```

## 규칙

- 입력 파일이 지정되지 않으면 `files/` 디렉토리에서 최근 `.md` 파일을 찾아 사용한다.
- 출력 경로를 별도로 지정하지 않으면 기본값(`output/<입력파일명>.png`)을 사용한다.
- 실행 후 생성된 이미지 경로를 선생님께 알려드린다.
