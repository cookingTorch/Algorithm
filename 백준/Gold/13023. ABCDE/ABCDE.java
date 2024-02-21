import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static final int ABCDE = 5;
	
	private static int n;
	private static boolean[] visited;
	private static ArrayList<ArrayList<Integer>> adj;
	
	private static boolean dfs(int curr, int depth) {
		if (visited[curr]) {
			return false;
		}
		if (depth == ABCDE) {
			return true;
		}
		visited[curr] = true;
		for (int next : adj.get(curr)) {
			if (dfs(next, depth + 1)) {
				visited[curr] = false;
				return true;
			}
		}
		visited[curr] = false;
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int m, a, b, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		adj = new ArrayList<>();
		for (i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		visited = new boolean[n];
		for (i = 0; i < n; i++) {
			if (dfs(i, 1)) {
				System.out.print('1');
				return;
			}
		}
		System.out.print('0');
	}
}
