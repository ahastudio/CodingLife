# Demo

- <https://platform.openai.com/docs/guides/responses-vs-chat-completions>
- <https://platform.openai.com/docs/api-reference/chat>

```bash
echo '3.13.2' > .python-version

uv init

uv venv --python $(cat .python-version)

uv add --dev black isort ruff

uv add python-dotenv

uvx black . && uvx isort . && uvx ruff check .

uv run main.py
```
