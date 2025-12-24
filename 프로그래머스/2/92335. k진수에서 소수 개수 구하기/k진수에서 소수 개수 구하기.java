class Solution {
	private static boolean isPrime(long n) {
		long i;

		if (n == 2L || n == 3L) {
			return true;
		}
		if ((n & 1L) == 0L || n % 3L == 0L || n == 1L) {
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
		int idx;
		int cnt;
		long num;

		num = 0L;
		idx = 0;
		cnt = 0;
		while (n != 0) {
			r = n % k;
			if (r == 0) {
				if (isPrime(num)) {
					cnt++;
				}
				num = 0L;
				idx = 0;
			} else {
				num += r * (long) Math.pow(10, idx++);
			}
			n /= k;
		}
		if (isPrime(num)) {
			cnt++;
		}
		return cnt;
	}
}
