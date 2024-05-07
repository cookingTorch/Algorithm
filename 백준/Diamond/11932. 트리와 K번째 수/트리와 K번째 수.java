import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	private static final class Edge {
		int idx;
		Edge next;
		
		Edge(int idx, Edge next) {
			this.idx = idx;
			this.next = next;
		}
	}
	
	private static final class Node {
		int val;
		int start;
		int end;
		int mid;
		Node left;
		Node right;
		
		Node(int start, int end) {
			this.start = start;
			this.end = end;
			this.mid = start + end >> 1;
		}
		
		void build() {
			this.left = new Node(start, mid);
			this.right = new Node(mid + 1, end);
		}
		
		boolean isLeaf() {
			return start == end;
		}
	}
	
	private static int n;
	private static int logN;
	private static int[] arr;
	private static int[] depth;
	private static int[] unique;
	private static int[] parents;
	private static int[][] dp;
	private static Edge[] adj;
	private static Node[] trees;
	
	private static final void compression() {
		int i;
		HashMap<Integer, Integer> map;
		
		unique = new int[n];
		System.arraycopy(arr, 1, unique, 0, n);
		Arrays.sort(unique);
		map = new HashMap<>();
		for (i = 0; i < n; i++) {
			map.put(unique[i], i);
		}
		for (i = 1; i <= n; i++) {
			arr[i] = map.get(arr[i]);
		}
	}
	
	private static final void buildGraph(int curr, int parent) {
		Edge child;
		
		parents[curr] = parent;
		for (child = adj[curr]; child != null; child = child.next) {
			if (child.idx == parent) {
				continue;
			}
			buildGraph(child.idx, curr);
		}
	}
	
	private static final void dfs(int curr, int parent) {
		Edge child;
		
		dp[0][curr] = parent;
		depth[curr] = depth[parent] + 1;
		for (child = adj[curr]; child != null; child = child.next) {
			if (child.idx == parent) {
				continue;
			}
			dfs(child.idx, curr);
		}
	}
	
	private static final void init(Node curr) {
		if (curr.isLeaf()) {
			return;
		}
		curr.build();
		init(curr.left);
		init(curr.right);
	}
	
	private static final Node attach(Node parentNode, int num) {
		Node node;
		
		node = new Node(parentNode.start, parentNode.end);
		if (!parentNode.isLeaf()) {
			if (num <= parentNode.mid) {
				node.left = attach(parentNode.left, num);
				node.right = parentNode.right;
			} else {
				node.left = parentNode.left;
				node.right = attach(parentNode.right, num);
			}
		}
		node.val = parentNode.val + 1;
		return node;
	}
	
	private static final Node getTree(int idx) {
		if (trees[idx] != null) {
			return trees[idx];
		}
		trees[idx] = attach(getTree(parents[idx]), arr[idx]);
		return trees[idx];
	}
	
	private static final int lca(int u, int v) {
		int i;
		int temp;
		int diff;
		
		if (depth[u] < depth[v]) {
			temp = u;
			u = v;
			v = temp;
		}
		for (diff = depth[u] - depth[v], i = 0; diff != 0; diff >>= 1, i++) {
			if ((diff & 1) == 1) {
				u = dp[i][u];
			}
		}
		if (u == v) {
			return u;
		}
		for (i = logN; i >= 0; i--) {
			if (dp[i][u] != dp[i][v]) {
				u = dp[i][u];
				v = dp[i][v];
			}
		}
		return dp[0][u];
	}
	
	private static final int query(Node node, int num) {
		if (num < node.start) {
			return 0;
		}
		if (node.end <= num) {
			return node.val;
		}
		return query(node.left, num) + query(node.right, num);
	}
	
	private static final int query(int x, int y, int val) {
		int ancestor;
		
		ancestor = lca(x, y);
		return query(getTree(x), val) + query(getTree(y), val) - query(getTree(ancestor), val) - query(getTree(parents[ancestor]), val);
	}
	
	public static void main(String[] args) throws IOException {
		int m;
		int x;
		int y;
		int k;
		int i;
		int j;
		int mid;
		int left;
		int right;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		compression();
		adj = new Edge[n + 1];
		for (i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			adj[x] = new Edge(y, adj[x]);
			adj[y] = new Edge(x, adj[y]);
		}
		logN = (int) Math.ceil(Math.log(n) / Math.log(2));
		dp = new int[logN + 1][n + 1];
		depth = new int[n + 1];
		dfs(1, 0);
		for (i = 1; i <= logN; i++) {
			for (j = 1; j <= n; j++) {
				dp[i][j] = dp[i - 1][dp[i - 1][j]];
			}
		}
		parents = new int[n + 1];
		buildGraph(1, 0);
		trees = new Node[n + 1];
		trees[0] = new Node(0, n - 1);
		init(trees[0]);
		sb = new StringBuilder();
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			left = 0;
			right = n;
			while (left < right) {
				mid = left + right >> 1;
				if (query(x, y, mid) < k) {
					left = mid + 1;
				} else {
					right = mid;
				}
			}
			sb.append(unique[right]).append('\n');
		}
		System.out.print(sb);
	}
}
