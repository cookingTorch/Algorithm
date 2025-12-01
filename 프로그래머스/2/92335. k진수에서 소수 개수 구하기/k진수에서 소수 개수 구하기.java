class Solution {
    private static boolean isPrime(long n) {
        long i;

        if (n == 3L) {
            return true;
        }
        if (n < 2L || n % 3L == 0L) {
            return false;
        }
        for (i = 5L; i * i <= n; i += 6L) {
            if (n % i == 0L || n % (i + 2L) == 0L) {
                return false;
            }
        }
        return true;
    }

    public int solution(int n, int k) {
        int r;
        int pow;
        int cnt;
        long num;

        cnt = 0;
        pow = 0;
        num = 0L;
        for (; n != 0;) {
            r = n % k;
            if (r == 0) {
                if (isPrime(num)) {
                    cnt++;
                }
                pow = 0;
                num = 0L;
            } else {
                num += r * (long) Math.pow(10, pow++);
            }
            n /= k;
        }
        if (isPrime(num)) {
            cnt++;
        }
        return cnt;
    }
}
