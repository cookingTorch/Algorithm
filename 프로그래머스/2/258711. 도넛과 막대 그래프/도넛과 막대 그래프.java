class Solution {
    private static final int SIZE = 1000001;
    
    public int[] solution(int[][] edges) {
        int i;
        int max;
        int[] result;
        int[] outDegree;
        boolean[] in;
        
        max = 0;
        in = new boolean[SIZE];
        outDegree = new int[SIZE];
        for (int[] edge : edges) {
            outDegree[edge[0]]++;
            in[edge[1]] = true;
        }
        result = new int[4];
        for (i = 1; i < SIZE; i++) {
            if (outDegree[i] > 1) {
                if (in[i]) {
                    result[3]++;
                } else {
                    result[0] = i;
                }
            } else if (in[i] && outDegree[i] == 0) {
                result[2]++;
            }
        }
        result[1] = outDegree[result[0]] - result[2] - result[3];
        return result;
    }
}