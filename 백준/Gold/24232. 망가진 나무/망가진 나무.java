import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int MIN = Integer.MIN_VALUE >> 1;
	private static final int DIFF = '0';
	
	private static final class Edge {
		int to;
		int dir;
		int idx;
		Edge next;
		
		Edge(int to, Edge next, int idx, int dir) {
			this.to = to;
			this.next = next;
			this.idx = idx;
			this.dir = dir;
		}
	}
	
	private static int[] dp;
	private static int[] roots;
	private static char[] ans;
	private static Edge[] adj;
	
	private static final int in(int curr, int parent, int dir) {
		int val;
		Edge edge;
		
		if (dp[curr] != -1) {
			return dp[curr];
		}
		val = dir;
		for (edge = adj[curr]; edge != null; edge = edge.next) {
			if (edge.to == parent) {
				continue;
			}
			val += in(edge.to, curr, edge.dir);
		}
		return dp[curr] = val;
	}
	
	private static final int out(int curr, int parent, int dir) {
		int ret;
		int val;
		int diff;
		int root;
		int outVal;
		int maxDiff;
		Edge edge;
		
		val = in(curr, parent, dir) - dir + (dir ^ 1);
		ret = -1;
		maxDiff = MIN;
		for (edge = adj[curr]; edge != null; edge = edge.next) {
			if (edge.to == parent) {
				continue;
			}
			outVal = out(edge.to, curr, edge.dir);
			root = roots[edge.to];
			diff = dp[edge.to] - outVal;
			if (diff > maxDiff) {
				ret = root;
				maxDiff = diff;
			}
		}
		if (val < val - maxDiff) {
			roots[curr] = curr;
			return val;
		}
		roots[curr] = ret;
		return val - maxDiff;
	}
	
	private static final void dfs(int curr, int parent) {
		Edge edge;
		
		for (edge = adj[curr]; edge != null; edge = edge.next) {
			if (edge.to == parent) {
				continue;
			}
			ans[edge.idx] = (char) (edge.dir + DIFF);
			dfs(edge.to, curr);
		}
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int u;
		int v;
		int i;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		adj = new Edge[n + 1];
		for (i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			adj[u] = new Edge(v, adj[u], i, 0);
			adj[v] = new Edge(u, adj[v], i, 1);
		}
		dp = new int[n + 1];
		for (i = 1; i <= n; i++) {
			dp[i] = -1;
		}
		roots = new int[n + 1];
		out(1, -1, 1);
		ans = new char[n - 1];
		dfs(roots[1], -1);
		System.out.print(ans);
	}
}
