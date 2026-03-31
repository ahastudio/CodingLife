#!/usr/bin/env node
/**
 * paper-infographic.js — 논문 요약 마크다운을 인포그래픽 PNG로 변환하는 범용 CLI 도구
 *
 * 사용법:
 *   node paper-infographic.js <input.md> [options]
 *
 * 옵션:
 *   --output <path>   출력 PNG 경로 (기본값: output/<입력파일명>.png)
 *   --model  <id>     Gemini 모델 ID (기본값: env GEMINI_MODEL 또는 gemini-3-pro-image-preview)
 *   --help            도움말 출력
 *
 * 환경 변수:
 *   GEMINI_API_KEY    Gemini API 키 (필수)
 *   GEMINI_MODEL      기본 모델 ID (선택)
 */

import fs from "node:fs";
import path from "node:path";
import process from "node:process";

const cwd = process.cwd();
const DEFAULT_MODEL = "gemini-3-pro-image-preview";

// --- .env 로더 ---

function loadDotEnv(filePath) {
  if (!fs.existsSync(filePath)) return;

  for (const line of fs.readFileSync(filePath, "utf8").split(/\r?\n/)) {
    const trimmed = line.trim();
    if (!trimmed || trimmed.startsWith("#")) continue;

    const sep = trimmed.indexOf("=");
    if (sep === -1) continue;

    const key = trimmed.slice(0, sep).trim();
    const value = trimmed.slice(sep + 1).trim().replace(/^['"]|['"]$/g, "");
    if (!(key in process.env)) process.env[key] = value;
  }
}

// --- CLI 인수 파서 ---

function parseArgs(argv) {
  const args = argv.slice(2);

  if (args.includes("--help") || args.includes("-h")) {
    printHelp();
    process.exit(0);
  }

  const opts = { input: null, output: null, model: null };

  for (let i = 0; i < args.length; i++) {
    if (args[i] === "--output" || args[i] === "-o") {
      opts.output = args[++i];
    } else if (args[i] === "--model" || args[i] === "-m") {
      opts.model = args[++i];
    } else if (!args[i].startsWith("-")) {
      opts.input = args[i];
    }
  }

  return opts;
}

function printHelp() {
  console.log(`
사용법:
  node paper-infographic.js <input.md> [옵션]

옵션:
  --output, -o <path>   출력 PNG 경로
                        (기본값: output/<입력파일명>.png)
  --model,  -m <id>     Gemini 모델 ID
                        (기본값: env GEMINI_MODEL 또는 ${DEFAULT_MODEL})
  --help,   -h          이 도움말 출력

환경 변수:
  GEMINI_API_KEY        Gemini API 키 (필수)
  GEMINI_MODEL          기본 모델 ID (선택)

예시:
  node paper-infographic.js files/my-paper.md
  node paper-infographic.js files/my-paper.md --output output/my-infographic.png
  node paper-infographic.js files/my-paper.md --model gemini-2.5-flash-image
`.trim());
}

function resolveOutputPath(inputPath, outputOpt) {
  if (outputOpt) return path.resolve(outputOpt);

  const base = path.basename(inputPath, path.extname(inputPath));
  return path.join(cwd, "output", `${base}.png`);
}

// --- 프롬프트 빌더 ---

function buildPrompt(article) {
  const cleanArticle = article
    .replace(/\r/g, "")
    .replace(/\n{3,}/g, "\n\n")
    .trim();

  return `
You are a professional infographic designer.
Read the source article below and create a single polished educational infographic as a vertical poster PNG.

Design rules:
- Audience: middle school students in South Korea
- Language: Korean throughout
- Tone: friendly, clear, science-club style — not childish
- Visual style: editorial infographic, bold typography, structured sections, clean icons,
  dark space background, vivid accent colors (orange-cyan contrast), premium magazine layout,
  highly legible Korean text
- Vertical composition, clean blocks with enough spacing
- One final image only — not a sketch

Content rules (derive everything from the source article):
- Write a concise main title that captures the paper's core question (max 20 chars)
- Write a subtitle that names the key variables studied (max 35 chars)
- Divide the content into 4 labeled sections:
    1. 핵심 개념 — 1-2 sentence explanation of the central idea
    2. 연구 방법 — how the researchers approached the problem
    3. 주요 결과 — key findings, use a comparison panel or ranking if appropriate
    4. 왜 중요한가? — significance and future outlook
- Extract 4-6 important keywords from the article and display them as small labels
- Keep every Korean sentence short and readable for a middle school student
- Avoid dense paragraphs; prefer 1-2 lines per text block

Source article:
${cleanArticle}
`.trim();
}

// --- Gemini API 호출 ---

async function generateImage(prompt, apiKey, model) {
  const response = await fetch(
    `https://generativelanguage.googleapis.com/v1beta/models/${model}:generateContent`,
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "x-goog-api-key": apiKey,
      },
      body: JSON.stringify({
        contents: [{ parts: [{ text: prompt }] }],
        generationConfig: { responseModalities: ["TEXT", "IMAGE"] },
      }),
    },
  );

  if (!response.ok) {
    const errorText = await response.text();
    throw new Error(`Gemini API 요청 실패 (${response.status}): ${errorText}`);
  }

  const data = await response.json();
  for (const candidate of data.candidates || []) {
    for (const part of candidate?.content?.parts || []) {
      if (part.inlineData?.data) {
        return Buffer.from(part.inlineData.data, "base64");
      }
    }
  }

  throw new Error("이미지 데이터가 응답에 없습니다.");
}

// --- 진입점 ---

async function main() {
  loadDotEnv(path.join(cwd, ".env"));

  const opts = parseArgs(process.argv);

  if (!opts.input) {
    console.error("오류: 입력 파일을 지정해야 합니다.\n");
    printHelp();
    process.exitCode = 1;
    return;
  }

  const inputPath = path.resolve(opts.input);
  if (!fs.existsSync(inputPath)) {
    throw new Error(`입력 파일을 찾을 수 없습니다: ${inputPath}`);
  }

  const apiKey = process.env.GEMINI_API_KEY;
  if (!apiKey) {
    throw new Error(".env 파일에 GEMINI_API_KEY를 설정해야 합니다.");
  }

  const model = opts.model || process.env.GEMINI_MODEL || DEFAULT_MODEL;
  const outputPath = resolveOutputPath(inputPath, opts.output);

  console.log(`입력: ${inputPath}`);
  console.log(`출력: ${outputPath}`);
  console.log(`모델: ${model}`);
  console.log("이미지 생성 중...");

  const article = fs.readFileSync(inputPath, "utf8");
  const prompt = buildPrompt(article);
  const image = await generateImage(prompt, apiKey, model);

  fs.mkdirSync(path.dirname(outputPath), { recursive: true });
  fs.writeFileSync(outputPath, image);

  console.log("완료!");
}

main().catch((error) => {
  console.error(error.message);
  process.exitCode = 1;
});
