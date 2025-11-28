class Solution {
    private static final int DIFF = '0';

    private static int cnt;
    private static int len;
    private static int cur;
    private static int last;
    private static int[] nums;
    private static boolean[] used;
    private static boolean[] notPrime;

    private static void dfs(int depth) {
        int i;
        int num;
        int visited;

        if (depth > len) {
            return;
        }
        cur *= 10;
        visited = cur == 0 ? 1 : 0;
        for (i = 0; i < len; i++) {
            num = nums[i];
            if (!used[i] && (visited & 1 << num) == 0) {
                visited |= 1 << num;
                used[i] = true;
                cur += num;
                if (!notPrime[cur * 10 + last]) {
                    cnt++;
                }
                dfs(depth + 1);
                cur -= num;
                used[i] = false;
            }
        }
        cur /= 10;
    }

    public int solution(String numbers) {
        int i;
        int j;
        int max;
        int limit;
        int visited;

        len = numbers.length();
        nums = new int[len];
        for (i = 0; i < len; i++) {
            nums[i] = numbers.charAt(i) - DIFF;
        }
        max = 1;
        for (i = 0; i < len; i++) {
            max *= 10;
        }
        max -= 1;
        notPrime = new boolean[max + 1];
        notPrime[0] = true;
        notPrime[1] = true;
        limit = (int) Math.sqrt(max);
        for (i = 2; i <= limit; i++) {
            if (!notPrime[i]) {
                for (j = i * i; j <= max; j += i) {
                    notPrime[j] = true;
                }
            }
        }
        visited = 0;
        used = new boolean[len];
        for (i = 0; i < len; i++) {
            last = nums[i];
            if ((visited & 1 << last) == 0) {
                visited |= 1 << last;
                if ((last & 1) != 0) {
                    used[i] = true;
                    dfs(2);
                    used[i] = false;
                }
            }
        }
        cnt += Integer.bitCount(visited & (1 << 2 | 1 << 3 | 1 << 5 | 1 << 7));
        return cnt;
    }
}
