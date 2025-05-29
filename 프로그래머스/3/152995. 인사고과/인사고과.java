import java.util.Arrays;

class Solution {
    private static final int FAIL = -1;

    public int solution(int[][] scores) {
        int len;
        int ans;
        int sum;
        int peer;
        int work;
        int prev;
        int[] wanho;

        wanho = scores[0];
        sum = wanho[0] + wanho[1];
        len = scores.length;
        Arrays.sort(scores, 0, len, (o1, o2) -> o2[0] - o1[0]);
        ans = 1;
        prev = 0;
        peer = 0;
        work = 0;
        for (int[] score : scores) {
            if (score[0] != peer) {
                peer = score[0];
                prev = work;
            }
            if (score[1] < prev) {
                if (score == wanho) {
                    return FAIL;
                }
                score[0] = 0;
                score[1] = FAIL;
            } else {
                work = Math.max(work, score[1]);
            }
            if (score[0] + score[1] > sum) {
                ans++;
            }
        }
        return ans;
    }
}
