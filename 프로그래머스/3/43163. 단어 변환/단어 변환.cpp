#include <string>
#include <vector>
#include <deque>

using namespace std;

constexpr int NIL = -1;
constexpr int FAIL = 0;

int len;

struct Edge {
    int to;
    Edge* next;

    Edge(int to, Edge* next) : to(to), next(next) {}
};

bool connected(string& s1, string& s2) {
    bool diff;
    int i;

    diff = false;
    for (i = 0; i < len; i++) {
        if (s1[i] != s2[i]) {
            if (diff) {
                return false;
            }
            diff = true;
        }
    }
    return true;
}

int bfs(int start, int end, vector<Edge*>& adj) {
    int i;
    int j;
    int to;
    int cur;
    int size;
    Edge* edge;
    deque<int> q;
    vector<bool> visited;

    visited.resize(adj.size());
    q.emplace_back(start);
    visited[start] = true;
    for (i = 1; (size = q.size()) != 0; i++) {
        for (j = 0; j < size; j++) {
            cur = q.front();
            q.pop_front();
            for (edge = adj[cur]; edge != nullptr; edge = edge->next) {
                to = edge->to;
                if (!visited[to]) {
                    if (to == end) {
                        return i;
                    }
                    q.emplace_back(to);
                    visited[to] = true;
                }
            }
        }
    }
    return FAIL;
}

int solution(string begin, string target, vector<string> words) {
    int i;
    int j;
    int end;
    int size;
    vector<Edge*> adj;

    len = begin.size();
    size = words.size();
    adj.resize(size + 1, nullptr);
    end = NIL;
    for (i = 0; i < size; i++) {
        if (connected(begin, words[i])) {
            adj[size] = new Edge(i, adj[size]);
            adj[i] = new Edge(size, adj[i]);
        }
        for (j = 0; j < i; j++) {
            if (connected(words[i], words[j])) {
                adj[i] = new Edge(j, adj[i]);
                adj[j] = new Edge(i, adj[j]);
            }
        }
        if (words[i] == target) {
            end = i;
        }
    }
    if (end == NIL) {
        return FAIL;
    }
    return bfs(size, end, adj);
}