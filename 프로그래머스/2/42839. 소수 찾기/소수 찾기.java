class Solution {
    private static final int DIFF = '0';

    private static int cnt;
    private static int len;
    private static int last;
    private static int[] nums;
    private static boolean[] used;

    private static boolean isPrime(int n) {
        int i;

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

    private static void dfs(int cur, int depth) {
        int i;
        int num;
        int visited;

        cur *= 10;
        if (isPrime(cur + last)) {
            cnt++;
        }
        if (depth == len) {
            return;
        }
        visited = 0;
        for (i = 0; i < len; i++) {
            num = nums[i];
            if (!used[i] && (visited & 1 << num) == 0) {
                visited |= 1 << num;
                used[i] = true;
                dfs(cur + num, depth + 1);
                used[i] = false;
            }
        }
    }

    public int solution(String numbers) {
        int i;
        int j;
        int first;
        int visLast;
        int visFirst;

        len = numbers.length();
        nums = new int[len];
        for (i = 0; i < len; i++) {
            nums[i] = numbers.charAt(i) - DIFF;
        }
        visLast = 0;
        used = new boolean[len];
        for (i = 0; i < len; i++) {
            last = nums[i];
            if ((visLast & 1 << last) != 0) {
                continue;
            }
            visLast |= 1 << last;
            if ((last & 1) == 0) {
                continue;
            }
            used[i] = true;
            visFirst = 1;
            for (j = 0; j < len; j++) {
                first = nums[j];
                if (j == i || (visFirst & 1 << first) != 0) {
                    continue;
                }
                visFirst |= 1 << first;
                used[j] = true;
                dfs(first, 2);
                used[j] = false;
            }
            used[i] = false;
        }
        return cnt + Integer.bitCount(visLast & (1 << 2 | 1 << 3 | 1 << 5 | 1 << 7));
    }
}
