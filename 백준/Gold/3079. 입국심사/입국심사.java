import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final long INF = 1_000_000_000_000_000_001L;
	
	public static void main(String[] args) throws IOException {
		int n;
		int i;
		long m;
		long mid;
		long left;
		long right;
		long people;
		long[] t;
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
		left = 1;
		right = INF;
		while (left < right) {
			mid = left + right >> 1;
			people = m;
			for (i = 0; i < n && people > 0; i++) {
				people -= mid / t[i];
			}
			if (people > 0) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		System.out.print(right);
	}
}
