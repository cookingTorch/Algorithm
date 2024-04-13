import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	private static final int MIN = 3;
	private static final char STAR = '*';
	private static final char EMPTY = ' ';
	private static final char LINE_BREAK = '\n';
	
	private static char[][] ans;
	
	private static void draw(int x, int y, int size) {
		if (size == MIN) {
			ans[x][y] = STAR;
			ans[x + 1][y - 1] = STAR;
			ans[x + 1][y + 1] = STAR;
			ans[x + 2][y - 2] = STAR;
			ans[x + 2][y - 1] = STAR;
			ans[x + 2][y] = STAR;
			ans[x + 2][y + 1] = STAR;
			ans[x + 2][y + 2] = STAR;
			return;
		}
		size >>= 1;
		draw(x, y, size);
		draw(x + size, y - size, size);
		draw(x + size, y + size, size);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n, size, i;
		
		n = Integer.parseInt(br.readLine());
		size = 2 * n + 1;
		ans = new char[n][size];
		Arrays.fill(ans[0], EMPTY);
		ans[0][size - 1] = LINE_BREAK;
		for (i = 1; i < n; i++) {
			System.arraycopy(ans[0], 0, ans[i], 0, size);
		}
		draw(0, n - 1, n);
		for (i = 0; i < n; i++) {
			sb.append(ans[i]);
		}
		System.out.print(sb);
	}
}
