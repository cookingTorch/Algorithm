#include <stdio.h>

typedef struct Edge {
    int v;
    int w;
    struct Edge* next;
} Edge;

const int INF = 2147483647 >> 1;

int n;
int dist[801], visited[801];
int pq[400001][2];
int pq_size = 0;
int e_cnt = 0;
Edge edges[400001];
Edge* adj[801];

Edge* new_edge(int, int, Edge*);
int dijkstra(int, int);
void offer(int, int);
void poll(int*, int*);

int main() {
    int e, u, v, w, tmp, ans, ans2, i;
    Edge* edge;

    scanf("%d %d", &n, &e);
    for (i = 1; i <= n; i++) {
        adj[i] = 0;
    }
    for (i = 0; i < e; i++) {
        scanf("%d %d %d", &u, &v, &w);
        adj[u] = new_edge(v, w, adj[u]);
        adj[v] = new_edge(u, w, adj[v]);
    }
    scanf("%d %d", &u, &v);
    if ((tmp = dijkstra(1, u)) != -1) {
        ans = tmp;
        if ((tmp = dijkstra(u, v)) != -1) {
            ans += tmp;
            if ((tmp = dijkstra(v, n)) != -1) {
                ans += tmp;
            } else {
                ans = INF;
            }
        } else {
            ans = INF;
        }
    } else {
        ans = INF;
    }
    if ((tmp = dijkstra(1, v)) != -1) {
        ans2 = tmp;
        if ((tmp = dijkstra(v, u)) != -1) {
            ans2 += tmp;
            if ((tmp = dijkstra(u, n)) != -1) {
                ans2 += tmp;
            } else {
                ans2 = INF;
            }
        } else {
            ans2 = INF;
        }
    } else {
        ans2 = INF;
    }
    ans = ans < ans2 ? ans : ans2;
    printf("%d", ans == INF ? -1 : ans);
}

Edge* new_edge(int v, int w, Edge* next) {
    edges[e_cnt].v = v;
    edges[e_cnt].w = w;
    edges[e_cnt].next = next;
    return &edges[e_cnt++];
}

int dijkstra(int s, int e) {
    int i, v, nv, d;
    Edge* edge;

    for (i = 1; i <= n; i++) {
        dist[i] = INF;
        visited[i] = 0;
    }
    pq_size = 0;
    dist[s] = 0;
    offer(s, 0);
    while (pq_size) {
        poll(&v, &d);
        if (visited[v]) {
            continue;
        }
        if (v == e) {
            return dist[e];
        }
        visited[v] = 1;
        for (edge = adj[v]; edge; edge = edge->next) {
            nv = edge->v;
            if (d + edge->w < dist[nv]) {
                dist[nv] = d + edge->w;
                offer(nv, dist[nv]);
            }
        }
    }
    return -1;
}

void offer(int v, int d) {
    int i = ++pq_size;

    while (i > 1 && pq[i >> 1][1] > d) {
        pq[i][0] = pq[i >> 1][0];
        pq[i][1] = pq[i >> 1][1];
        i >>= 1;
    }
    pq[i][0] = v;
    pq[i][1] = d;
}

void poll(int* v, int* d) {
    int last_v = pq[pq_size][0];
    int last_d = pq[pq_size--][1];
    int parent = 1, child = 2;

    *v = pq[1][0];
    *d = pq[1][1];
    while (child <= pq_size) {
        if (child + 1 <= pq_size && pq[child + 1][1] < pq[child][1]) {
            child++;
        }
        if (last_d <= pq[child][1]) {
            break;
        }
        pq[parent][0] = pq[child][0];
        pq[parent][1] = pq[child][1];
        parent = child;
        child <<= 1;
    }
    pq[parent][0] = last_v;
    pq[parent][1] = last_d;
}