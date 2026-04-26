import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static final long INF = Long.MAX_VALUE >> 2;
	private static final String DELIM = " ";

	private static final class Edge {
		int to;
		int cost;

		Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	private static final class Node implements Comparable<Node> {
		int vertex;
		long cost;

		Node(int vertex, long cost) {
			this.vertex = vertex;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(cost, o.cost);
		}
	}

	private static int k;
	private static long[] tree;
	private static ArrayList<ArrayList<Edge>> adj;

	private static final void update(int node, int left, int right, int queryLeft, int queryRight, long value) {
		int mid;

		if (queryRight < left || right < queryLeft) {
			return;
		}
		if (queryLeft <= left && right <= queryRight) {
			tree[node] = Math.min(tree[node], value);
			return;
		}
		mid = (left + right) >> 1;
		update(node << 1, left, mid, queryLeft, queryRight, value);
		update((node << 1) | 1, mid + 1, right, queryLeft, queryRight, value);
	}

	private static final long query(int node, int left, int right, int idx) {
		int mid;
		long ret;

		ret = tree[node];
		if (left == right) {
			return ret;
		}
		mid = (left + right) >> 1;
		if (idx <= mid) {
			ret = Math.min(ret, query(node << 1, left, mid, idx));
		} else {
			ret = Math.min(ret, query((node << 1) | 1, mid + 1, right, idx));
		}
		return ret;
	}

	private static final long[] dijkstra(int n, int start) {
		long[] dist;
		long cost;
		PriorityQueue<Node> pq;
		Node node;

		dist = new long[n + 1];
		Arrays.fill(dist, INF);
		pq = new PriorityQueue<>();
		dist[start] = 0L;
		pq.add(new Node(start, 0L));
		while (!pq.isEmpty()) {
			node = pq.poll();
			if (dist[node.vertex] < node.cost) {
				continue;
			}
			for (Edge edge : adj.get(node.vertex)) {
				cost = node.cost + edge.cost;
				if (cost < dist[edge.to]) {
					dist[edge.to] = cost;
					pq.add(new Node(edge.to, cost));
				}
			}
		}
		return dist;
	}

	private static final int[] buildIndex(int n, int[] pos, long[] dist) {
		int i;
		int u;
		int[] idx;
		Integer[] order;

		idx = new int[n + 1];
		Arrays.fill(idx, -1);
		order = new Integer[n];
		for (i = 0; i < n; i++) {
			order[i] = i + 1;
		}
		Arrays.sort(order, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Long.compare(dist[o1], dist[o2]);
			}
		});
		for (i = 0; i < n; i++) {
			u = order[i];
			if (dist[u] == INF) {
				continue;
			}
			if (pos[u] != -1) {
				idx[u] = pos[u];
				continue;
			}
			for (Edge edge : adj.get(u)) {
				if (dist[edge.to] != INF && dist[edge.to] + edge.cost == dist[u]) {
					idx[u] = idx[edge.to];
					break;
				}
			}
		}
		return idx;
	}

	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int a;
		int b;
		int i;
		int u;
		int v;
		int w;
		int left;
		int right;
		long cost;
		long answer;
		int[] edgeU;
		int[] edgeV;
		int[] edgeW;
		int[] path;
		int[] pos;
		int[] leftIdx;
		int[] rightIdx;
		long[] distA;
		long[] distB;
		boolean[][] lucky;
		BufferedReader br;
		StringTokenizer st;
		StringBuilder sb;

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine(), DELIM, false);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		adj = new ArrayList<>();
		for (i = 0; i <= n; i++) {
			adj.add(new ArrayList<>());
		}
		edgeU = new int[m];
		edgeV = new int[m];
		edgeW = new int[m];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), DELIM, false);
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			edgeU[i] = u;
			edgeV[i] = v;
			edgeW[i] = w;
			adj.get(u).add(new Edge(v, w));
			adj.get(v).add(new Edge(u, w));
		}
		st = new StringTokenizer(br.readLine(), DELIM, false);
		k = Integer.parseInt(st.nextToken());
		path = new int[k];
		pos = new int[n + 1];
		Arrays.fill(pos, -1);
		lucky = new boolean[n + 1][n + 1];
		for (i = 0; i < k; i++) {
			path[i] = Integer.parseInt(st.nextToken());
			pos[path[i]] = i;
			if (i > 0) {
				lucky[path[i - 1]][path[i]] = true;
				lucky[path[i]][path[i - 1]] = true;
			}
		}

		distA = dijkstra(n, a);
		distB = dijkstra(n, b);
		leftIdx = buildIndex(n, pos, distA);
		rightIdx = buildIndex(n, pos, distB);

		tree = new long[(k << 2) + 5];
		Arrays.fill(tree, INF);
		for (i = 0; i < m; i++) {
			u = edgeU[i];
			v = edgeV[i];
			w = edgeW[i];
			if (lucky[u][v]) {
				continue;
			}

			if (leftIdx[u] != -1 && rightIdx[v] != -1) {
				cost = distA[u] + w + distB[v];
				left = leftIdx[u];
				right = rightIdx[v] - 1;
				if (left <= right) {
					update(1, 0, k - 2, left, right, cost);
				}
			}

			if (leftIdx[v] != -1 && rightIdx[u] != -1) {
				cost = distA[v] + w + distB[u];
				left = leftIdx[v];
				right = rightIdx[u] - 1;
				if (left <= right) {
					update(1, 0, k - 2, left, right, cost);
				}
			}
		}

		for (i = 0; i < k - 1; i++) {
			answer = query(1, 0, k - 2, i);
			if (answer == INF) {
				sb.append("-1\n");
			} else {
				sb.append(answer).append('\n');
			}
		}
		System.out.print(sb);
	}
}