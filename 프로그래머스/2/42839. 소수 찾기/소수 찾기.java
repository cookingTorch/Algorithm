class Solution {
    private static final int DIFF = '0';
    private static final int RADIX = 10;

    private static int ans;
    private static int len;
    private static int last;
    private static int[] cnts;

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

    private static void dfs(int num, int depth) {
        int i;

        num *= RADIX;
        if (isPrime(num + last)) {
            ans++;
        }
        if (depth++ == len) {
            return;
        }
        for (i = 0; i < RADIX; i++) {
            if (cnts[i] != 0) {
                cnts[i]--;
                dfs(num + i, depth);
                cnts[i]++;
            }
        }
    }

    public int solution(String numbers) {
        int i;
        int j;

        ans = 0;
        cnts = new int[RADIX];
        len = numbers.length();
        for (i = 0; i < len; i++) {
            if (cnts[j = numbers.charAt(i) - DIFF]++ == 0) {
                if (j == 2 || j == 3 || j == 5 || j == 7) {
                    ans++;
                }
            }
        }
        for (i = 1; i < RADIX; i++) {
            if (cnts[i] != 0) {
                cnts[i]--;
                for (j = 1; j < RADIX; j += 2) {
                    if (cnts[j] != 0) {
                        cnts[j]--;
                        last = j;
                        dfs(i, 2);
                        cnts[j]++;
                    }
                }
                cnts[i]++;
            }
        }
        return ans;
    }
}
