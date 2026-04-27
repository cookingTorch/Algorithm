import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final class Node {
		int idx;
		Node next;
		
		Node(int idx, Node next) {
			this.idx = idx;
			this.next = next;
		}
	}
	
	private static int n;
	private static int[] match;
	private static int[] parent;
	private static int[] base;
	private static int[] queue;
	private static boolean[] used;
	private static boolean[] blossom;
	private static boolean[] lcaCheck;
	private static Node[] adj;
	
	private static final int lca(int a, int b) {
		int i;
		
		for (i = 1; i <= n; i++) {
			lcaCheck[i] = false;
		}
		while (true) {
			a = base[a];
			lcaCheck[a] = true;
			if (match[a] == 0) {
				break;
			}
			a = parent[match[a]];
		}
		while (true) {
			b = base[b];
			if (lcaCheck[b]) {
				return b;
			}
			b = parent[match[b]];
		}
	}
	
	private static final void markPath(int v, int b, int child) {
		while (base[v] != b) {
			blossom[base[v]] = true;
			blossom[base[match[v]]] = true;
			parent[v] = child;
			child = match[v];
			v = parent[match[v]];
		}
	}
	
	private static final boolean findPath(int root) {
		int i;
		int v;
		int u;
		int head;
		int tail;
		int currBase;
		Node node;
		
		for (i = 1; i <= n; i++) {
			used[i] = false;
			parent[i] = 0;
			base[i] = i;
		}
		head = 0;
		tail = 0;
		queue[tail++] = root;
		used[root] = true;
		while (head < tail) {
			v = queue[head++];
			for (node = adj[v]; node != null; node = node.next) {
				u = node.idx;
				if (base[v] == base[u] || match[v] == u) {
					continue;
				}
				if (u == root || match[u] != 0 && parent[match[u]] != 0) {
					currBase = lca(v, u);
					for (i = 1; i <= n; i++) {
						blossom[i] = false;
					}
					markPath(v, currBase, u);
					markPath(u, currBase, v);
					for (i = 1; i <= n; i++) {
						if (!blossom[base[i]]) {
							continue;
						}
						base[i] = currBase;
						if (!used[i]) {
							used[i] = true;
							queue[tail++] = i;
						}
					}
				} else if (parent[u] == 0) {
					parent[u] = v;
					if (match[u] == 0) {
						while (u != 0) {
							v = parent[u];
							i = match[v];
							match[u] = v;
							match[v] = u;
							u = i;
						}
						return true;
					}
					used[match[u]] = true;
					queue[tail++] = match[u];
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		int m;
		int u;
		int v;
		int i;
		int ans;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		adj = new Node[n + 1];
		match = new int[n + 1];
		parent = new int[n + 1];
		base = new int[n + 1];
		queue = new int[n + 1];
		used = new boolean[n + 1];
		blossom = new boolean[n + 1];
		lcaCheck = new boolean[n + 1];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			adj[u] = new Node(v, adj[u]);
			adj[v] = new Node(u, adj[v]);
		}
		ans = 0;
		for (i = 1; i <= n; i++) {
			if (match[i] == 0 && findPath(i)) {
				ans++;
			}
		}
		System.out.print(ans);
	}
}