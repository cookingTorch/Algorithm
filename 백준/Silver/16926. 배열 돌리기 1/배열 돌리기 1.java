import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int nextX(int x, int y, int n, int m) {
		if (y == 0 && x < n - 1) {
			return x + 1;
		} else if (y == m - 1 && x > 0) {
			return x - 1;
		}
		return x;
	}
	
	private static int nextY(int x, int y, int n, int m) {
		if (x == 0 && y > 0) {
			return y - 1;
		} else if (x == n - 1 && y < m - 1) {
			return y + 1;
		}
		return y;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, r, nn, nm, nr, size, start, x, y, nx, ny, tx, ty, i, j;
		int[][] arr, ans;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = new int[n][m];
		for (start = 0, nn = n, nm = m; nn > 0 && nm > 0; nn -= 2, nm -= 2, start++) {
			size = 2 * (nn + nm) - 4;
			nr = r % size;
			for (i = 0, x = 0, y = 0; i < size; i++) {
				tx = nextX(x, y, nn, nm);
				ty = nextY(x, y, nn, nm);
				x = tx;
				y = ty;
				for (j = 0, nx = x, ny = y; j < nr; j++) {
					tx = nextX(nx, ny, nn, nm);
					ty = nextY(nx, ny, nn, nm);
					nx = tx;
					ny = ty;
				}
				ans[start + nx][start + ny] = arr[start + x][start + y];
			}
		}
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				sb.append(ans[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}
