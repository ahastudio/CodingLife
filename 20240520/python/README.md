# Python Socket Programming (HTTP example)

## Set up virtual environment

```bash
poetry env use $(cat .python-version)

poetry install
```

## Lint

```bash
poetry run black .
```

## Run client

```bash
poetry run python client.py
```

## Run server

```bash
poetry run python server.py
```
