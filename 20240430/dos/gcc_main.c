// See <https://nullprogram.com/blog/2014/12/09/>

asm (
    ".code16gcc\n"
    "call   main\n"
    "mov    ah, 0x4C\n"
    "int    0x21\n"
);

void set_mode(unsigned short mode) {
    asm volatile (
        "mov    ax, %0\n"
        "int    0x10\n"
        :
        : "r" (mode)
        : "ax"
    );
}

void put_pixel(short x, short y, unsigned char color) {
    asm (
        "mov    ax, 0xA000\n"
        "mov    es, ax\n"
        "mov    bx, %1\n"
        "mov    ax, %2\n"
        "mov    cx, 320\n"
        "imul   ax, cx\n"
        "add    ax, bx\n"
        "mov    di, ax\n"
        "mov    al, %0\n"
        "mov    es:[di], al\n"
        :
        : "r" (color), "r" (x), "r" (y)
        : "ax", "bx", "cx"
    );
}

int main() {
    set_mode(0x13);

    for (;;) {
        for (int i = 0; i < 200; i += 1) {
            for (int j = 0; j < 320; j += 1) {
                put_pixel(j, i, i + j);
            }
        }
    }

    set_mode(0x3);

    return 0;
}
