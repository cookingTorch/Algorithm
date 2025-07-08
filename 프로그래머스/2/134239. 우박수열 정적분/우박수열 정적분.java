import java.util.ArrayList;

class Solution {
    private static final double FAIL = -1.0;

    public double[] solution(int k, int[][] ranges) {
        int n;
        int l;
        int r;
        int i;
        int nk;
        int len;
        int sum;
        double[] ans;
        ArrayList<Integer> list;

        list = new ArrayList<>();
        list.add(sum = 0);
        while (k != 1) {
            if ((k & 1) == 0) {
                nk = k >>> 1;
            } else {
                nk = k * 3 + 1;
            }
            list.add(sum += nk + k);
            k = nk;
        }
        n = list.size() - 1;
        len = ranges.length;
        ans = new double[len];
        for (i = 0; i < len; i++) {
            l = ranges[i][0];
            r = n + ranges[i][1];
            if (l > r) {
                ans[i] = FAIL;
            } else {
                ans[i] = (list.get(r) - list.get(l)) / 2.0;
            }
        }
        return ans;
    }
}
