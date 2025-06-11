class Solution {
    private static final int TRUE = 1;
    private static final int FALSE = 0;

    private boolean dfs(long num, int root) {
        if (root == 0) {
            return true;
        }
        if ((num >>> root & 1L) == 0L) {
            return (num & (1L << (root << 1 | 1)) - 1) == 0L;
        }
        return dfs(num, root >>> 1) && dfs(num >>> root + 1, root >>> 1);
    }

    public int[] solution(long[] numbers) {
        int i;
        int len;
        int[] ans;

        len = numbers.length;
        ans = new int[len];
        for (i = 0; i < len; i++) {
            ans[i] = dfs(numbers[i], Integer.highestOneBit(64 - Long.numberOfLeadingZeros(numbers[i])) - 1) ? TRUE : FALSE;
        }
        return ans;
    }
}
