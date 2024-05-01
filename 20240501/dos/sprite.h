#ifndef __SPRITE_H__
#define __SPRITE_H__

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

struct SPRITE_SET far* load_sprite_set(char* name);
void unload_sprite_set(struct SPRITE_SET far* sprite_set);

#endif __SPRITE_H__
