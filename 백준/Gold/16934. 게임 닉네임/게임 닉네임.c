#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define TRIE_DIFF           'a'
#define TRIE_ALPHABET       26
#define TRIE_STR_MAX_LEN    16

typedef struct trie_s trie_t;
struct trie_s {
    int num;
    trie_t *next[TRIE_ALPHABET];
};

trie_t *
trie_new()
{
    int i;
    trie_t* trie;

    trie = (trie_t *) malloc(sizeof(trie_t));
    memset(trie->next, 0, TRIE_ALPHABET);
    return trie;
}

void
trie_insert(trie_t *trie, char *str)
{
    int idx;
    char *ch;
    trie_t *curr;

    ch = str;
    for (curr = trie; *ch; ch++, curr = curr->next[idx]) {
        idx = *ch - TRIE_DIFF;
        if (!(curr->next[idx])) {
            curr->next[idx] = trie_new();
            break;
        }
    }
    if (*ch) {
        str = ch + 1;
        for (; *ch; ch++, curr = curr->next[idx]) {
            idx = *ch - TRIE_DIFF;
            if (!(curr->next[idx]))
                curr->next[idx] = trie_new();
        }
        *str = '\0';
    }
    else if (curr->num)
        sprintf(ch, "%d", curr->num + 1);
    curr->num++;
}

int
main()
{
    int n;
    char str[TRIE_STR_MAX_LEN];
    trie_t *trie;

    scanf("%d", &n);
    trie = trie_new();
    while (n-- > 0) {
        scanf("%s", str);
        trie_insert(trie, str);
        printf("%s\n", str);
    }
    return 0;
}