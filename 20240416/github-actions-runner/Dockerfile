FROM mcr.microsoft.com/dotnet/runtime:8.0.3-bookworm-slim-arm64v8

WORKDIR /u/actions-runner

RUN apt update && \
    apt install -y curl && \
    curl -o actions-runner-linux.tar.gz \
        -L https://github.com/actions/runner/releases/download/v2.315.0/actions-runner-linux-arm64-2.315.0.tar.gz && \
    tar xzf actions-runner-linux.tar.gz && \
    rm actions-runner-linux.tar.gz && \
    rm -rf /var/lib/apt/lists/*

ENV RUNNER_ALLOW_RUNASROOT "1"

ARG GITHUB_URL
ARG GITHUB_TOKEN

RUN ./config.sh --unattended --url $GITHUB_URL --token $GITHUB_TOKEN --replace

ENV PATH "/u/bin:$PATH"

RUN mkdir /u/bin

COPY scripts/* /u/bin/

CMD ./run.sh
