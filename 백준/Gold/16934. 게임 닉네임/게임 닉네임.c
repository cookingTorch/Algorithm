#include <stdio.h>
#include <string.h>

#define TRIE_DIFF           'a'
#define TRIE_ROOT           0
#define TRIE_MAX_NODE       728279
#define TRIE_ALPHABET       26
#define TRIE_BUFF_SIZE      3249992
#define TRIE_STR_MAX_LEN    11

int _idx;
int _num[TRIE_MAX_NODE];
int _next[TRIE_MAX_NODE][TRIE_ALPHABET];
char *_ptr;
char _buff[TRIE_BUFF_SIZE];

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
    char *start;

    for (curr = TRIE_ROOT; *str; str++, curr = _next[curr][idx]) {
        *(_ptr++) = *str;
        idx = *str - TRIE_DIFF;
        if (!_next[curr][idx]) {
            curr = _next[curr][idx] = trie_new();
            str++;
            break;
        }
    }
    if (*str) {
        for (; *str; str++, curr = _next[curr][idx]) {
            idx = *str - TRIE_DIFF;
            if (!_next[curr][idx])
                _next[curr][idx] = trie_new();
        }
    }
    else if (_num[curr])
        _ptr += sprintf(_ptr, "%d", _num[curr] + 1);
    _num[curr]++;
}

int
main()
{
    int n;
    char str[TRIE_STR_MAX_LEN];

    _idx = 0;
    trie_new();
    _ptr = _buff;
    scanf("%d", &n);
    while (n--) {
        scanf("%s", str);
        trie_insert(str);
        *(_ptr++) = '\n';
    }
    *_ptr = '\0';
    printf("%s", _buff);
    return 0;
}