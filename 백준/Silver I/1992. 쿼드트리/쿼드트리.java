import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final StringBuilder ZERO = new StringBuilder().append('0');
	private static final StringBuilder ONE = new StringBuilder().append('1');
	
	private static char[][] video;
	
	private static StringBuilder dfs(int n, int x, int y) {
		StringBuilder a, b, c, d;
		
		if (n == 1) {
			if (video[x][y] == '0') {
				return ZERO;
			} else {
				return ONE;
			}
		}
		n /= 2;
		a = dfs(n, x, y);
		b = dfs(n, x, y + n);
		c = dfs(n, x + n, y);
		d = dfs(n, x + n, y + n);
		if (a == b && b == c && c == d) {
			return a;
		}
		return new StringBuilder().append('(').append(a).append(b).append(c).append(d).append(')');
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n, i;
		
		n = Integer.parseInt(br.readLine());
		video = new char[n][n];
		for (i = 0; i < n; i++) {
			video[i] = br.readLine().toCharArray();
		}
		System.out.print(dfs(n, 0, 0));
	}
}
