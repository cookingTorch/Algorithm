import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int SIZE = 283147;
	
	private static int n, idx;
	
	private static int[] getPrimes() {
		int prev, i, j;
		int[] primes;
		boolean[] notPrime;
		
		primes = new int[SIZE];
		primes[0] = 2;
		prev = 2;
		idx = 1;
		notPrime = new boolean[n + 1];
		for (i = 3; i <= n; i += 2) {
			if (notPrime[i]) {
				continue;
			}
			if (prev + i > n) {
				if ((n & 1) == 1 && !notPrime[n]) {
					primes[idx++] = n;
				}
				break;
			}
			primes[idx++] = prev = i;
			for (j = i << 1; j <= n; j += i) {
				notPrime[j] = true;
			}
		}
		return primes;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int left, right, sum, cnt;
		int[] primes;
		
		n = Integer.parseInt(br.readLine());
		primes = getPrimes();
		cnt = 0;
		sum = 0;
		for (left = 0, right = 0;; sum -= primes[left++]) {
			while (sum < n) {
				if (right == idx) {
					System.out.print(cnt);
					return;
				}
				sum += primes[right++];
			}
			if (sum == n) {
				cnt++;
			}
		}
	}
}
