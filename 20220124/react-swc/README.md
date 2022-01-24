# React + SWC + PubSub 실험

기본적인 아이디어는 [Teaful](https://j.mp/3KD3kwx)을 참고함.

- <https://github.com/ahastudio/CodingLife/tree/main/20211008/react>
- <https://github.com/swc-project/jest>
- <https://swc.rs/docs/configuration/compilation#typescript>
- <https://swc.rs/docs/configuration/compilation#jsctransform>
- <https://swc.rs/docs/configuration/compilation#jsctransformreact>

## 프로젝트 생성

```bash
npm init -y
```

## Git과 ESLint를 위한 무시 파일 생성

```bash
touch .gitignore
touch .eslintignore
```

두 파일 모두 다음과 같이 작성한다.

```txt
/node_modules/
/dist/
.parcel-cache
```

## Visual Studio Code 세팅

기존 파일을 그대로 다운로드헤서 사용한다.

```bash
mkdir .vscode

curl https://raw.githubusercontent.com/ahastudio/CodingLife/main/20211008/react/.vscode/settings.json \
    -o .vscode/settings.json
```

## TypeScript 세팅

```bash
npm i -D typescript

npx tsc --init
```

JSX를 사용하기 위해 `tsconfig.json` 파일의 `jsx` 주석을 풀어준다.
Parcel과 SWC 모두 이 설정의 영향을 받지 않지만,
이렇게 하지 않으면 Visual Studio Code에서 경고한다.

## ESLint 세팅

```bash
npm i -D eslint

npx eslint --init
```

`.eslintrc.js` 파일은 적절히 수정한다.

## Jest와 SWC로 테스트 환경 구축

의존성 설치.

```bash
npm i -D jest @types/jest @swc/core @swc/jest \
    @testing-library/react @testing-library/jest-dom
```

Jest 환경 설정 파일 생성.

```bash
touch jest.config.js
```

`jest.config.js` 파일 작성.

```js
module.exports = {
  testEnvironment: 'jsdom',
  setupFilesAfterEnv: [
    '@testing-library/jest-dom/extend-expect',
  ],
  transform: {
    '^.+\\.(t|j)sx?$': ['@swc/jest', {
      jsc: {
        parser: {
          syntax: 'typescript',
          jsx: true,
        },
        transform: {
          react: {
            runtime: 'automatic',
          },
        },
      },
    }],
  },
  testPathIgnorePatterns: [
    '<rootDir>/node_modules/',
    '<rootDir>/dist/',
  ],
};
```

## React 설치

```bash
npm i react react-dom
npm i -D @types/react @types/react-dom
```

## 기본 React 앱 코드 준비

기존 코드를 그대로 다운로드해서 사용한다.

```bash
mkdir src

curl https://raw.githubusercontent.com/ahastudio/CodingLife/main/20211008/react/src/index.tsx \
    -o src/index.tsx

curl https://raw.githubusercontent.com/ahastudio/CodingLife/main/20211008/react/src/App.tsx \
    -o src/App.tsx

curl https://raw.githubusercontent.com/ahastudio/CodingLife/main/20211008/react/src/App.test.tsx \
    -o src/App.test.tsx
```

`index.html` 파일도 마찬가지로 다운로드해서 사용한다.

```bash
curl https://raw.githubusercontent.com/ahastudio/CodingLife/main/20211008/react/index.html \
    -o index.html
```

## 테스트 실행

```bash
npx jest
```

## Parcel 설치 & 웹 서버 실행

```bash
npm i -D parcel

npx parcel index.html --port 8000
```

## 구독과 발행을 위한 Hook 준비

```bash
mkdir -p src/hooks

touch src/hooks/usePubSub.ts
```

`usePubSub.ts` 파일 작성.

```ts
import { useState } from 'react';

const listeners: { [name: string]: Set<Function> } = {};

let state: { [name: string]: any } = {};

export default function usePubSub() {
  const [, update] = useState<any>();

  return {
    subscribe(key: string) {
      if (!listeners[key]) {
        listeners[key] = new Set();
      }
      listeners[key].add(update);
    },
    publish(key: string, value: any) {
      if (!listeners[key]) {
        return;
      }
      listeners[key].forEach((listener) => {
        state = { ...state, [key]: value };
        listener(state);
      });
    },
    value(key: string) {
      return state[key];
    },
  };
}
```

## 컴포넌트 2개 만들어서 구독과 발행 실험

```bash
mkdir -p src/components

touch src/components/Input.tsx
touch src/components/Output.tsx
```

`Input.tsx` 파일 작성.

```tsx
import usePubSub from '../hooks/usePubSub';

export default function Input() {
  const { publish, value } = usePubSub();

  const handleClick = () => {
    const count = value('count') || 0;
    publish('count', count + 1);
  };

  return (
    <button type="button" onClick={handleClick}>
      Click Me!
    </button>
  );
}
```

`Output.tsx` 파일 작성.

```tsx
import { useEffect } from 'react';

import usePubSub from '../hooks/usePubSub';

export default function Output() {
  const { subscribe, unsubscribe, value } = usePubSub();

  useEffect(() => {
    subscribe('count');
    return () => unsubscribe('count');
  }, []);

  const count = value('count');

  return (
    <div>
      <p>
        Count:
        {' '}
        {String(count)}
      </p>
      <p>
        Time:
        {' '}
        {new Date().getTime()}
      </p>
    </div>
  );
}
```
