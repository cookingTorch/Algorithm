import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE >> 1;
	private static final char SPACE = ' ';

	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int u;
		int v;
		int w;
		int k;
		int i;
		int max;
		int min;
		int size;
		int[] cities;
		int[][] map;
		ArrayList<Integer> ans;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n + 1][n + 1];
		for (v = 1; v <= n; v++) {
			map[1][v] = INF;
		}
		for (v = 2; v <= n; v++) {
			System.arraycopy(map[1], 1, map[v], 1, n);
			map[v][v] = 0;
		}
		map[1][1] = 0;
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine(), " ", false);
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		for (w = 1; w <= n; w++) {
			for (u = 1; u <= n; u++) {
				for (v = 1; v <= n; v++) {
					map[u][v] = Math.min(map[u][v], map[u][w] + map[w][v]);
				}
			}
		}
		k = Integer.parseInt(br.readLine());
		cities = new int[k];
		st = new StringTokenizer(br.readLine(), " ", false);
		for (i = 0; i < k; i++) {
			cities[i] = Integer.parseInt(st.nextToken());
		}
		ans = new ArrayList<>();
		min = INF;
		for (w = 1; w <= n; w++) {
			max = 0;
			for (i = 0; i < k; i++) {
				max = Math.max(max, map[cities[i]][w] + map[w][cities[i]]);
			}
			if (max < min) {
				ans.clear();
				ans.add(w);
				min = max;
			} else if (max == min) {
				ans.add(w);
			}
		}
		sb = new StringBuilder();
		size = ans.size();
		for (i = 0; i < size; i++) {
			sb.append(ans.get(i)).append(SPACE);
		}
		System.out.print(sb.toString());
	}
}
