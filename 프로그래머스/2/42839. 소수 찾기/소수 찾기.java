class Solution {
    private static final int DIFF = '0';
    private static final int RADIX = 10;

    private static int ans;
    private static int len;
    private static int size;
    private static int last;
    private static int[] nums;
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
        for (i = 0; i < size; i++) {
            if (cnts[i] != 0) {
                cnts[i]--;
                dfs(num + nums[i], depth);
                cnts[i]++;
            }
        }
    }

    public int solution(String numbers) {
        int i;
        int j;

        cnts = new int[RADIX];
        len = numbers.length();
        for (i = 0; i < len; i++) {
            cnts[numbers.charAt(i) - DIFF]++;
        }
        ans = 0;
        size = 0;
        nums = new int[len];
        for (i = 0; i < RADIX; i++) {
            if (cnts[i] != 0) {
                nums[size] = i;
                cnts[size++] = cnts[i];
                if (i == 2 || i == 3 || i == 5 || i == 7) {
                    ans++;
                }
            }
        }
        for (i = nums[0] == 0 ? 1 : 0; i < size; i++) {
            if (cnts[i] != 0) {
                cnts[i]--;
                for (j = 0; j < size; j++) {
                    if ((nums[j] & 1) != 0 && cnts[j] != 0) {
                        cnts[j]--;
                        last = nums[j];
                        dfs(nums[i], 2);
                        cnts[j]++;
                    }
                }
                cnts[i]++;
            }
        }
        return ans;
    }
}
