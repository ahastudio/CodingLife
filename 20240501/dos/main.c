#include <stdio.h>
#include <conio.h>
#include <string.h>
#include <malloc.h>
#include <dos.h>

#include "graphic.h"
#include "sprite.h"

int main() {
    if (init_graphic()) {
        return -1;
    }

    struct SPRITE_SET far* sprite_set = load_sprite_set("ASSETS\\SPRITE.SPR");
    if (!sprite_set) {
        printf("Sprite Not Found!\n");
        return -1;
    }

    for (word i = 0; i < 1000; i += 1) {
        int index = (i % 5) + ((i / 20) % 2) * 10;

        struct SPRITE far* sprite = &sprite_set->sprites[index];
        clear_screen(255);
        draw_sprite(sprite, 10 + i, 100);
        flip_page();

        delay(1);
    }

    unload_sprite_set(sprite_set);

    end_graphic();

    return 0;
}
