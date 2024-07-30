#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define HASH_PRIME              31U
#define HASH_MAP_CAPACITY       32
#define PRIORITY_QUEUE_CAPACITY 10
#define STRING_SIZE             240

typedef struct entry_s entry_t;
struct entry_s {
    void *key;
    void *value;
};

typedef struct linked_list_s linked_list_t;
struct linked_list_s {
    void *data;
    linked_list_t *next;
};

typedef struct hash_map_s hash_map_t;
struct hash_map_s {
    unsigned int (*hash_code)(void *);
    int (*cmp)(void *, void *);
    linked_list_t *table[HASH_MAP_CAPACITY];
};

typedef struct priority_queue_s priority_queue_t;
struct priority_queue_s {
    int cap;
    int size;
    int (*cmp)(void *, void *);
    void **heap;
};

typedef struct trie_s trie_t;
struct trie_s {
    hash_map_t *next;
    priority_queue_t *pq;
};

entry_t *
entry_new(void *key, void *value)
{
    entry_t *entry;

    entry = (entry_t *)malloc(sizeof(entry_t));
    entry->key = key;
    entry->value = value;
    return entry;
}

void
linked_list_add_first(linked_list_t **list, void *data)
{
    linked_list_t *node;

    node = (linked_list_t *)malloc(sizeof(linked_list_t));
    node->data = data;
    node->next = *list;
    *list = node;
}

hash_map_t *
hash_map_new(unsigned int (*hash_code)(void *), int (*cmp)(void *, void *))
{
    hash_map_t *map;

    map = (hash_map_t *)malloc(sizeof(hash_map_t));
    map->hash_code = hash_code;
    map->cmp = cmp;
    memset(map->table, 0, HASH_MAP_CAPACITY * sizeof(linked_list_t *));
    return map;
}

void
hash_map_put(hash_map_t *map, void *key, void *value)
{
    linked_list_add_first(&(map->table[(map->hash_code(key)) & (HASH_MAP_CAPACITY - 1)]), entry_new(key, value));
}

entry_t *
hash_map_get_enrty(linked_list_t *list, void *key, int (*cmp)(void *, void *))
{
    for (; list; list = list->next) {
        if (!cmp(((entry_t *)(list->data))->key, key))
            return (entry_t *)(list->data);
    }
    return NULL;
}

void *
hash_map_get(hash_map_t *map, void *key)
{
    entry_t *entry;
    entry = hash_map_get_enrty(map->table[(map->hash_code(key)) & (HASH_MAP_CAPACITY - 1)], key, map->cmp);
    if (!entry)
        return NULL;
    return entry->value;
}

priority_queue_t *
priority_queue_new(int (*cmp)(void *, void *))
{
    priority_queue_t *pq;

    pq = (priority_queue_t *)malloc(sizeof(priority_queue_t));
    pq->cap = PRIORITY_QUEUE_CAPACITY;
    pq->size = 0;
    pq->cmp = cmp;
    pq->heap = (void **)malloc(pq->cap * sizeof(void *));
    return pq;
}

void
priority_queue_offer(priority_queue_t *pq, void *val)
{
    int child;
    int parent;
    int (*cmp)(void *, void *);
    void **heap;

    cmp = pq->cmp;
    child = ++pq->size;
    if (child >= pq->cap)
        pq->heap = (void **)realloc(pq->heap, (pq->cap <<= 1) * sizeof(void *));
    heap = pq->heap;
    heap[child] = val;
    while (child > 1) {
        parent = child >> 1;
        if (cmp(val, heap[parent]) >= 0)
            break;
        heap[child] = heap[parent];
        child = parent;
    }
    heap[child] = val;
}

void *
priority_queue_poll(priority_queue_t *pq)
{
    int size;
    int child;
    int parent;
    int (*cmp)(void *, void *);
    void *rc;
    void *val;
    void **heap;

    if (!pq->size) {
        return NULL;
    }
    cmp = pq->cmp;
    heap = pq->heap;
    rc = heap[1];
    val = heap[pq->size];
    size = --pq->size;
    parent = 1;
    child = parent << 1;
    while (child <= size) {
        if (child < size && cmp(heap[child], heap[child + 1]) > 0)
            child++;
        if (cmp(val, heap[child]) <= 0)
            break;
        heap[parent] = heap[child];
        parent = child;
        child = parent << 1;
    }
    heap[parent] = val;
    return rc;
}

trie_t *
trie_new(unsigned int (*hash_code)(void *), int (*cmp)(void *, void *))
{
    trie_t *trie;

    trie = (trie_t *)malloc(sizeof(trie_t));
    trie->next = hash_map_new(hash_code, cmp);
    trie->pq = priority_queue_new(cmp);
    return trie;
}

void
trie_insert(trie_t *trie, char *token)
{
    char *key;
    trie_t *next;

    for (; token; token = strtok(NULL, " "), trie = next) {
        if (!(next = hash_map_get(trie->next, token))) {
            key = strdup(token);
            next = trie_new(trie->next->hash_code, trie->next->cmp);
            hash_map_put(trie->next, key, next);
            priority_queue_offer(trie->pq, key);
        }
    }
}

void
trie_print(trie_t *trie, char *indent)
{
    char *key;
    hash_map_t *map;
    priority_queue_t *pq;

    map = trie->next;
    pq = trie->pq;
    while (pq->size) {
        key = priority_queue_poll(pq);
        printf("%s%s\n", indent, key);
        trie_print(hash_map_get(map, key), indent - 2);
    }
}

unsigned int
string_hash_code(void *ptr)
{
    unsigned int rc;
    unsigned char *str;

    str = (unsigned char *)ptr;
    for (rc = 0U; *str; str++)
        rc = HASH_PRIME * rc + *str;
    return rc;
}

int
string_compare(void *ptr1, void *ptr2)
{
    unsigned char *str1;
    unsigned char *str2;

    str1 = (unsigned char *)ptr1;
    str2 = (unsigned char *)ptr2;
    for (; *str1 && *str1 == *str2; str1++, str2++);
    if (*str1 == *str2)
        return 0;
    if (*str1 < *str2)
        return -1;
    return 1;
}

int
main()
{
    int n;
    int i;
    int num;
    int max;
    int len;
    char str[STRING_SIZE];
    char *indent;
    trie_t *trie;

    scanf("%d", &n);
    trie = trie_new(&string_hash_code, &string_compare);
    max = 0;
    for (i = 0; i < n; i++) {
        scanf("%d %[^\n]", &num, str);
        if (num > max)
            max = num;
        trie_insert(trie, strtok(str, " "));
    }
    len = (max << 1) - 1;
    indent = (char *)malloc(len * sizeof(char));
    memset(indent, '-', (len - 1) * sizeof(char));
    indent[len - 1] = '\0';
    trie_print(trie, &(indent[len - 1]));
    return 0;
}