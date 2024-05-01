#include <stdio.h>
#include <conio.h>
#include <string.h>
#include <malloc.h>
#include <dos.h>

const int SCREEN_WIDTH = 320;
const int SCREEN_HEIGHT = 200;

typedef unsigned char byte;
typedef unsigned short word;
typedef unsigned long dword;

struct SPRITE {
    dword file_point;
    word width;
    word height;
    byte far* image;
};

struct SPRITE_SET {
    word count;
    struct SPRITE far* sprites;
};

const char SPRITE_CODE[] = "CSHSPRFILE";

// byte far* screen = (byte far*)0xA0000000;
byte far* screen = (byte far*)MK_FP(0xA000, 0x0000);

byte far* buffer = NULL;

void set_mode(word mode) {
    // __asm {
    //     mov     ax, mode
    //     int     10h
    // }

    union REGS r;
    r.x.ax = mode;
    int86(0x10, &r, &r);
}

void put_pixel(int x, int y, byte color) {
    // __asm {
    //     mov    ax, 0xA000
    //     mov    es, ax
    //     mov    bx, x
    //     mov    ax, y
    //     mov    cx, SCREEN_WIDTH
    //     mul    cx
    //     add    ax, bx
    //     mov    di, ax
    //     mov    al, color
    //     mov    es:[di], al
    // }

    screen[y * SCREEN_WIDTH + x] = color;
}

void draw_test_rect() {
    for (int i = 0; i < SCREEN_HEIGHT / 2; i += 1) {
        for (int j = 0; j < SCREEN_WIDTH / 2; j += 1) {
            put_pixel(j, i, i + j);
        }
    }
}

void draw_rect(int x, int y, int width, int height, byte color) {
    for (int i = 0; i < height; i += 1) {
        for (int j = 0; j < width; j += 1) {
            put_pixel(x + j, y + i, color);
        }
    }
}

void page_draw_rect(int x, int y, int width, int height, byte color) {
    byte far* target = buffer + y * 320 + x;

    for (int i = 0; i < height; i += 1) {
        for (int j = 0; j < width; j += 1) {
            *target++ = color;
        }
        target += SCREEN_WIDTH - width;
    }
}

void init_page_buffer() {
    if (buffer != NULL) {
        return;
    }

    buffer = (byte far*)_fmalloc(SCREEN_WIDTH * SCREEN_HEIGHT);
}

void close_page_buffer() {
    if (buffer == NULL) {
        return;
    }

    _ffree(buffer);

    buffer = NULL;
}

void flip_page() {
    _fmemcpy(screen, buffer, SCREEN_WIDTH * SCREEN_HEIGHT);
}

void _ffread(void far* pointer, word size, FILE* fp) {
	byte* buffer = (byte*)malloc(size);
    fread(buffer, size, 1, fp);
    _fmemcpy(pointer, buffer, size);
    free(buffer);
}

struct SPRITE_SET far* load_sprite_set(char* name) {
    FILE* fp = fopen(name, "rb");
    if (!fp) {
        printf("File Not Found: %s\n", name);
        return NULL;
    }

    char code[11];
    fread(code, 11, 1, fp);
    if (strcmp(SPRITE_CODE, code) != 0) {
        printf("Code Not Matched: %s\n", code);
        return NULL;
    }

    byte version[2];
    fread(version, 2, 1, fp);
    if (version[0] != 2 || version[1] != 0) {
        printf("Version Not Matched: %d.%d\n", version[0], version[1]);
        return NULL;
    }

    struct SPRITE_SET far* sprite_set = (struct SPRITE_SET far*)
            _fmalloc(sizeof(struct SPRITE_SET));
    _ffread(&sprite_set->count, sizeof(word), fp);

    sprite_set->sprites = (struct SPRITE far*)
            _fmalloc(sizeof(struct SPRITE) * sprite_set->count);

    for (word i = 0; i < sprite_set->count; i += 1) {
        struct SPRITE far* sprite = &sprite_set->sprites[i];
        _ffread(&sprite->file_point, sizeof(dword), fp);
    }

