import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	private static final int THR = 20;
	private static final char SPACE = ' ';
	private static final char LINE_BREAK = '\n';
	private static final BigInteger ONE = BigInteger.valueOf(1L);
	private static final BigInteger TWO = BigInteger.valueOf(2L);

	private static StringBuilder sb;

	private static void printMove(int from, int to, int mid, int size) {
		if (size == 1) {
			sb.append(LINE_BREAK).append(from).append(SPACE).append(to);
			return;
		}
		printMove(from, mid, to, size - 1);
		printMove(from, to, mid, 1);
		printMove(mid, to, from, size - 1);
	}

	public static void main(String[] args) throws IOException {
		int n;
		int i;
		BigInteger dp;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		dp = BigInteger.valueOf(0L);
		for (i = 1; i <= n; i++) {
			dp = dp.multiply(TWO).add(ONE);
		}
		sb.append(dp);
		if (n <= THR) {
			printMove(1, 3, 2, n);
		}
		System.out.print(sb);
	}
}
