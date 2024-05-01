docker run -it --rm --platform linux/amd64 \
    -v ~/data/open-watcom-v2:/usr/bin/watcom \
    -v ~/DosBox/work:/work \
    ubuntu:22.04 bash -c '
        export WATCOM=/usr/bin/watcom
        export PATH=$WATCOM/binl64:$PATH
        export INCLUDE=$WATCOM/h
        cd /work
        wcl -za99 -bcl=dos -fe=main.exe main.c graphic.c palette.c sprite.c
    '

dosbox -c '
    d:
    cd \work
    # main.com
    main.exe
'
