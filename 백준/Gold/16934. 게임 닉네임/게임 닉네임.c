#include <stdio.h>
#include <stdlib.h>

#define TRIE_DIFF           'a'
#define TRIE_ALPHABET       26
#define TRIE_STR_MAX_LEN    16

typedef struct trie_s trie_t;
struct trie_s {
    int num;
    trie_t **next;
};

trie_t *
trie_new()
{
    int i;
    trie_t* trie;

    trie = (trie_t *) malloc(sizeof(trie_t));
    trie->next = (trie_t **) calloc(TRIE_ALPHABET, sizeof(trie_t *));
    return trie;
}

void
trie_insert(trie_t *trie, char *str)
{
    int i;
    int idx;
    int len;
    char *ch;
    trie_t *curr;
    trie_t *next;

    ch = str;
    for (curr = trie; *ch; ch++, curr = curr->next[idx]) {
        idx = *ch - TRIE_DIFF;
        if (!(curr->next[idx])) {
            curr->next[idx] = trie_new();
            break;
        }
    }
    printf("%.*s", (int) (ch - str + 1), str);
    if (*ch)
        for (; *ch; ch++, curr = curr->next[idx]) {
            idx = *ch - TRIE_DIFF;
            if (!(curr->next[idx]))
                curr->next[idx] = trie_new();
        }
    else if (curr->num)
        printf("%d", curr->num + 1);
    curr->num++;
    printf("\n");
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
    }
    return 0;
}