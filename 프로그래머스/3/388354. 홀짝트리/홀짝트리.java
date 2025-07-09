import java.util.HashMap;

class Solution {
    private static int[] roots;
    private static int[][] cnt;
    private static boolean[] reverse;

    private static int find(int v) {
        if (roots[v] <= 0) {
            return v;
        }
        return roots[v] = find(roots[v]);
    }

    private static void union(int u, int v) {
        if (roots[u] > roots[v]) {
            roots[u] = v;
            cnt[v][0] += cnt[u][0];
            cnt[v][1] += cnt[u][1];
        } else {
            if (roots[u] == roots[v]) {
                roots[u]--;
            }
            roots[v] = u;
            cnt[u][0] += cnt[v][0];
            cnt[u][1] += cnt[v][1];
        }
    }

    private static int addEdge(int v) {
        if (reverse[v]) {
            reverse[v] = false;
            v = find(v);
            cnt[v][1]--;
            cnt[v][0]++;
        } else {
            reverse[v] = true;
            v = find(v);
            cnt[v][0]--;
            cnt[v][1]++;
        }
        return v;
    }

    public int[] solution(int[] nodes, int[][] edges) {
        int i;
        int len;
        int[] ans;
        HashMap<Integer,Integer> map;

        len = nodes.length;
        map = new HashMap<>((int) Math.ceil(len / 0.75));
        reverse = new boolean[len + 1];
        cnt = new int[len + 1][2];
        for (i = 1; i <= len; i++) {
            map.put(nodes[i - 1], i);
            if ((nodes[i - 1] & 1) == 0) {
                cnt[i][0]++;
            } else {
                reverse[i] = true;
                cnt[i][1]++;
            }
        }
        roots = new int[len + 1];
        for (int[] edge : edges) {
            union(addEdge(map.get(edge[0])), addEdge(map.get(edge[1])));
        }
        ans = new int[2];
        for (i = 1; i <= len; i++) {
            if (roots[i] <= 0) {
                if (cnt[i][0] == 1) {
                    ans[0]++;
                }
                if (cnt[i][1] == 1) {
                    ans[1]++;
                }
            }
        }
        return ans;
    }
}
