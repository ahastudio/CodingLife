#include <stdio.h>
#include <conio.h>
#include <string.h>
#include <malloc.h>
#include <dos.h>

#include "graphic.h"
#include "palette.h"
#include "sprite.h"

const int SCREEN_WIDTH = 320;
const int SCREEN_HEIGHT = 200;

const byte TRANSPARENT = 0;

byte far* screen = NULL;

static void set_mode(word mode) {
    __asm {
        mov     ax, mode
        int     10h
    }
}

static void set_palette(byte* palette) {
    outp(0x3C8, 0);

    for (int i = 0; i < 256; i += 1) {
        for (int j = 0; j < 3; j += 1) {
            byte value = palette[i * 3 + j];
            outp(0x3C9, value);
        }
    }
}

int init_graphic() {
    set_mode(0x13);

    byte* palette = load_palette("ASSETS\\PALETTE.PAL");
    if (!palette) {
        printf("Palette Not Found!\n");
        return -1;
    }

    set_palette(palette);

    free(palette);

    // screen = (byte far*)MK_FP(0xA000, 0x0000);
    screen = (byte far*)_fmalloc(SCREEN_WIDTH * SCREEN_HEIGHT);

    return 0;
}

void end_graphic() {
    _ffree(screen);

    set_mode(0x3);
}

void flip_page() {
    byte far* video = (byte far*)MK_FP(0xA000, 0x0000);
    _fmemcpy(video, screen, SCREEN_WIDTH * SCREEN_HEIGHT);
}

void clear_screen(byte color) {
    _fmemset(screen, color, SCREEN_WIDTH * SCREEN_HEIGHT);
}

void put_pixel(int x, int y, byte color) {
    if (x < 0 || x >= SCREEN_WIDTH || y < 0 || y >= SCREEN_HEIGHT) {
        return;
    }

    screen[y * SCREEN_WIDTH + x] = color;
}

void draw_rect(int x, int y, int width, int height, byte color) {
    for (int i = 0; i < height; i += 1) {
        for (int j = 0; j < width; j += 1) {
            put_pixel(x + j, y + i, color);
        }
    }
}

void draw_sprite(struct SPRITE far* sprite, int x, int y) {
    for (int i = 0; i < sprite->height; i += 1) {
        for (int j = 0; j < sprite->width; j += 1) {
            byte color = sprite->image[i * sprite->width + j];
            if (color == TRANSPARENT) {
                continue;
            }
            put_pixel(x + j, y + i, color);
        }
    }
}
