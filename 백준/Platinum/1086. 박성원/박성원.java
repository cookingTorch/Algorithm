import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class Main {
	
	private static long gcd(long a, long b) {
		if (b == 0) {
			return a;
		}
		else {
			return gcd(b, a % b);
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		
		int n, i, j, k, l, length;
		int[] remainder, remainder10;
		long[][] dp;
		long p, q = 0, pqGcd;
		String tenString;
		String[] numString;
		BigInteger tenBig;
		BigInteger[] numBig;
		
		// 숫자 입력
		str = br.readLine();
		n = Integer.parseInt(str);
		numString = new String[n];
		for (i = 0; i < n; i++) {
			str = br.readLine();
			numString[i] = str;
		}
		str = br.readLine();
		k = Integer.parseInt(str);
		
		// 나머지 저장
		numBig = new BigInteger[n];
		remainder = new int[n];
		remainder10 = new int[n];
		for (i = 0; i < n; i++) {
			numBig[i] = new BigInteger(numString[i]);
			remainder[i] = numBig[i].remainder(BigInteger.valueOf(k)).intValue();
			length = numString[i].length();
			tenString = "1";
			for (j = 0; j < length; j++) {
				tenString += "0";
			}
			tenBig = new BigInteger(tenString);
			remainder10[i] = tenBig.remainder(BigInteger.valueOf(k)).intValue();
		}
		
		// DP
		dp = new long[1 << n][k];
		for (i = 0; i < (1 << n) - 1; i++) {
			for (j = 0; j < n; j++) {
				if (i == 0) {
					dp[1 << j][remainder[j]] = 1;
				}
				else if ((i >> j & 1) == 0) {
					for (l = 0; l < k; l++) {
						dp[i | (1 << j)][((l) * (remainder10[j]) + (remainder[j])) % k] += dp[i][l];
					}
				}
			}
		}
		
		// 약분
		p = dp[(1 << n) - 1][0];
		for (i = 0; i < k; i++) {
			q += dp[(1 << n) - 1][i];
		}
		if (p == 0) {
			q = 1;
		}
		else {
			pqGcd = gcd(p, q);
			p /= pqGcd;
			q /= pqGcd;
		}
		
		// 출력
		sb.append(p).append("/").append(q);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

}