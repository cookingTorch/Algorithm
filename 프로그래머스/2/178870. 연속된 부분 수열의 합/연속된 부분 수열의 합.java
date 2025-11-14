class Solution {
    public int[] solution(int[] sequence, int k) {
        int l;
        int r;
        int len;
        int sum;
        int min;
        int[] ans;

        sum = 0;
        len = sequence.length;
        min = len;
        ans = new int[2];
        for (l = 0, r = 0; r < len; r++) {
            sum += sequence[r];
            while (sum > k) {
                sum -= sequence[l++];
            }
            if (sum == k && r - l < min) {
                min = r - l;
                ans[0] = l;
                ans[1] = r;
            }
        }
        return ans;
    }
}
