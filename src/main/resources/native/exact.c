
#include <stddef.h>

int exact(int n, int degrees[]) {
    if (n <= 0 || degrees == NULL) {
        return 0;
    }

    int max = degrees[0];
    for (int i = 1; i < n; i++) {
        if (degrees[i] > max) {
            max = degrees[i];
        }
    }
    return max;
}