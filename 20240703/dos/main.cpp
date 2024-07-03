#include <stdio.h>
#include <string.h>
#include <conio.h>
#include <malloc.h>
#include <dos.h>

typedef unsigned char byte;
typedef unsigned short word;
typedef unsigned long dword;

const word SCREEN_WIDTH = 320;
const word SCREEN_HEIGHT = 200;

byte far* video = (byte far*)0xA0000000L;
byte far* screen = (byte far*)_fmalloc(SCREEN_WIDTH * SCREEN_HEIGHT);

typedef struct image_t {
    word width;
    word height;
    byte far* pixels;
    byte palette[256 * 3];
} image_t;

size_t _ffread(void far* buf, size_t elsize, size_t nelem, FILE *fp) {
    size_t bytes = elsize * nelem;
    byte* buffer = (byte*)malloc(bytes);
    size_t read = fread(buffer, bytes, 1, fp);
    _fmemcpy(buf, buffer, bytes);
    free(buffer);
    return read / elsize;
}

void set_video_mode(int mode) {
    union REGS regs;
    regs.h.ah = 0;
    regs.h.al = mode;
    int86(0x10, &regs, &regs);

    // __asm {
    //     mov ax, mode;
    //     int 10h;
    // }
}

void set_palette(byte far* palette) {
    outp(0x03C8, 0);

    for (int i = 0; i < 256 * 3; i++) {
        outp(0x03C9, palette[i] >> 2);
    }
}

void page_flip() {
    _fmemcpy(video, screen, SCREEN_WIDTH * SCREEN_HEIGHT);
}

void clear_screen(byte color) {
    _fmemset(screen, color, SCREEN_WIDTH * SCREEN_HEIGHT);
}

void put_pixel(int x, int y, byte color) {
    if (x < 0 || x >= SCREEN_WIDTH || y < 0 || y >= SCREEN_HEIGHT) {
        return;
    }

    screen[SCREEN_WIDTH * y + x] = color;
}

void draw_rect(int x, int y, int width, int height, byte color) {
    for (int i = y; i < y + height; i++) {
        for (int j = x; j < x + width; j++) {
            put_pixel(j, i, color);
        }
    }
}

void draw_image(int x, int y, image_t far* image) {
    for (int i = 0; i < image->height; i++) {
        for (int j = 0; j < image->width; j++) {
            byte color = image->pixels[image->width * i + j];
            put_pixel(x + j, y + i, color);
        }
    }
}

void draw_sprite(int x, int y, image_t far* image) {
    for (int i = 0; i < image->height; i++) {
        for (int j = 0; j < image->width; j++) {
            byte color = image->pixels[image->width * i + j];
            if (color != 0) {
                put_pixel(x + j, y + i, color);
            }
        }
    }
}

image_t far* load_bmp(const char* filename) {
    FILE* file = fopen(filename, "rb");
    if (file == NULL) {
        return NULL;
    }

    image_t far* image = (image_t far*)_fmalloc(sizeof(image_t));

    fseek(file, 0, SEEK_END);
    long size = ftell(file);

    fseek(file, 2 + 4 + 4 + 4 + 4, SEEK_SET);

    dword width;
    dword height;
    fread(&width, 4, 1, file);
    fread(&height, 4, 1, file);

    image->width = width;
    image->height = height;
    image->pixels = (byte far*)_fmalloc(image->width * image->height);

    fseek(file, 54, SEEK_SET);

    for (int i = 0; i < 256; i++)  {
        _ffread(image->palette + i * 3 + 2, 1, 1, file);
        _ffread(image->palette + i * 3 + 1, 1, 1, file);
        _ffread(image->palette + i * 3 + 0, 1, 1, file);
        fseek(file, 1, SEEK_CUR);
    }

    for (int i = image->height - 1; i >= 0; i--) {
        _ffread(image->pixels + image->width * i, image->width, 1, file);
    }

    return image;
}

int main() {
    image_t far* image = load_bmp("assets\\image.bmp");

    set_video_mode(0x13);

    set_palette(image->palette);

    int x = 0;
    int y = 0;
    int x_velocity = 1;

    byte color = 0;

    int quit = 0;

    while (!quit) {
        color = (color + 1) % 16;

        x += x_velocity;
        if (x <= 0 || x >= SCREEN_WIDTH - image->width) {
            x_velocity *= -1;
        }

        clear_screen(0x00);
        draw_rect(x + 10, y + 10, 40, 40, color);
        // draw_image(x, y, image);
        draw_sprite(x, y, image);
        page_flip();
    }

    _ffree(image);

    set_video_mode(0x03);

    return 0;
}
