import java.util.Arrays;

class Solution {
    private static final int FAIL = -1;

    private int upperBound(int[][] arr, int right, int val) {
        int mid;
        int left;

        left = 0;
        while (left < right) {
            mid = left + right >>> 1;
            if (arr[mid][0] + arr[mid][1] > val) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    public int solution(int[][] scores) {
        int len;
        int peer;
        int work;
        int prev;
        int[] wanho;

        wanho = scores[0];
        len = scores.length;
        Arrays.sort(scores, 0, len, (o1, o2) -> o2[0] - o1[0]);
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
        }
        Arrays.sort(scores, 0, len, (o1, o2) -> o2[0] + o2[1] - o1[0] - o1[1]);
        return upperBound(scores, len, wanho[0] + wanho[1]) + 1;
    }
}
