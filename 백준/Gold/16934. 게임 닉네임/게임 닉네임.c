#include <stdio.h>
#include <string.h>

#define TRIE_DIFF           'a'
#define TRIE_ROOT           0
#define TRIE_MAX_NODE       728279
#define TRIE_ALPHABET       26
#define TRIE_STR_MAX_LEN    16

int _idx;
int _num[TRIE_MAX_NODE];
int _next[TRIE_MAX_NODE][TRIE_ALPHABET];

int
trie_new()
{
    _num[_idx] = 0;
    memset(&_next[_idx], 0, TRIE_ALPHABET * sizeof(int));
    return _idx++;
}

void
trie_insert(char *str)
{
    int idx;
    int curr;
    char *ch;

    ch = str;
    for (curr = TRIE_ROOT; *ch; ch++, curr = _next[curr][idx]) {
        idx = *ch - TRIE_DIFF;
        if (!_next[curr][idx]) {
            _next[curr][idx] = trie_new();
            break;
        }
    }
    if (*ch) {
        str = ch + 1;
        for (; *ch; ch++, curr = _next[curr][idx]) {
            idx = *ch - TRIE_DIFF;
            if (!_next[curr][idx])
                _next[curr][idx] = trie_new();
        }
        *str = '\0';
    }
    else if (_num[curr])
        sprintf(ch, "%d", _num[curr] + 1);
    _num[curr]++;
}

int
main()
{
    int n;
    char str[TRIE_STR_MAX_LEN];

    _idx = 0;
    trie_new();
    scanf("%d", &n);
    while (n--) {
        scanf("%s", str);
        trie_insert(str);
        printf("%s\n", str);
    }
    return 0;
}