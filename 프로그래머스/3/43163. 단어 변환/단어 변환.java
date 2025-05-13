import java.util.ArrayDeque;

class Solution {
    private static final int FAIL = 0;

    private final static class Edge {
        int to;
        Edge next;

        Edge(int to, Edge next) {
            this.to = to;
            this.next = next;
        }
    }

    private int len;
    private Edge[] adj;

    private int bfs(int start, int end, int size) {
        int node;
        int[] dist;
        Edge edge;
        ArrayDeque<Integer> q;

        dist = new int[size];
        dist[start] = 1;
        q = new ArrayDeque<>(size);
        q.addLast(start);
        while (!q.isEmpty()) {
            node = q.pollFirst();
            for (edge = adj[node]; edge != null; edge = edge.next) {
                if (dist[edge.to] == 0) {
                    if (edge.to == end) {
                        return dist[node];
                    }
                    q.addLast(edge.to);
                    dist[edge.to] = dist[node] + 1;
                }
            }
        }
        return FAIL;
    }

    private boolean isConnected(char[] s1, char[] s2) {
        int i;
        boolean diff;

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

    public int solution(String begin, String target, String[] words) {
        int i;
        int j;
        int end;
        int size;
        char[][] arr;
        boolean flag;

        len = begin.length();
        size = words.length;
        adj = new Edge[size + 1];
        arr = new char[size + 1][];
        arr[size] = begin.toCharArray();
        flag = true;
        end = 0;
        for (i = 0; i < size; i++) {
            if (flag && words[i].equals(target)) {
                end = i;
                flag = false;
            }
            arr[i] = words[i].toCharArray();
            for (j = 0; j < i; j++) {
                if (isConnected(arr[i], arr[j])) {
                    adj[i] = new Edge(j, adj[i]);
                    adj[j] = new Edge(i, adj[j]);
                }
            }
        }
        if (flag) {
            return FAIL;
        }
        for (i = 0; i < size; i++) {
            if (isConnected(arr[size], arr[i])) {
                adj[size] = new Edge(i, adj[size]);
                adj[i] = new Edge(size, adj[i]);
            }
        }
        return bfs(size, end, size + 1);
    }
}
