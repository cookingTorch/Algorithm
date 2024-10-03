import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final char IS_FRIEND = 'Y';

	public static void main(String[] args) throws IOException {
		int n;
		int u;
		int v;
		int k;
		int i;
		int max;
		int size;
		int[] cnt;
		char[][] map;
		boolean[][] visited;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		size = n + 1;
		map = new char[n][size];
		for (i = 0; i < n; i++) {
			br.read(map[i], 0, size);
		}
		max = 0;
		cnt = new int[n];
		visited = new boolean[n][n];
		for (k = 0; k < n; k++) {
			for (u = 0; u < n; u++) {
				for (v = 0; v < u; v++) {
					if ((map[u][v] == IS_FRIEND || (u != k && v != k && map[u][k] == IS_FRIEND && map[k][v] == IS_FRIEND)) && !visited[u][v]) {
						visited[u][v] = true;
						max = Math.max(max, Math.max(++cnt[u], ++cnt[v]));
					}
				}
			}
		}
		System.out.print(max);
	}
}
