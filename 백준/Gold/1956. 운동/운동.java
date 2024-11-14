import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE >> 1;
	private static final char[] FAIL = {'-', '1'};

	public static void main(String[] args) throws IOException {
		int v;
		int e;
		int i;
		int j;
		int k;
		int min;
		int[][] dist;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		dist = new int[v + 1][v + 1];
		for (i = 1; i <= v; i++) {
			dist[1][i] = INF;
		}
		for (i = 2; i <= v; i++) {
			System.arraycopy(dist[1], 1, dist[i], 1, v);
		}
		while (e-- > 0) {
			st = new StringTokenizer(br.readLine(), " ", false);
			dist[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		for (k = 1; k <= v; k++) {
			for (i = 1; i <= v; i++) {
				for (j = 1; j <= v; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		min = INF;
		for (i = 1; i <= v; i++) {
			for (j = 1; j <= v; j++) {
				if (i == j) {
					continue;
				}
				min = Math.min(min, dist[i][j] + dist[j][i]);
			}
		}
		if (min == INF) {
			System.out.print(FAIL);
		} else {
			System.out.print(min);
		}
	}
}
