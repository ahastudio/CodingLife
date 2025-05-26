FROM ghcr.io/astral-sh/uv:0.6.13-alpine3.21

ENV APP_HOME=/u/app

WORKDIR $APP_HOME

COPY pyproject.toml uv.lock ./

RUN uv venv .venv && \
    uv sync

ENV PATH="$APP_HOME/.venv/bin:$PATH"

COPY . .

ENTRYPOINT ["uv", "run", "main.py"]
