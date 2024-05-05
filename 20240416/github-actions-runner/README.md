# GitHub Actions Runner

```bash
docker build \
    --platform linux/amd64 \
    --build-arg GITHUB_URL="https://github.com/<owner>/<repo>" \
    --build-arg GITHUB_TOKEN="<token>" \
    -t github-actions-runner .

docker run -d --name github-actions-runner \
    --platform linux/amd64 \
    github-actions-runner
dockre logs -f github-actions-runner
```
