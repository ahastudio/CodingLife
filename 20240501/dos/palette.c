#include <stdio.h>
#include <malloc.h>

#include "palette.h"

static const int size = 256 * 3;

byte* load_palette(char* name) {
    FILE* fp = fopen(name, "rb");
    if (!fp) {
        printf("File Not Found: %s\n", name);
        return NULL;
    }

    byte* buffer = (byte*)malloc(size);

    fread(buffer, size, 1, fp);

    for (int i = 0; i < size; i += 1) {
        buffer[i] /= 4;
    }

    return buffer;
}
