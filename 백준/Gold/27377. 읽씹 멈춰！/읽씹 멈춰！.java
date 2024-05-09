import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br;
	
	private static final BigInteger solution() throws IOException {
		BigInteger n;
		BigInteger s;
		BigInteger t;
		BigInteger ans;
		StringTokenizer st;
		n = new BigInteger(br.readLine());
		st = new StringTokenizer(br.readLine());
		s = new BigInteger(st.nextToken());
		t = new BigInteger(st.nextToken());
		for (ans = BigInteger.ZERO; n.compareTo(BigInteger.ZERO) != 0;) {
			if (n.mod(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0) {
				n = n.divide(BigInteger.TWO);
				if (t.compareTo(n.multiply(s)) <= 0) {
					ans = ans.add(t);
				} else {
					ans = ans.add(n.multiply(s));
				}
			} else {
				ans = ans.add(s);
				n = n.subtract(BigInteger.ONE);
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
