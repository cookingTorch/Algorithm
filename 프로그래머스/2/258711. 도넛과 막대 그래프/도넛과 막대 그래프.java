class Solution {
    private static final int SIZE = 1000001;
    
    public int[] solution(int[][] edges) {
        int u;
        int v;
        int idx;
        int max;
        int curr;
        int start;
        int[] next;
        int[] firsts;
        int[] result;
        int[] outDegree;
        boolean[] in;
        
        max = 0;
        in = new boolean[SIZE];
        outDegree = new int[SIZE];
        for (int[] edge : edges) {
            outDegree[edge[0]]++;
            in[edge[1]] = true;
            max = Math.max(max, Math.max(edge[0], edge[1]));
        }
        idx = 0;
        start = 0;
        next = new int[max + 1];
        firsts = null;
        result = new int[4];
        for (int[] edge : edges) {
            u = edge[0];
            v = edge[1];
            if (start == 0 && !in[u] && outDegree[u] > 1) {
                start = u;
                result[0] = start;
                firsts = new int[outDegree[start]];
            }
            if (u == start) {
                firsts[idx++] = v;
            }
            next[u] = v;
        }
        for (int first : firsts) {
            if (next[first] == 0) {
                result[2]++;
                continue;
            }
            if (outDegree[first] == 2) {
                result[3]++;
                continue;
            }
            for (curr = next[first];; curr = next[curr]) {
                if (curr == first) {
                    result[1]++;
                    break;
                }
                if (next[curr] == 0) {
                    result[2]++;
                    break;
                }
                if (outDegree[curr] == 2) {
                    result[3]++;
                    break;
                }
            }
        }
        return result;
    }
}