# Kafka Demo

- <https://github.com/python-poetry/poetry>
- <https://github.com/volopivoshenko/poetry-plugin-dotenv>
- <https://github.com/dpkp/kafka-python>
- <https://github.com/fastavro/fastavro>
- <https://fastavro.readthedocs.io/>

## `.env` 파일 준비

```bash
cp .env.default .env
```

## Poetry 환경 설정

```bash
poetry env use $(cat .python-version)

poetry self add poetry-plugin-dotenv

poetry install
```

## 정적 분석

```bash
poetry run bash -c "isort . && black . && ruff ."
```

## 이벤트 구독

```bash
poetry run python consumer.py
```

## 이벤트 발행

```bash
poetry run python producer.py
```
