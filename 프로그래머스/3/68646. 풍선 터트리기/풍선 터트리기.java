class Solution {
    private static final int INF = Integer.MAX_VALUE;

    public int solution(int[] a) {
        int i;
        int len;
        int min;
        int cnt;
        boolean[] visited;

        len = a.length;
        visited = new boolean[len];
        cnt = 0;
        min = INF;
        for (i = 0; i < len; i++) {
            if (a[i] < min) {
                min = a[i];
                visited[i] = true;
                cnt++;
            }
        }
        min = INF;
        for (i = len - 1; i >= 0; i--) {
            if (a[i] < min) {
                min = a[i];
                if (!visited[i]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
