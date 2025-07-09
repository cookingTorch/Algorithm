import java.util.Arrays;

class Solution {
    private static final int INF = Integer.MAX_VALUE;

    private static final int X = 0;
    private static final int Y = 1;

    private static int[][] datas;

    private static int upperBound(int l, int r, int val, int xy) {
        int mid;

        while (l < r) {
            mid = l + r >>> 1;
            if (datas[mid][xy] <= val) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return r;
    }

    private static int count(int n, int[][] data) {
        int y;
        int i;
        int j;
        int ni;
        int nj;
        int low;
        int min;
        int cnt;

        cnt = 0;
        for (i = 0; i < n;) {
            ni = upperBound(i + 1, n, data[i][X], X);
            for (; i < ni; i++) {
                if ((y = data[i][Y]) == INF) {
                    continue;
                }
                min = INF;
                for (j = ni; j < n; j = nj) {
                    nj = upperBound(j + 1, n, data[j][X], X);
                    low = upperBound(j, nj, y, Y);
                    cnt += upperBound(j, nj, min, Y) - low;
                    if (low < nj) {
                        min = Math.min(min, data[low][Y]);
                    }
                }
            }
        }
        return cnt;
    }

    public int solution(int n, int[][] data) {
        int cnt;

        Arrays.sort(data, 0, n, (o1, o2) -> {
            if (o1[X] == o2[X]) {
                return o1[Y] - o2[Y];
            }
            return o1[X] - o2[X];
        });
        datas = data;
        cnt = count(n, data);
        for (int[] pos : data) {
            pos[X] = ~pos[X];
        }
        Arrays.sort(data, 0, n, (o1, o2) -> {
            if (o1[X] == o2[X]) {
                return o1[Y] - o2[Y];
            }
            return o1[X] - o2[X];
        });
        cnt += count(n, data);
        return cnt;
    }
}
