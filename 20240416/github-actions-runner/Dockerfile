FROM mcr.microsoft.com/dotnet/runtime:8.0.3-jammy-amd64

WORKDIR /u/actions-runner

RUN apt-get update && \
    apt-get install -y sudo curl ca-certificates && \
    #
    install -m 0755 -d /etc/apt/keyrings && \
    curl -fsSL https://download.docker.com/linux/ubuntu/gpg \
        -o /etc/apt/keyrings/docker.asc && \
    chmod a+r /etc/apt/keyrings/docker.asc && \
    #
    echo \
        "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] \
            https://download.docker.com/linux/ubuntu \
            $(. /etc/os-release && echo "$VERSION_CODENAME") stable" \
        | tee /etc/apt/sources.list.d/docker.list > /dev/null && \
    apt-get update && \
    apt-get install -y docker-ce docker-ce-cli containerd.io \
        docker-buildx-plugin docker-compose-plugin && \
    #
    curl -o actions-runner-linux-x64.tar.gz \
        -L https://github.com/actions/runner/releases/download/v2.316.1/actions-runner-linux-x64-2.316.1.tar.gz && \
    tar xzf actions-runner-linux-x64.tar.gz && \
    rm actions-runner-linux-x64.tar.gz && \
    #
    rm -rf /var/lib/apt/lists/*

ENV RUNNER_ALLOW_RUNASROOT "1"

ARG GITHUB_URL
ARG GITHUB_TOKEN

RUN ./config.sh --unattended --url $GITHUB_URL --token $GITHUB_TOKEN --replace

ENV PATH "/u/bin:$PATH"

RUN mkdir /u/bin

COPY scripts/* /u/bin/

CMD ./run.sh
