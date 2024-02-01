import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static int[] parent;
	private static ArrayList<ArrayList<Integer>> adj;
	
	private static void dfs(int node) {
		for (int child : adj.get(node)) {
			if (child == parent[node]) {
				continue;
			}
			parent[child] = node;
			dfs(child);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, node1, node2, i;
		
		n = Integer.parseInt(br.readLine());
		parent = new int[n + 1];
		adj = new ArrayList<>();
		for (i = 0; i <= n; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			node1 = Integer.parseInt(st.nextToken());
			node2 = Integer.parseInt(st.nextToken());
			adj.get(node1).add(node2);
			adj.get(node2).add(node1);
		}
		parent = new int[n + 1];
		dfs(1);
		for (i = 2; i <= n; i++) {
			sb.append(parent[i]).append('\n');
		}
		System.out.print(sb);
	}
}
