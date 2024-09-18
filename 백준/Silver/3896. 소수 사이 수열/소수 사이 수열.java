import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	private static final int FAIL = 0;
	private static final int SIZE = 1_299_710;
	private static final char LINE_BREAK = '\n';

	private static int size;
	private static boolean[] notPrime;
	private static ArrayList<Integer> primes;
	private static BufferedReader br;

	private static void initPrimes() {
		int i;
		int j;

		notPrime = new boolean[SIZE];
		primes = new ArrayList<>();
		for (i = 2; i < SIZE; i++) {
			if (notPrime[i]) {
				continue;
			}
			primes.add(i);
			for (j = i << 1; j < SIZE; j += i) {
				notPrime[j] = true;
			}
		}
		size = primes.size();
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
		right = size;
		while (left < right) {
			mid = left + right >> 1;
			if (primes.get(mid) < k) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return primes.get(right) - primes.get(right - 1);
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
