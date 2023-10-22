# Kafka Demo

- <https://github.com/python-poetry/poetry>
- <https://github.com/volopivoshenko/poetry-plugin-dotenv>
- <https://github.com/dpkp/kafka-python>
- <https://github.com/fastavro/fastavro>
- <https://fastavro.readthedocs.io/>

```bash
poetry env use $(cat .python-version)

poetry self add poetry-plugin-dotenv

poetry install
```

```bash
poetry run bash -c "isort . && black . && ruff ."
```

```bash
poetry run python producer.py
```

```bash
poetry run python consumer.py
```
