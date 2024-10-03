import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;

	private static int rain;
	private static int[] d;
	private static int[] map;
	private static boolean[] initialVisited;
	private static boolean[] visited;

	private static final void dfs(int pos) {
		int i;
		int npos;

		for (i = 0; i < 4; i++) {
			npos = pos + d[i];
			if (!visited[npos] && map[npos] > rain) {
				visited[npos] = true;
				dfs(npos);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int j;
		int num;
		int max;
		int col;
		int low;
		int pos;
		int high;
		int size;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		col = n + 2;
		size = col * col;
		low = INF;
		high = 0;
		map = new int[size];
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine(), " ", false);
			for (j = 1; j <= n; j++) {
				map[i * col + j] = Integer.parseInt(st.nextToken());
				low = Math.min(low, map[i * col + j]);
				high = Math.max(high, map[i * col + j]);
			}
		}
		initialVisited = new boolean[size];
		visited = new boolean[size];
		d = new int[] {-col, 1, col, -1};
		max = 1;
		high--;
		for (rain = low; rain < high; rain++) {
			num = 0;
			for (i = 1; i <= n; i++) {
				for (j = 1; j <= n; j++) {
					pos = i * col + j;
					if (!visited[pos] && map[pos] > rain) {
						num++;
						visited[pos] = true;
						dfs(pos);
					}
				}
			}
			max = Math.max(max, num);
			System.arraycopy(initialVisited, 0, visited, 0, size);
		}
		num = 0;
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= n; j++) {
				pos = i * col + j;
				if (!visited[pos] && map[pos] > rain) {
					num++;
					visited[pos] = true;
					dfs(pos);
				}
			}
		}
		max = Math.max(max, num);
		System.out.print(max);
	}
}
