# GitHub Actions Runner

```bash
docker build \
    --build-arg GITHUB_URL="https://github.com/<owner>/<repo>" \
    --build-arg GITHUB_TOKEN="<token>" \
    -t github-actions-runner .

docker run -it --rm --name github-actions-runner github-actions-runner
```
