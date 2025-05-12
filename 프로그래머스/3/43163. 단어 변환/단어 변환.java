class Solution {
    private static final int FAIL = 0;
    private static final int INF = Integer.MAX_VALUE >>> 1;

    private final class Edge {
        int to;
        Edge next;

        Edge(int to, Edge next) {
            this.to = to;
            this.next = next;
        }
    }

    private int len;
    private int targetIdx;
    private boolean[] visited;
    private Edge[] adj;

    private int dfs(int node) {
        int min;
        int next;
        Edge edge;

        min = INF;
        for (edge = adj[node]; edge != null; edge = edge.next) {
            next = edge.to;
            if (visited[next]) {
                continue;
            }
            if (next == targetIdx) {
                return 1;
            }
            visited[next] = true;
            min = Math.min(min, dfs(next));
            visited[next] = false;
        }
        return min + 1;
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
        int ans;
        int size;
        char[][] arr;
        boolean flag;

        len = begin.length();
        size = words.length;
        adj = new Edge[size + 1];
        arr = new char[size + 1][];
        arr[size] = begin.toCharArray();
        flag = true;
        for (i = 0; i < size; i++) {
            if (flag && words[i].equals(target)) {
                targetIdx = i;
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
        visited = new boolean[size + 1];
        visited[size] = true;
        ans = dfs(size);
        if (ans >= INF) {
            return FAIL;
        }
        return ans;
    }
}
