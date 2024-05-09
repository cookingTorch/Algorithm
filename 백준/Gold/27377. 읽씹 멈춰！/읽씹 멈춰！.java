import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final long INF = Long.MAX_VALUE;
	
	private static BufferedReader br;
	
	private static final long solution() throws IOException {
		long n;
		long s;
		long t;
		long ans;
		StringTokenizer st;
		n = Long.parseLong(br.readLine());
		st = new StringTokenizer(br.readLine());
		s = Long.parseLong(st.nextToken());
		t = Long.parseLong(st.nextToken());
		for (ans = 0; n != 0;) {
			if ((n & 1) == 0) {
				n >>= 1;
				if (n > INF / s || t <= n * s) {
					ans += t;
				} else {
					ans += n * s;
				}
			} else {
				ans += s;
				n--;
			}
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		int t;
		int testCase;
		StringBuilder sb;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			sb.append(solution()).append('\n');
		}
		System.out.print(sb);
	}
}
