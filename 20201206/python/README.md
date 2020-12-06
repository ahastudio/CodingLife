# Python 3.9 in a Docker Container

Local:

```bash
./dev.sh

# Ignore error message: “bash: venv/bin/activate: No such file or directory”
```

In container:

```bash
pip install -U pip && pip install -U virtualenv && virtualenv venv

source venv/bin/activate
```
