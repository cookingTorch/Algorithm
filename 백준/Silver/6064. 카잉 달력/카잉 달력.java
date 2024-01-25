import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static long solution(BufferedReader br, StringTokenizer st) throws IOException {
		long lcm, l, r;
		int m, n, x, y;
		
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		l = m;
		r = n;
		while (true) {
			if (l < r) {
				l += m;
			} else if (l > r) {
				r += n;
			} else {
				lcm = l;
				break;
			}
		}
		l = x;
		r = y;
		while (true) {
			if (l > lcm || r > lcm) {
				return -1;
			} else if (l < r) {
				l += m;
			} else if (l > r) {
				r += n;
			} else {
				return l;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			sb.append(solution(br, st)).append('\n');
		}
		System.out.println(sb);
	}
}