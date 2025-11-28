class Solution {
    private static final int DIFF = '0';

    private static int cnt;
    private static int len;
    private static int cur;
    private static int last;
    private static int[] nums;
    private static boolean[] used;

    private static boolean isPrime(int n) {
        int i;

        if (n == 3) {
            return true;
        }
        if (n % 3 == 0) {
            return false;
        }
        for (i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

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
                if (isPrime(cur * 10 + last)) {
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
        int visited;

        len = numbers.length();
        nums = new int[len];
        for (i = 0; i < len; i++) {
            nums[i] = numbers.charAt(i) - DIFF;
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
