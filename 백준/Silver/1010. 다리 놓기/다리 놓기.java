import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	private static int MAX = 31;
	
	private static BigInteger[] facto;
	
	private static BigInteger solution(BufferedReader br, StringTokenizer st) throws IOException {
		int n, m, r;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Math.min(n, m);
		n = Math.max(n, m);
		return facto[n].divide(facto[n - r]).divide(facto[r]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase, i;
		
		facto = new BigInteger[MAX];
		facto[0] = new BigInteger("1");
		for (i = 1; i < MAX; i++) {
			facto[i] = facto[i - 1].multiply(new BigInteger(Integer.toString(i)));
		}
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			sb.append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}