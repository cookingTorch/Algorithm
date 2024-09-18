import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int FAIL = 0;
	private static final int MAX = 1_299_710;
	private static final int SIZE = 100_000;
	private static final char LINE_BREAK = '\n';

	private static int[] primes;
	private static boolean[] notPrime;
	private static BufferedReader br;

	private static void initPrimes() {
		int i;
		int j;
		int idx;

		notPrime = new boolean[MAX];
		primes = new int[SIZE];
		idx = 0;
		for (i = 2; i < MAX; i++) {
			if (notPrime[i]) {
				continue;
			}
			primes[idx++] = i;
			for (j = i << 1; j < MAX; j += i) {
				notPrime[j] = true;
			}
		}
	}

	private static int solution() throws IOException {
		int k;
		int left;
		int right;
		int mid;

		k = Integer.parseInt(br.readLine());
		if (!notPrime[k]) {
			return FAIL;
		}
		left = 0;
		right = SIZE;
		while (left < right) {
			mid = left + right >> 1;
			if (primes[mid] < k) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return primes[right] - primes[right - 1];
	}

	public static void main(String[] args) throws IOException {
		int t;
		StringBuilder sb;

		initPrimes();
		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while (t-- > 0) {
			sb.append(solution()).append(LINE_BREAK);
		}
		System.out.print(sb.toString());
	}
}
