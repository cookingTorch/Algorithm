import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n, idx, left, right, mid, cnt, i, j;
		long[] primes;
		boolean[] notPrime;
		
		n = Integer.parseInt(br.readLine());
		primes = new long[(n >> 1) + 2];
		primes[0] = 0L;
		idx = 0;
		notPrime = new boolean[n + 1];
		for (i = 2; i <= n; i++) {
			if (!notPrime[i]) {
				primes[++idx] = primes[idx - 1] + i;
				for (j = i + i; j <= n; j += i) {
					notPrime[j] = true;
				}
			}
		}
		cnt = 0;
		for (i = 0; i < idx; i++) {
			left = i + 1;
			right = idx + 1;
			while (left < right) {
				mid = (left + right) >> 1;
				if (primes[mid] - primes[i] < n) {
					left = mid + 1;
				} else if (primes[mid] - primes[i] > n) {
					right = mid;
				} else {
					cnt++;
					break;
				}
			}
		}
		System.out.print(cnt);
	}
}
