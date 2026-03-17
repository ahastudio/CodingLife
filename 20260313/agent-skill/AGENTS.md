# 지침

## 논문 시각화

논문을 시각화하라는 요청이 오면 `scripts/paper-infographic.js` 스크립트를 실행해서 이미지를 생성한다.

```bash
node scripts/paper-infographic.js <input.md> [옵션]
```

| 옵션                  | 설명           | 기본값                       |
| --------------------- | -------------- | ---------------------------- |
| `--output, -o <path>` | 출력 PNG 경로  | `output/<입력파일명>.png`    |
| `--model, -m <id>`    | Gemini 모델 ID | `gemini-3-pro-image-preview` |
| `--help, -h`          | 도움말 출력    |                              |

생성된 이미지는 `output/` 디렉토리에 저장된다.

### 예시

```bash
# 기본 실행
node scripts/paper-infographic.js files/my-paper.md

# 출력 경로 지정
node scripts/paper-infographic.js files/my-paper.md --output output/my-infographic.png

# 모델 변경
node scripts/paper-infographic.js files/my-paper.md --model gemini-2.5-flash-image
```
