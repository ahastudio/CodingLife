FROM ghcr.io/astral-sh/uv:0.6.13-alpine3.21

ENV APP_HOME=/u/server

WORKDIR $APP_HOME

COPY pyproject.toml uv.lock ./

RUN uv venv .venv && \
    uv sync

ENV PATH="$APP_HOME/.venv/bin:$PATH"

COPY . .

EXPOSE 8000

ENTRYPOINT ["uvicorn", "main:app", "--host", "0.0.0.0"]
