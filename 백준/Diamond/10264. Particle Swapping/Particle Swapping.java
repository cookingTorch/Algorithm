import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final char LINE_FEED = '\n';
	private static final String DELIM = " ";

	private static final class Edge1 {
		int to;
		Edge1 next;

		Edge1(int to, Edge1 next) {
			this.to = to;
			this.next = next;
		}
	}

	private static final class Edge2 implements Comparable<Edge2> {
		int u;
		int v;
		int weight;

		Edge2(int u, int v) {
			this.u = u;
			this.v = v;
			weight = Math.min(getDist(points[(u - 1) / n + 1], points[(u - 1) % n + 1]), getDist(points[(v - 1) / n + 1], points[(v - 1) % n + 1]));
		}

		@Override
		public int compareTo(Edge2 o) {
			return o.weight - weight;
		}
	}

	private static final class Pbs {
		int u;
		int v;
		int left;
		int right;
		int ans;
		Pbs next;

		Pbs(int x, int y, Pbs next) {
			u = (x - 1) * n + y;
			v = (y - 1) * n + x;
			left = 1;
			right = size;
			this.next = next;
		}

		void validate(int mid) {
			arr[mid] = next;
			if (find(u) == find(v)) {
				right = mid;
			} else {
				left = mid + 1;
			}
			if (left >= right) {
				ans = edges[mid].weight;
				cnt++;
				return;
			}
			mid = left + right >>> 1;
			next = arr[mid];
			arr[mid] = this;
		}
	}

	private static int n;
	private static int cnt;
	private static int size;
	private static int[] initRoots;
	private static int[] roots;
	private static int[][] points;
	private static Pbs[] arr;
	private static Edge1[] adj;
	private static Edge2[] edges;

	private static int getDist(int[] p1, int[] p2) {
		return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
	}

	private static void prim() {
		int x;
		int y;
		int to;
		int node;
		int nodeCnt;
		boolean[] visited;
		Edge1 edge;
		Edge2 curr;
		PriorityQueue<Edge2> pq;

		edges = new Edge2[size];
		visited = new boolean[size + 1];
		visited[1] = true;
		pq = new PriorityQueue<>();
		for (edge = adj[1]; edge != null; edge = edge.next) {
			pq.offer(new Edge2(1, edge.to));
			pq.offer(new Edge2(1, (edge.to - 1) * n + 1));
		}
		nodeCnt = 1;
		for (;;) {
			curr = pq.poll();
			if (visited[node = curr.v]) {
				continue;
			}
			visited[node] = true;
			edges[nodeCnt] = curr;
			if (++nodeCnt == size) {
				break;
			}
			x = (node - 1) / n + 1;
			y = (node - 1) % n + 1;
			for (edge = adj[y]; edge != null; edge = edge.next) {
				if (visited[to = (x - 1) * n + edge.to]) {
					continue;
				}
				pq.offer(new Edge2(node, to));
			}
			for (edge = adj[x]; edge != null; edge = edge.next) {
				if (visited[to = (edge.to - 1) * n + y]) {
					continue;
				}
				pq.offer(new Edge2(node, to));
			}
		}
		Arrays.sort(edges, 1, size);
	}

	private static int find(int v) {
		if (roots[v] <= 0) {
			return v;
		}
		return roots[v] = find(roots[v]);
	}

	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u == v) {
			return;
		}
		if (roots[u] > roots[v]) {
			roots[u] = v;
		} else {
			if (roots[u] == roots[v]) {
				roots[u]--;
			}
			roots[v] = u;
		}
	}

	public static void main(String[] args) throws IOException {
		int m;
		int l;
		int u;
		int v;
		int i;
		int mid;
		Pbs[] ans;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		points = new int[n + 1][2];
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine(), DELIM, false);
			points[i][0] = Integer.parseInt(st.nextToken());
			points[i][1] = Integer.parseInt(st.nextToken());
		}
		adj = new Edge1[n + 1];
		m = Integer.parseInt(br.readLine());
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), DELIM, false);
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			adj[u] = new Edge1(v, adj[u]);
			adj[v] = new Edge1(u, adj[v]);
		}
		size = n * n;
		l = Integer.parseInt(br.readLine());
		ans = new Pbs[l];
		arr = new Pbs[size];
		mid = (size + 1) >>> 1;
		for (i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine(), DELIM, false);
			arr[mid] = new Pbs(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), arr[mid]);
			ans[i] = arr[mid];
		}
		prim();
		initRoots = new int[size + 1];
		roots = new int[size + 1];
		cnt = 0;
		while (cnt != l) {
			System.arraycopy(initRoots, 1, roots, 1, size);
			for (i = 1; i < size; i++) {
				union(edges[i].u, edges[i].v);
				while (arr[i] != null) {
					arr[i].validate(i);
				}
			}
		}
		sb = new StringBuilder();
		for (i = 0; i < l; i++) {
			sb.append(Math.sqrt(ans[i].ans)).append(LINE_FEED);
		}
		System.out.print(sb.toString());
	}
}
