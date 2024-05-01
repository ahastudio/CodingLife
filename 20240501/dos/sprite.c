#include <stdio.h>
#include <string.h>
#include <malloc.h>

#include "sprite.h"

const char SPRITE_CODE[] = "CSHSPRFILE";

static void _ffread(void far* pointer, word size, FILE* fp) {
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
