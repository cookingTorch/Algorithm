import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final long NUM = 1000000007;
	private static final char R = 'R';
	private static final char O = 'O';
	private static final char C = 'C';
	private static final char K = 'K';
	
	private static long[] table;
	
	private static final long pow(int num) {
		if (table[num] != 0) {
			return table[num];
		}
		if (num == 0) {
			return table[num] = 1;
		}
		if ((num & 1) == 0) {
			return table[num] = pow(num >> 1) * pow(num >> 1) % NUM;
		}
		return table[num] = 2 * pow(num - 1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n;
		int i;
		long r;
		long o;
		long c;
		long k;
		char[] str;
		
		n = Integer.parseInt(br.readLine());
		str = br.readLine().toCharArray();
		table = new long[n];
		r = 0L;
		o = 0L;
		c = 0L;
		k = 0L;
		for (i = 0; i < n; i++) {
			switch (str[i]) {
			case R:
				r = (r + (long) pow(i)) % NUM;
				break;
			case O:
				o = (r + o) % NUM;
				break;
			case C:
				c = (c + o) % NUM;
				break;
			case K:
				k = (k + c) % NUM;
			}
		}
		System.out.print(k);
	}
}
