# Kafka Demo

- <https://github.com/astral-sh/uv>
- <https://github.com/dpkp/kafka-python>
- <https://github.com/wbarnha/kafka-python-ng>
- <https://github.com/fastavro/fastavro>
- <https://fastavro.readthedocs.io/>

## `.env` 파일 준비

```bash
cp .env.default .env
```

## uv 환경 설정

```bash
uv venv .venv
```

## 정적 분석

```bash
uv run bash -c "isort . && black . && ruff check ."
```

## 이벤트 구독

```bash
npx env-cmd uv run python consumer.py
```

## 이벤트 발행

```bash
npx env-cmd uv run python producer.py
```
