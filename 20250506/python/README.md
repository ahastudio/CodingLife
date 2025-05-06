# Slack Bot Demo

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

## Run script

```bash
uv run main.py
```
