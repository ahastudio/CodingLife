import { fetchExampleText, upper, print } from './http';

async function main() {
  // 1. Input
  const text = await fetchExampleText();

  // 2. Process

  const transformed = upper(text);

  // 3. Output
  print(transformed);
}

main();
