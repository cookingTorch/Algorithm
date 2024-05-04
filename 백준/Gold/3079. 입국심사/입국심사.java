import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final Long MAX = 1_000_000_000L;
	
	private static int n;
	private static long m;
	private static long[] t;
	
	private static final boolean validate(long time) {
		int i;
		long people;
		
		people = m;
		for (i = 0; i < n; i++) {
			people -= time / t[i];
			if (people <= 0) {
				return true;
			}
		}
		return false;
	}
	
	private static final long lowerBound() {
		long mid;
		long left;
		long right;
		
		left = 1;
		right = ((m - 1) / n + 1) * MAX;
		while (left < right) {
			mid = left + right >> 1;
			if (validate(mid)) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return right;
	}
	
	public static void main(String[] args) throws IOException {
		int i;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Long.parseLong(st.nextToken());
		t = new long[n];
		for (i = 0; i < n; i++) {
			t[i] = Long.parseLong(br.readLine());
		}
		System.out.print(lowerBound());
	}
}
