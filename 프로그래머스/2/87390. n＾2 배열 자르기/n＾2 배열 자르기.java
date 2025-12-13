class Solution {
    public int[] solution(int n, long left, long right) {
		int idx;
        int[] ans;
        long i;
        long size;

        size = n;
        ans = new int[(int) (right - left) + 1];
        for (i = left, idx = 0; i <= right; i++, idx++) {
            ans[idx] = Math.max((int) (i / size), (int) (i % size)) + 1;
        }
        return ans;
    }
}
