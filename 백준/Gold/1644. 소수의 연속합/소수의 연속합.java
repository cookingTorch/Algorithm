import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n, idx, left, right, mid, cnt, i, j;
		boolean[] notPrime;
		ArrayList<Long> primes;
		
		n = Integer.parseInt(br.readLine());
		primes = new ArrayList<>(n >> 1);
		primes.add(0L);
		idx = 0;
		notPrime = new boolean[n + 1];
		for (i = 2; i <= n; i++) {
			if (!notPrime[i]) {
				primes.add(primes.get(idx++) + i);
				for (j = i + i; j <= n; j += i) {
					notPrime[j] = true;
				}
			}
		}
		cnt = 0;
		for (i = 0; i < primes.size() - 1; i++) {
			left = i + 1;
			right = primes.size();
			while (left < right) {
				mid = (left + right) >> 1;
				if (primes.get(mid) - primes.get(i) < n) {
					left = mid + 1;
				} else if (primes.get(mid) - primes.get(i) > n) {
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
