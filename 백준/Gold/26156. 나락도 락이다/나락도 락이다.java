import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final long NUM = 1000000007;
	private static final char R = 'R';
	private static final char O = 'O';
	private static final char C = 'C';
	private static final char K = 'K';
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n;
		int i;
		long r;
		long o;
		long c;
		long k;
		long pow;
		char[] str;
		
		n = Integer.parseInt(br.readLine());
		str = br.readLine().toCharArray();
		r = 0L;
		o = 0L;
		c = 0L;
		k = 0L;
		pow = 1L;
		for (i = 0; i < n; i++) {
			switch (str[i]) {
			case R:
				r = (r + pow) % NUM;
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
			pow = (pow << 1) % NUM;
		}
		System.out.print(k);
	}
}
