import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int n;
	private static int[][] adj, ans;
	private static boolean[] visited;
	
	private static void dfs(int node, int start, boolean first) {
		int i;
		
		if (visited[node]) {
			return;
		}
		if (!first) {
			visited[node] = true;
			ans[start][node] = 1;
		}
		for (i = 0; i < n; i++) {
			if (adj[node][i] == 1) {
				dfs(i, start, false);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int i, j;
		
		n = Integer.parseInt(br.readLine());
		adj = new int[n][n];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < n; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[n];
		ans = new int[n][n];
		for (i = 0; i < n; i++) {
			Arrays.fill(visited, false);
			dfs(i, i, true);
		}
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				sb.append(ans[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}