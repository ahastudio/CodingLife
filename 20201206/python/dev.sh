docker run --rm -it \
    -v $(pwd)/_profile:/root/.bashrc \
    -v $(pwd):/work \
    -w /work \
    python:3.9 bash
