import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE >> 1;
	
	private static final class Node {
		int idx;
		int weight;
		Node next;
		
		Node(int idx, int weight, Node next) {
			this.idx = idx;
			this.weight = weight;
			this.next = next;
		}
	}
	
	private static int k;
	private static int[] subSize;
	private static int[] minDepth;
	private static int[] subDepth;
	private static boolean[] visited;
	private static Node[] adj;
	private static ArrayDeque<Integer> copy;
	private static ArrayDeque<Integer> updated;
	
	private static int getSize(int curr, int parent) {
		Node child;
		
		subSize[curr] = 1;
		for (child = adj[curr]; child != null; child = child.next) {
			if (visited[child.idx] || child.idx == parent) {
				continue;
			}
			subSize[curr] += getSize(child.idx, curr);
		}
		return subSize[curr];
	}
	
	private static int getCentroid(int curr, int parent, int threshold) {
		Node child;
		
		for (child = adj[curr]; child != null; child = child.next) {
			if (visited[child.idx] || child.idx == parent) {
				continue;
			}
			if (subSize[child.idx] > threshold) {
				return getCentroid(child.idx, curr, threshold);
			}
		}
		return curr;
	}
	
	private static int dfs(int curr, int parent, int dist, int depth) {
		int ret;
		Node child;
		
		if (dist > k) {
			return INF;
		}
		ret = INF;
		if (depth < subDepth[dist]) {
			subDepth[dist] = depth;
			copy.addLast(dist);
			updated.addLast(dist);
		}
		ret = Math.min(ret, minDepth[k - dist] + depth);
		for (child = adj[curr]; child != null; child = child.next) {
			if (visited[child.idx] || child.idx == parent) {
				continue;
			}
			ret = Math.min(ret, dfs(child.idx, curr, dist + child.weight, depth + 1));
		}
		return ret;
	}
	
	private static int dnc(int curr) {
		int ret;
		int dist;
		int centroid;
		Node child;
		
		centroid = getCentroid(curr, -1, getSize(curr, -1) >> 1);
		visited[centroid] = true;
		while (!updated.isEmpty()) {
			minDepth[updated.pollFirst()] = INF;
		}
		while (!copy.isEmpty()) {
			subDepth[copy.pollFirst()] = INF;
		}
		ret = INF;
		for (child = adj[centroid]; child != null; child = child.next) {
			if (visited[child.idx]) {
				continue;
			}
			while (!copy.isEmpty()) {
				dist = copy.pollFirst();
				if (subDepth[dist] < minDepth[dist]) {
					minDepth[dist] = subDepth[dist];
					updated.addLast(dist);
				}
				subDepth[dist] = INF;
			}
			ret = Math.min(ret, dfs(child.idx, centroid, child.weight, 1));
		}
		for (child = adj[centroid]; child != null; child = child.next) {
			if (visited[child.idx]) {
				continue;
			}
			ret = Math.min(ret, dnc(child.idx));
		}
		return ret;
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int u;
		int v;
		int l;
		int i;
		int ans;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		adj = new Node[n];
		for (i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			adj[u] = new Node(v, l, adj[u]);
			adj[v] = new Node(u, l, adj[v]);
		}
		subSize = new int[n];
		visited = new boolean[n];
		minDepth = new int[k + 1];
		subDepth = new int[k + 1];
		for (i = 1; i <= k; i++) {
			minDepth[i] = INF;
			subDepth[i] = INF;
		}
		copy = new ArrayDeque<>(n);
		updated = new ArrayDeque<>(n);
		ans = dnc(0);
		System.out.print(ans == INF ? "-1" : ans);
	}
}
