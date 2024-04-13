import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int MIN = 3;
	private static final byte STAR = '*';
	private static final byte EMPTY = ' ';
	private static final byte LINE_BREAK = '\n';
	
	private static int size;
	private static byte[] ans;
	
	private static void draw(int x, int y, int scale) {
		if (scale == MIN) {
			ans[x * size + y] = STAR;
			ans[(x + 1) * size + y - 1] = STAR;
			ans[(x + 1) * size + y + 1] = STAR;
			ans[(x + 2) * size + y - 2] = STAR;
			ans[(x + 2) * size + y - 1] = STAR;
			ans[(x + 2) * size + y] = STAR;
			ans[(x + 2) * size + y + 1] = STAR;
			ans[(x + 2) * size + y + 2] = STAR;
			return;
		}
		scale >>= 1;
		draw(x, y, scale);
		draw(x + scale, y - scale, scale);
		draw(x + scale, y + scale, scale);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n, i;
		
		n = Integer.parseInt(br.readLine());
		size = 2 * n + 1;
		ans = new byte[n * size];
		for (i = 0; i < size; i++) {
			ans[i] = EMPTY;
		}
		ans[size - 1] = LINE_BREAK;
		for (i = 1; i < n; i++) {
			System.arraycopy(ans, 0, ans, i * size, size);
		}
		draw(0, n - 1, n);
		System.out.write(ans);
	}
}
