import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static final int NONE = 0;
	
	private static int[] team;
	private static ArrayList<ArrayList<Integer>> adj;
	
	private static boolean dfs(int node, int parent) {
		if (team[node] != NONE) {
			if (team[node] == parent) {
				return false;
			}
			return true;
		}
		team[node] = -parent;
		for (int child : adj.get(node)) {
			if (!dfs(child, team[node])) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, a, b, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		team = new int[n + 1];
		adj = new ArrayList<>(n + 1);
		for (i = 0; i <= n; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		for (i = 1; i <= n; i++) {
			if (team[i] == NONE && !dfs(i, 1)) {
				System.out.print(0);
				return;
			}
		}
		System.out.print(1);
	}
}
