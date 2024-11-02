# iCal sample

## uv

- <https://github.com/astral-sh/uv>
- <https://docs.astral.sh/uv/>

## requests

- <https://github.com/psf/requests>
- <https://requests.readthedocs.io/>

## ics.py

- <https://github.com/ics-py/ics-py>
- <https://icspy.readthedocs.io/>

## Set up

```bash
brew install uv
```

```bash
echo '3.12.6' > .python-version
```

```bash
uv init
```

```bash
uv venv .venv
```

```bash
uv add requests
uv add ics

uv add --dev black
uv add --dev isort
```

## Run

```bash
uv run main.py
```

```bash
uv run -- black .
uv run -- isort .
```
