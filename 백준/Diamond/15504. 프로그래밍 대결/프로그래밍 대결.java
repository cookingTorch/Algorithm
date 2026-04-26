import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static final long INF = Long.MAX_VALUE >> 2;
	private static final int MASK = (1 << 20) - 1;
	private static final String DELIM = " ";

	private static final class Edge {
		int to;
		int cap;
		int flow;
		int cost;
		Edge reverse;

		Edge(int to, int cap, int cost) {
			this.to = to;
			this.cap = cap;
			this.flow = 0;
			this.cost = cost;
		}
	}

	private static int[] prev;
	private static boolean[] inQueue;
	private static long[] distance;
	private static Edge[] path;
	private static ArrayList<ArrayList<Edge>> adj;

	private static final long pack(int a, int h, int l) {
		return ((long) a << 40) | ((long) h << 20) | l;
	}

	private static final int getA(long value) {
		return (int) (value >> 40);
	}

	private static final int getH(long value) {
		return (int) ((value >> 20) & MASK);
	}

	private static final int getL(long value) {
		return (int) (value & MASK);
	}

	private static final void addAdj(int from, int to, int cap, int cost) {
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
		long dist;
		ArrayDeque<Integer> q;

		Arrays.fill(inQueue, false);
		Arrays.fill(distance, INF);
		distance[source] = 0L;
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

	private static final long mcmf(int source, int sink, int maxFlow) {
		int i;
		int flow;
		int totalFlow;
		long cost;

		cost = 0L;
		totalFlow = 0;
		while (totalFlow < maxFlow && spfa(source, sink)) {
			flow = maxFlow - totalFlow;
			for (i = sink; i != source; i = prev[i]) {
				flow = Math.min(flow, path[i].cap - path[i].flow);
			}
			for (i = sink; i != source; i = prev[i]) {
				path[i].flow += flow;
				path[i].reverse.flow -= flow;
				cost += (long) path[i].cost * flow;
			}
			totalFlow += flow;
		}
		return cost;
	}

	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int j;
		int source;
		int sink;
		int nodeCnt;
		int cap;
		int cost;
		int[] a;
		int[] h;
		int[] l;
		long[] players;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		a = new int[n];
		h = new int[n];
		l = new int[n];
		players = new long[n];

		st = new StringTokenizer(br.readLine(), DELIM, false);
		for (i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), DELIM, false);
		for (i = 0; i < n; i++) {
			h[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), DELIM, false);
		for (i = 0; i < n; i++) {
			l[i] = Integer.parseInt(st.nextToken());
			players[i] = pack(a[i], h[i], l[i]);
		}

		Arrays.sort(players);
		for (i = 0; i < n; i++) {
			a[i] = getA(players[i]);
			h[i] = getH(players[i]);
			l[i] = getL(players[i]);
		}

		source = 0;
		sink = n << 1;
		nodeCnt = sink + 1;
		adj = new ArrayList<>();
		for (i = 0; i < nodeCnt; i++) {
			adj.add(new ArrayList<>());
		}

		for (i = 0; i < n - 1; i++) {
			addAdj(source, i + 1, 1, 0);
			for (j = i + 1; j < n; j++) {
				cost = h[i] + h[j] - (a[i] ^ a[j]);
				addAdj(i + 1, n + j, 1, cost);
			}
		}
		for (i = 0; i < n; i++) {
			if (i == n - 1) {
				cap = l[i];
			} else {
				cap = l[i] - 1;
			}
			addAdj(n + i, sink, cap, 0);
		}

		prev = new int[nodeCnt];
		inQueue = new boolean[nodeCnt];
		distance = new long[nodeCnt];
		path = new Edge[nodeCnt];

		System.out.println(-mcmf(source, sink, n - 1));
	}
}