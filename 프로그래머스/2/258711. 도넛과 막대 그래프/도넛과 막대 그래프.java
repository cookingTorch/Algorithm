class Solution {
    private static final int SIZE = 1_000_001;
    private static final int START = 0;
    private static final int DONUT = 1;
    private static final int STICK = 2;
    private static final int EIGHT = 3;

    public int[] solution(int[][] edges) {
        int i;
        int len;
        int[] edge;
        int[] result;
        int[] outDegree;
        boolean[] in;

        in = new boolean[SIZE];
        outDegree = new int[SIZE];
        len = edges.length;
        for (i = 0; i < len; i++) {
            edge = edges[i];
            outDegree[edge[0]]++;
            in[edge[1]] = true;
        }
        result = new int[4];
        for (i = 1; i < SIZE; i++) {
            if (outDegree[i] > 1) {
                if (in[i]) {
                    result[EIGHT]++;
                } else {
                    result[START] = i;
                }
            } else if (in[i] && outDegree[i] == 0) {
                result[STICK]++;
            }
        }
        result[DONUT] = outDegree[result[START]] - result[STICK] - result[EIGHT];
        return result;
    }
}