    for (word i = 0; i < sprite_set->count; i += 1) {
        struct SPRITE far* sprite = &sprite_set->sprites[i];
        fseek(fp, sprite->file_point, SEEK_SET);
        _ffread(&sprite->width, sizeof(word), fp);
        _ffread(&sprite->height, sizeof(word), fp);
        sprite->width += 1;
        sprite->height += 1;
        sprite->image = (byte far*)_fmalloc(sprite->width * sprite->height);
        _ffread(sprite->image, sprite->width * sprite->height, fp);
    }

    fclose(fp);

    return sprite_set;
}

void unload_sprite_set(struct SPRITE_SET far* sprite_set) {
    _ffree(sprite_set->sprites);
    _ffree(sprite_set);
}

void page_draw_sprite(struct SPRITE far* sprite, int x, int y) {
    byte far* source = sprite->image;
    byte far* target = buffer + y * 320 + x;

    for (int i = 0; i < sprite->height; i += 1) {
        for (int j = 0; j < sprite->width; j += 1) {
            *target++ = *source++;
        }
        target += SCREEN_WIDTH - sprite->width;
    }
}

void* load_file(char* name) {
    FILE* fp = fopen(name, "rb");
    if (!fp) {
        printf("File Not Found: %s\n", name);
        return NULL;
    }

    fseek(fp, 0, SEEK_END);
    int size = ftell(fp);

    fseek(fp, 0, SEEK_SET);

	byte* buffer = (byte*)malloc(size);
    fread(buffer, size, 1, fp);

    return buffer;
}

// inline void outportb(int port, byte data) {
//     __asm {
//         mov     dx, port
//         mov     al, data
//         out     dx, al
//     }
// }

void set_palette(byte* palette) {
    outp(0x3C8, 0);

    for (int i = 0; i < 256; i += 1) {
        // outp(0x3C8, i);

        for (int j = 0; j < 3; j += 1) {
            byte value = palette[i * 3 + j];
            outp(0x3C9, value / 4);
        }
    }
}

int main() {
    // (1) Hello, world!

    printf("Hello, world!\n");

    // (2) Mode 13h

    set_mode(0x13);

    // (3) Draw Rectangle

    // draw_test_rect();
    // getch();

    // (4) Move Ractangle

    // for (int i = 0; i < SCREEN_WIDTH - 10; i += 10) {
    //     draw_rect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, 0);
    //     draw_rect(i, 90, 10, 10, 1);
    // }

    // → Slow & Blinking

    // (5) Double Buffering (Virtual Page Flipping)

    // init_page_buffer();

    // for (int i = 0; i < SCREEN_WIDTH - 10; i += 2) {
    //     page_draw_rect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, 0);
    //     page_draw_rect(i, 90, 10, 10, 1);
    //     flip_page();
    // }

    // close_page_buffer();

    // (6) Load Sprite

    // struct SPRITE_SET far* sprite_set = load_sprite_set("ASSETS\\SPRITE.SPR");
    // if (!sprite_set) {
    //     printf("Sprite Not Found!\n");
    //     return -1;
    // }

    // printf("Sprite count: %d\n", sprite_set->count);

    // unload_sprite_set(sprite_set);

    // (7) Set Palette & Draw Sprite

    byte* palette = (byte*)load_file("ASSETS\\PALETTE.PAL");
    if (!palette) {
        printf("Palette Not Found!\n");
        return -1;
    }

    set_palette(palette);

    free(palette);

    init_page_buffer();

    struct SPRITE_SET far* sprite_set = load_sprite_set("ASSETS\\SPRITE.SPR");
    if (!sprite_set) {
        printf("Sprite Not Found!\n");
        return -1;
    }

    for (word i = 0; i < 100; i += 1) {
        int index = (i % 5) + ((i / 20) % 2) * 10;

        struct SPRITE far* sprite = &sprite_set->sprites[index];
        page_draw_rect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, 0);
        page_draw_sprite(sprite, 10, 10);
        flip_page();

        delay(1);
    }

    unload_sprite_set(sprite_set);

    close_page_buffer();

    // set_mode(0x3);

    return 0;
}
