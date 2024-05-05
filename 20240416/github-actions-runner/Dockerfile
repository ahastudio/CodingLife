FROM mcr.microsoft.com/dotnet/runtime:8.0.3-bookworm-slim-amd64

WORKDIR /u/actions-runner

RUN apt-get update && \
    apt-get install -y curl && \
    curl -o actions-runner-linux-x64.tar.gz \
        -L https://github.com/actions/runner/releases/download/v2.316.1/actions-runner-linux-x64-2.316.1.tar.gz && \
    tar xzf actions-runner-linux-x64.tar.gz && \
    rm actions-runner-linux-x64.tar.gz && \
    rm -rf /var/lib/apt/lists/*

ENV RUNNER_ALLOW_RUNASROOT "1"

ARG GITHUB_URL
ARG GITHUB_TOKEN

RUN ./config.sh --unattended --url $GITHUB_URL --token $GITHUB_TOKEN --replace

ENV PATH "/u/bin:$PATH"

RUN mkdir /u/bin

COPY scripts/* /u/bin/

CMD ./run.sh
