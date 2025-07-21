class Solution {
    private static final int INF = Integer.MAX_VALUE;

    private static int size;
    
    private static void rotate(int[] arr, int i, int rotate) {
        if (i > 0) {
            arr[i - 1] = arr[i - 1] + rotate & 3;
        }
        arr[i] = arr[i] + rotate & 3;
        if (i < size - 1) {
            arr[i + 1] = arr[i + 1] + rotate & 3;
        }
    }

    public int solution(int[][] clockHands) {
        int i;
        int j;
        int k;
        int thr;
        int min;
        int cnt;
        int move;
        int[] tmp;
        int[] cur;
        int[] prev;
        int[] next;

        min = INF;
        size = clockHands.length;
        prev = new int[size];
        cur = new int[size];
        next = new int[size];
        thr = 1 << (size << 1);
        for (i = 0; i < thr; i++) {
            System.arraycopy(clockHands[0], 0, prev, 0, size);
            System.arraycopy(clockHands[1], 0, cur, 0, size);
            move = 0;
            for (j = 0; j < size; j++) {
                cnt = (i >> (j << 1)) & 3;
                move += cnt;
                rotate(prev, j, cnt);
                cur[j] = cur[j] + cnt & 3;
            }
            for (j = 2; j < size; j++) {
                System.arraycopy(clockHands[j], 0, next, 0, size);
                for (k = 0; k < size; k++) {
                    cnt = 4 - prev[k] & 3;
                    move += cnt;
                    rotate(cur, k, cnt);
                    next[k] = next[k] + cnt & 3;
                }
                tmp = prev;
                prev = cur;
                cur = next;
                next = tmp;
            }
            for (j = 0; j < size; j++) {
                cnt = 4 - prev[j] & 3;
                move += cnt;
                rotate(cur, j, cnt);
                if (j > 0 && cur[j - 1] != 0) {
                    break;
                }
            }
            if (j == size && cur[size - 1] == 0) {
                min = Math.min(min, move);
            }
        }
        return min;
    }
}
