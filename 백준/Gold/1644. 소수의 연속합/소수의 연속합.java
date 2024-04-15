import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int SIZE = 283147;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n, idx, left, right, sum, cnt, prev, i, j;
		int[] primes;
		boolean[] notPrime;
		
		n = Integer.parseInt(br.readLine());
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
			primes[idx++] = i;
			for (j = i << 1; j <= n; j += i) {
				notPrime[j] = true;
			}
			prev = i;
		}
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
