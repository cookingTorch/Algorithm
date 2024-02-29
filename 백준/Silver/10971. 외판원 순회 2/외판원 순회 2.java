import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	
	private static int n, min;
	private static int[][] adj;
	private static boolean[] visited;
	
	private static void dfs(int node, int dist, int depth) {
		int i;
		
		if (dist >= min) {
			return;
		}
		if (depth == n) {
			if (adj[node][0] != 0) {
				min = Math.min(min, dist + adj[node][0]);
			}
			return;
		}
		visited[node] = true;
		for (i = 1; i < n; i++) {
			if (!visited[i] && adj[node][i] != 0) {
				visited[i] = true;
				dfs(i, dist + adj[node][i], depth + 1);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
		min = INF;
		visited = new boolean[n];
		dfs(0, 0, 1);
		System.out.print(min);
	}
}
