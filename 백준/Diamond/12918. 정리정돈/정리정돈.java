import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static final int MAX_FLOW = Integer.MAX_VALUE;
	private static final double INF = Double.POSITIVE_INFINITY;
	private static final double EPS = 1e-12;
	private static final String DELIM = " ";

	private static final class Edge {
		int to;
		int cap;
		int flow;
		double cost;
		Edge reverse;

		Edge(int to, int cap, double cost) {
			this.to = to;
			this.cap = cap;
			this.flow = 0;
			this.cost = cost;
		}
	}

	private static int[] prev;
	private static double[] distance;
	private static boolean[] inQueue;
	private static Edge[] path;
	private static ArrayList<ArrayList<Edge>> adj;

	private static final double getSaving(int[] p1, int[] p2) {
		return Math.abs(p1[0]) + Math.abs(p2[0]) - Math.sqrt((p1[0] + p2[0]) * (p1[0] + p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]));
	}

	private static final void addAdj(int from, int to, int cap, double cost) {
		Edge forward;
		Edge backward;

		forward = new Edge(to, cap, cost);
		backward = new Edge(from, 0, -cost);
		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
	}

	private static final boolean spfa(int source, int sink) {
		int curr;
		double dist;
		ArrayDeque<Integer> q;

		Arrays.fill(inQueue, false);
		Arrays.fill(distance, INF);
		distance[source] = 0.0;
		q = new ArrayDeque<>();
		q.addLast(source);
		inQueue[source] = true;
		while (!q.isEmpty()) {
			curr = q.pollFirst();
			inQueue[curr] = false;
			for (Edge edge : adj.get(curr)) {
				if (edge.cap > edge.flow) {
					dist = distance[curr] + edge.cost;
					if (dist < distance[edge.to]) {
						distance[edge.to] = dist;
						prev[edge.to] = curr;
						path[edge.to] = edge;
						if (!inQueue[edge.to]) {
							q.addLast(edge.to);
							inQueue[edge.to] = true;
						}
					}
				}
			}
		}
		return distance[sink] < INF;
	}

	private static final double mcmf(int source, int sink) {
		int i;
		int flow;
		double cost;

		cost = 0.0;
		while (spfa(source, sink)) {
			if (distance[sink] >= -EPS) {
				break;
			}
			flow = MAX_FLOW;
			for (i = sink; i != source; i = prev[i]) {
				flow = Math.min(flow, path[i].cap - path[i].flow);
			}
			for (i = sink; i != source; i = prev[i]) {
				path[i].flow += flow;
				path[i].reverse.flow -= flow;
				cost += path[i].cost * flow;
			}
		}
		return cost;
	}

	public static void main(String[] args) throws IOException {
		int n;
		int x;
		int y;
		int i;
		int j;
		int neg;
		int pos;
		int sink;
		int source;
		int[][] points;
		double base;
		double saving;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		source = 0;
		sink = (n << 1) + 1;
		points = new int[n + 1][2];
		neg = 1;
		pos = n;
		base = 0.0;
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine(), DELIM, false);
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			base += Math.abs(x);
			if (x < 0) {
				points[neg][0] = x;
				points[neg++][1] = y;
			} else if (x > 0) {
				points[pos][0] = x;
				points[pos--][1] = y;
			}
		}
		adj = new ArrayList<>();
		for (i = source; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 1; i < neg; i++) {
			addAdj(source, i, 1, 0.0);
			for (j = pos + 1; j <= n; j++) {
				saving = getSaving(points[i], points[j]);
				addAdj(i, n + j, 1, -saving);
			}
		}
		for (j = pos + 1; j <= n; j++) {
			addAdj(n + j, sink, 1, 0.0);
		}
		prev = new int[sink + 1];
		distance = new double[sink + 1];
		inQueue = new boolean[sink + 1];
		path = new Edge[sink + 1];
		System.out.printf("%.3f", base + mcmf(source, sink));
	}
}