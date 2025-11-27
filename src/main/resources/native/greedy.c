#include <stdlib.h>
#include <time.h>

static int cmp_desc(const void *a, const void *b) {
    int x = *(const int *)a;
    int y = *(const int *)b;
    return (y - x);
}

int greedy(int n, int degrees[]) {
    if (n <= 0 || degrees == NULL) return 0;

    qsort(degrees, n, sizeof(int), cmp_desc);

    int k = n / 2;
    if (k == 0) k = n;

    int sum = 0;
    for (int i = 0; i < k; i++) {
        sum += degrees[i];
    }
    return sum;
}


int random_greedy(int n, int degrees[]) {
    if (n <= 0 || degrees == NULL) return 0;


    static int seeded = 0;
    if (!seeded) {
        srand((unsigned int)time(NULL));
        seeded = 1;
    }

    int *copy = (int *)malloc(n * sizeof(int));
    if (copy == NULL) return 0;

    for (int i = 0; i < n; i++) {
        copy[i] = degrees[i];
    }


    for (int i = n - 1; i > 0; i--) {
        int j = rand() % (i + 1);
        int tmp = copy[i];
        copy[i] = copy[j];
        copy[j] = tmp;
    }

    int k = n / 2;
    if (k == 0) k = n;

    int sum = 0;
    for (int i = 0; i < k; i++) {
        sum += copy[i];
    }

    free(copy);
    return sum;
}