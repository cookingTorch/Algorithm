import java.util.Arrays;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int c;
        int i;
        int j;
        int len;
        int sum;
        int ans;
        int[] row;

        c = col - 1;
        Arrays.sort(data, 0, data.length, (o1, o2) -> {
            if (o1[c] == o2[c]) {
                return o2[0] - o1[0];
            }
            return o1[c] - o2[c];
        });
        ans = 0;
        len = data[0].length;
        for (i = row_begin; i <= row_end; i++) {
            sum = 0;
            row = data[i - 1];
            for (j = 0; j < len; j++) {
                sum += row[j] % i;
            }
            ans ^= sum;
        }
        return ans;
    }
}
