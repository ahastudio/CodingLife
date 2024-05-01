#ifndef __GRAPHIC_H__
#define __GRAPHIC_H__

typedef unsigned char byte;
typedef unsigned short word;
typedef unsigned long dword;

struct SPRITE;

int init_graphic();
void end_graphic();
void flip_page();
void clear_screen(byte color);
void put_pixel(int x, int y, byte color);
void draw_rect(int x, int y, int width, int height, byte color);
void draw_sprite(struct SPRITE far* sprite, int x, int y);

#endif __GRAPHIC_H__
