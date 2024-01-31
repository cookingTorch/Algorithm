import java.util.Arrays;

class Solution {
    int n, max, temp, r;
    int[] dices1, dices2, result1, result2, cnt1, cnt2, ans;
    int[][] nums;

    private void pi(int[] dices, int[] result, int[] cnt, int d) {
        int sum, i;

        if (d == r) {
            sum = 0;
            for (i = 0; i < r; i++) {
                sum += nums[dices[i]][result[i]];
            }
            cnt[sum]++;
        } else {
            for (i = 0; i < 6; i++) {
                result[d] = i;
                pi(dices, result, cnt, d + 1);
            }
        }
    }

    private void combi(int start, int d) {
        int i, j, i1, i2;

        if (d == r) {
            i1 = 0;
            i2 = 0;
            for (i = 0; i < n; i++) {
                if (i1 >= r || dices1[i1] != i) {
                    dices2[i2++] = i;
                } else {
                    i1++;
                }
            }
            cnt1 = new int[501];
            cnt2 = new int[501];
            pi(dices1, result1, cnt1, 0);
            pi(dices2, result2, cnt2, 0);
            temp = 0;
            for (i = 1; i <= 500; i++) {
                if (cnt1[i] > 0) {
                    for (j = 1; j < i; j++) {
                        temp += cnt1[i] * cnt2[j];
                    }
                }
            }
            if (temp > max) {
                ans = Arrays.copyOf(dices1, r);
                for (i = 0; i < r; i++) {
                    ans[i]++;
                }
                max = temp;
            }
        } else {
            for (i = start; i < n; i++) {
                dices1[d] = i;
                combi(i + 1, d + 1);
            }
        }
    }

    public int[] solution(int[][] dice) {
        nums = dice;
        n = nums.length;
        r = n / 2;
        dices1 = new int[r];
        dices2 = new int[r];
        result1 = new int[r];
        result2 = new int[r];
        max = -1;
        combi(0, 0);
        return ans;
    }
}