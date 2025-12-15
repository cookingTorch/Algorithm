class Solution {
    private static int max;
    private static int len;
    private static int[][] arr;
    private static boolean[] visited;
    
    private static void dfs(int k, int depth) {
        int i;

        if (depth > max) {
            max = depth;
        }
        depth++;
        for (i = 0; i < len; i++) {
            if (!visited[i] && k >= arr[i][0]) {
                visited[i] = true;
                dfs(k - arr[i][1], depth);
                visited[i] = false;
            }
        }
    }
    
    public int solution(int k, int[][] dungeons) {
        arr = dungeons;
        len = dungeons.length;
        visited = new boolean[len];
        max = 0;
        dfs(k, 0);
        return max;
    }
}
