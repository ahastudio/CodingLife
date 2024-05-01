docker run -it --rm --platform linux/amd64 \
    -v .:/work \
    gcc:13.2.0 bash -c '
        cd work
        gcc -std=gnu99 -Os -nostdlib -m32 -march=i386 -masm=intel \
            -ffreestanding -Wl,--nmagic,--script=com.ld \
            -o main.com gcc_main.c
    '

docker run -it --rm --platform linux/amd64 \
    -v ~/data/open-watcom-v2:/usr/bin/watcom \
    -v ~/DosBox/work:/work \
    ubuntu:22.04 bash -c '
        export WATCOM=/usr/bin/watcom
        export PATH=$WATCOM/binl64:$PATH
        export INCLUDE=$WATCOM/h
        cd /work
        wcl -za99 -bcl=dos -fe=main.exe main.c
    '

# See <https://www.dosbox.com/wiki/Usage>

dosbox -c '
    d:
    cd \work
    # main.com
    main.exe
'
