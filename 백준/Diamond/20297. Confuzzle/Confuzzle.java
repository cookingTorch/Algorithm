import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE >> 1;
	
	private static final class Node {
		int idx;
		Node next;
		
		Node(int idx, Node next) {
			this.idx = idx;
			this.next = next;
		}
	}
	
	private static int ans;
	private static int[] nums;
	private static int[] subSize;
	private static int[] minDepth;
	private static int[] subDepth;
	private static boolean[] visited;
	private static Node[] adj;
	private static ArrayDeque<Integer> copy;
	private static ArrayDeque<Integer> updated;
	
	private static final int getSize(int curr, int parent) {
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
	
	private static final int getCentroid(int curr, int parent, int threshold) {
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
	
	private static final void dfs(int curr, int parent, int depth) {
		Node child;
		
		if (depth == ans) {
			return;
		}
		if (depth < subDepth[nums[curr]]) {
			subDepth[nums[curr]] = depth;
			copy.addLast(nums[curr]);
		}
		ans = Math.min(ans, minDepth[nums[curr]] + depth);
		for (child = adj[curr]; child != null; child = child.next) {
			if (visited[child.idx] || child.idx == parent) {
				continue;
			}
			dfs(child.idx, curr, depth + 1);
		}
	}
	
	private static final void dnc(int curr) {
		int num;
		int centroid;
		Node child;
		
		centroid = getCentroid(curr, 0, getSize(curr, 0) >> 1);
		visited[centroid] = true;
		while (!updated.isEmpty()) {
			minDepth[updated.pollFirst()] = INF;
		}
		while (!copy.isEmpty()) {
			subDepth[copy.pollFirst()] = INF;
		}
		minDepth[nums[centroid]] = 0;
		updated.addLast(nums[centroid]);
		for (child = adj[centroid]; child != null; child = child.next) {
			if (visited[child.idx]) {
				continue;
			}
			while (!copy.isEmpty()) {
				num = copy.pollFirst();
				if (subDepth[num] < minDepth[num]) {
					minDepth[num] = subDepth[num];
					updated.addLast(num);
				}
				subDepth[num] = INF;
			}
			dfs(child.idx, centroid, 1);
		}
		for (child = adj[centroid]; child != null; child = child.next) {
			if (visited[child.idx]) {
				continue;
			}
			dnc(child.idx);
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
		nums= new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		adj = new Node[n + 1];
		for (i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			adj[u] = new Node(v, adj[u]);
			adj[v] = new Node(u, adj[v]);
		}
		subSize = new int[n + 1];
		visited = new boolean[n + 1];
		minDepth = new int[n + 1];
		subDepth = new int[n + 1];
		for (i = 1; i <= n; i++) {
			minDepth[i] = INF;
			subDepth[i] = INF;
		}
		copy = new ArrayDeque<>(n);
		updated = new ArrayDeque<>(n);
		ans = INF;
		dnc(1);
		System.out.print(ans);
	}
}
