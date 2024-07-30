#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define HASH_MAP_CAPACITY 32

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
    int (*equals)(void *, void *);
    linked_list_t *table[HASH_MAP_CAPACITY];
};

long long _p;
long long _q;
hash_map_t *_map;

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
hash_map_new(unsigned int (*hash_code)(void *), int (*equals)(void *, void *))
{
    hash_map_t *map;

    map = (hash_map_t *)malloc(sizeof(hash_map_t));
    map->hash_code = hash_code;
    map->equals = equals;
    memset(map->table, 0, HASH_MAP_CAPACITY * sizeof(linked_list_t *));
    return map;
}

void
hash_map_put(hash_map_t *map, void *key, void *value)
{
    linked_list_add_first(&(map->table[(map->hash_code(key)) & (HASH_MAP_CAPACITY - 1)]), entry_new(key, value));
}

entry_t *
hash_map_get_enrty(linked_list_t *list, void *key, int (*equals)(void *, void *))
{
    for (; list; list = list->next) {
        if (equals(((entry_t *)(list->data))->key, key))
            return (entry_t *)(list->data);
    }
    return NULL;
}

void *
hash_map_get(hash_map_t *map, void *key)
{
    entry_t *entry;
    entry = hash_map_get_enrty(map->table[(map->hash_code(key)) & (HASH_MAP_CAPACITY - 1)], key, map->equals);
    if (!entry)
        return NULL;
    return entry->value;
}

unsigned int
long_long_hash_code(void *ptr)
{
    return (unsigned int) *(long long *) ptr;
}

int
long_long_equals(void *ptr1, void *ptr2)
{
    return *(long long *) ptr1 == *(long long *) ptr2;
}

void *
long_long_dup(long long num)
{
    long long *ptr;

    ptr = (long long *) malloc(sizeof(long long));
    *ptr = num;
    return ptr;
}

long long
infinite_sequence_get_dp(long long n)
{
    long long rc;
    long long*ptr;

    if ((ptr = (long long *) hash_map_get(_map, &n)))
        return *ptr;
    rc = infinite_sequence_get_dp(n / _p) + infinite_sequence_get_dp(n / _q);
    hash_map_put(_map, long_long_dup(n), long_long_dup(rc));
    return rc;
}

int
main()
{
    long long n;

    _map = hash_map_new(long_long_hash_code, long_long_equals);
    hash_map_put(_map, long_long_dup(0LL), long_long_dup(1LL));
    scanf("%lld %lld %lld", &n, &_p, &_q);
    printf("%lld", infinite_sequence_get_dp(n));
    return 0;
}