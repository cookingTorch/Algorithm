class Solution {
    public int solution(int[] elements) {
        int i;
        int j;
        int len;
        int sum;
        int cnt;
        int cur;
        boolean[] visited;

        len = elements.length;
        sum = 0;
        for (i = 0; i < len; i++) {
            sum += elements[i];
        }
        visited = new boolean[sum];
        cnt = 1;
        for (i = 1; i < len; i++) {
            cur = 0;
            for (j = i; j < len; j++) {
                cur += elements[j];
                if (!visited[cur]) {
                    cnt++;
                    visited[cur] = true;
                }
                if (!visited[sum - cur]) {
                    cnt++;
                    visited[sum - cur] = true;
                }
            }
        }
        return cnt;
    }
}
