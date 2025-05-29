# RAG Demo

## Generate `.env` file

```bash
cp .env.example .env
```

## Install dependencies

```bash
uv sync
```

## Run formatter

```bash
uvx ruff format
```

## Run linter

```bash
uvx ruff check --fix .
```

## Run server

```bash
uv run uvicorn main:app --reload
```

## Build container

```bash
docker build --platform linux/amd64 -t rag-demo .
```

## Run container

```bash
docker run -it --rm --name rag-demo \
  --env-file .env \
  -p 8000:8000 \
  rag-demo
```
