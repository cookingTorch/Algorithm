import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static final double INF = 1e18;
	private static final double EPS = 1e-9;
	private static final String DELIM = " ";

	private static final class Edge {
		int to;
		double cap;
		Edge reverse;

		Edge(int to, double cap) {
			this.to = to;
			this.cap = cap;
		}
	}

	private static int n;
	private static int m;
	private static int source;
	private static int sink;
	private static int[] degree;
	private static int[] edgeU;
	private static int[] edgeV;
	private static int[] level;
	private static int[] work;
	private static ArrayList<ArrayList<Edge>> adj;

	private static final void addAdj(int from, int to, double cap) {
		Edge forward;
		Edge backward;

		forward = new Edge(to, cap);
		backward = new Edge(from, 0.0);
		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
	}

	private static final void build(double density) {
		int i;

		adj = new ArrayList<>();
		for (i = 0; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 1; i <= n; i++) {
			addAdj(source, i, m);
			addAdj(i, sink, m + 2.0 * density - degree[i]);
		}
		for (i = 0; i < m; i++) {
			addAdj(edgeU[i], edgeV[i], 1.0);
			addAdj(edgeV[i], edgeU[i], 1.0);
		}
	}

	private static final boolean bfs() {
		int head;
		int tail;
		int curr;
		int[] q;

		Arrays.fill(level, -1);
		q = new int[sink + 1];
		head = 0;
		tail = 0;
		q[tail++] = source;
		level[source] = 0;
		while (head < tail) {
			curr = q[head++];
			for (Edge edge : adj.get(curr)) {
				if (edge.cap > EPS && level[edge.to] == -1) {
					level[edge.to] = level[curr] + 1;
					q[tail++] = edge.to;
				}
			}
		}
		return level[sink] != -1;
	}

	private static final double dfs(int curr, double flow) {
		double pushed;
		Edge edge;

		if (curr == sink) {
			return flow;
		}
		while (work[curr] < adj.get(curr).size()) {
			edge = adj.get(curr).get(work[curr]);
			if (edge.cap > EPS && level[edge.to] == level[curr] + 1) {
				pushed = dfs(edge.to, Math.min(flow, edge.cap));
				if (pushed > EPS) {
					edge.cap -= pushed;
					edge.reverse.cap += pushed;
					return pushed;
				}
			}
			work[curr]++;
		}
		return 0.0;
	}

	private static final double dinic() {
		double flow;
		double pushed;

		flow = 0.0;
		while (bfs()) {
			Arrays.fill(work, 0);
			while (true) {
				pushed = dfs(source, INF);
				if (pushed <= EPS) {
					break;
				}
				flow += pushed;
			}
		}
		return flow;
	}

	private static final boolean possible(double density) {
		build(density);
		return dinic() < (double) m * n - EPS;
	}

	private static final boolean[] getReachable() {
		int head;
		int tail;
		int curr;
		int[] q;
		boolean[] visited;

		visited = new boolean[sink + 1];
		q = new int[sink + 1];
		head = 0;
		tail = 0;
		q[tail++] = source;
		visited[source] = true;
		while (head < tail) {
			curr = q[head++];
			for (Edge edge : adj.get(curr)) {
				if (edge.cap > EPS && !visited[edge.to]) {
					visited[edge.to] = true;
					q[tail++] = edge.to;
				}
			}
		}
		return visited;
	}

	public static void main(String[] args) throws IOException {
		int i;
		int u;
		int v;
		int cnt;
		double left;
		double right;
		double mid;
		boolean[] reachable;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), DELIM, false);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		if (m == 0) {
			System.out.println(1);
			System.out.println(1);
			return;
		}

		source = 0;
		sink = n + 1;
		degree = new int[n + 1];
		edgeU = new int[m];
		edgeV = new int[m];
		level = new int[sink + 1];
		work = new int[sink + 1];

		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), DELIM, false);
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			edgeU[i] = u;
			edgeV[i] = v;
			degree[u]++;
			degree[v]++;
		}

		left = 0.0;
		right = m;
		for (i = 0; i < 70; i++) {
			mid = (left + right) / 2.0;
			if (possible(mid)) {
				left = mid;
			} else {
				right = mid;
			}
		}

		build(Math.max(0.0, left - 1e-7));
		dinic();
		reachable = getReachable();

		sb = new StringBuilder();
		cnt = 0;
		for (i = 1; i <= n; i++) {
			if (reachable[i]) {
				cnt++;
			}
		}
		sb.append(cnt).append('\n');
		for (i = 1; i <= n; i++) {
			if (reachable[i]) {
				sb.append(i).append('\n');
			}
		}
		System.out.print(sb);
	}
}