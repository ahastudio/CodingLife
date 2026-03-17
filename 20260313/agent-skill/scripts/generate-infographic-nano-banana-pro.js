#!/usr/bin/env node

import fs from "node:fs";
import path from "node:path";
import process from "node:process";
import { fileURLToPath } from "node:url";

const scriptDir = path.dirname(fileURLToPath(import.meta.url));
const rootDir = path.resolve(scriptDir, "..");
const envPath = path.join(rootDir, ".env");
const sourcePath = path.join(rootDir, "files", "event_horizon_2603.11741.easy.md");
const outputPath = path.join(rootDir, "output", "event-horizon-infographic-nano-banana-pro.png");
const model = process.env.GEMINI_MODEL || "gemini-3-pro-image-preview";

function loadDotEnv(filePath) {
  if (!fs.existsSync(filePath)) {
    return;
  }

  const lines = fs.readFileSync(filePath, "utf8").split(/\r?\n/);
  for (const line of lines) {
    const trimmed = line.trim();
    if (!trimmed || trimmed.startsWith("#")) {
      continue;
    }

    const separatorIndex = trimmed.indexOf("=");
    if (separatorIndex === -1) {
      continue;
    }

    const key = trimmed.slice(0, separatorIndex).trim();
    const rawValue = trimmed.slice(separatorIndex + 1).trim();
    const value = rawValue.replace(/^['"]|['"]$/g, "");

    if (!(key in process.env)) {
      process.env[key] = value;
    }
  }
}

function assertRequiredFile(filePath) {
  if (!fs.existsSync(filePath)) {
    throw new Error(`필수 파일이 없습니다: ${filePath}`);
  }
}

function buildPrompt(article) {
  const cleanArticle = article
    .replace(/\r/g, "")
    .replace(/\n{3,}/g, "\n\n")
    .trim();

  return `
Create a single educational infographic in Korean as a polished vertical poster PNG.
Audience: middle school students in South Korea.
Tone: friendly, clear, science-club style, not childish.
Visual style: editorial infographic with bold typography, structured sections, clean icons,
subtle space texture background, glowing accretion disk, dark black-hole core, orange-cyan
contrast palette, premium magazine layout, highly legible Korean text.

Canvas:
- Vertical composition
- Clean infographic blocks with enough spacing
- One final image only
- Output must be a polished finished infographic, not a sketch

Content requirements:
- Main title: "블랙홀 그림자는 왜 작아질까?"
- Subtitle: "플라즈마와 양자 효과로 읽는 최신 블랙홀 연구"
- Add 4 sections with short Korean text:
  1. "블랙홀 그림자란?"
  2. "이 논문의 핵심"
  3. "결과 한눈에 보기"
  4. "왜 중요한가?"
- Include a simple 4-step ranking or comparison panel showing:
  일반 블랙홀 > 플라즈마 있는 일반 블랙홀 > 양자 보정 블랙홀 > 플라즈마 + 양자 보정 블랙홀
- Include small labels for keywords:
  사건 지평선, 플라즈마, 양자 효과, EHT, Sgr A*
- Keep every Korean sentence short and readable for a middle school student.
- Avoid dense paragraphs.
- Prefer 1-2 lines per text block.
- Make the infographic factually grounded in the following source summary.

Source summary:
${cleanArticle}
`.trim();
}

async function generateImage(prompt, apiKey) {
  const response = await fetch(
    `https://generativelanguage.googleapis.com/v1beta/models/${model}:generateContent`,
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "x-goog-api-key": apiKey,
      },
      body: JSON.stringify({
        contents: [
          {
            parts: [{ text: prompt }],
          },
        ],
        generationConfig: {
          responseModalities: ["TEXT", "IMAGE"],
        },
      }),
    },
  );

  if (!response.ok) {
    const errorText = await response.text();
    throw new Error(`Gemini API 요청 실패 (${response.status}): ${errorText}`);
  }

  const data = await response.json();
  const candidates = data.candidates || [];
  for (const candidate of candidates) {
    const parts = candidate?.content?.parts || [];
    for (const part of parts) {
      if (part.inlineData?.data) {
        return Buffer.from(part.inlineData.data, "base64");
      }
    }
  }

  throw new Error("이미지 데이터가 응답에 없습니다.");
}

async function main() {
  loadDotEnv(envPath);
  assertRequiredFile(sourcePath);

  const apiKey = process.env.GEMINI_API_KEY;
  if (!apiKey) {
    throw new Error(".env 파일에 GEMINI_API_KEY를 설정해야 합니다.");
  }

  const article = fs.readFileSync(sourcePath, "utf8");
  const prompt = buildPrompt(article);

  console.log(`모델: ${model}`);
  console.log("이미지 생성 중...");

  const image = await generateImage(prompt, apiKey);

  fs.mkdirSync(path.dirname(outputPath), { recursive: true });
  fs.writeFileSync(outputPath, image);

  console.log(`PNG 생성 완료: ${outputPath}`);
}

main().catch((error) => {
  console.error(error.message);
  process.exitCode = 1;
});
