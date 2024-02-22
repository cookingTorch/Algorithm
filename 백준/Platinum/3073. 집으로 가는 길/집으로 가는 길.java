import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static final int MAX = 202;
	private static final char CHILD = 'm';
	private static final char HOME = 'H';
	
	private static class Edge {
		int to, capacity, flow, cost;
		Edge reverse;
		
		Edge(int to, int capacity, int cost) {
			this.to = to;
			this.capacity = capacity;
			this.cost = cost;
		}
	}
	
	private static int[] prev, distance;
	private static Edge[] path;
	private static boolean[] inQueue;
	private static Queue<Integer> q;
	private static ArrayList<ArrayList<Edge>> adj;
	
	private static void addAdj(int from, int to, int capacity, int cost) {
		Edge forward, backward;
		
		forward = new Edge(to, capacity, cost);
		backward = new Edge(from, 0, -cost);
		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
	}
	
	private static int dist(int[] child, int[] home) {
		return Math.abs(child[0] - home[0]) + Math.abs(child[1] - home[1]);
	}
	
	private static boolean spfa(int source, int sink) {
		int curr;
		
		Arrays.fill(distance, INF);
		distance[source] = 0;
		q.add(source);
		while (!q.isEmpty()) {
			curr = q.poll();
			inQueue[curr] = false;
			for (Edge edge : adj.get(curr)) {
				if (edge.capacity > edge.flow && distance[curr] + edge.cost < distance[edge.to]) {
					distance[edge.to] = distance[curr] + edge.cost;
					prev[edge.to] = curr;
					path[edge.to] = edge;
					if (!inQueue[edge.to]) {
						q.add(edge.to);
						inQueue[edge.to] = true;
					}
				}
			}
		}
		if (distance[sink] == INF) {
			return false;
		}
		return true;
	}
	
	private static int minCost(int source, int sink) {
		int flow, cost, i;
		
		cost = 0;
		while (spfa(source, sink)) {
			flow = INF;
			for (i = sink; i != source; i = prev[i]) {
				flow = Math.min(flow, path[i].capacity - path[i].flow);
			}
			for (i = sink; i != source; i = prev[i]) {
				path[i].flow += flow;
				path[i].reverse.flow -= flow;
				cost += flow * path[i].cost;
			}
		}
		return cost;
	}
	
	private static int solution(int n, int m, BufferedReader br) throws IOException {
		int source, sink, cnt, i, j;
		char[] str;
		ArrayList<int[]> children, homes;
		
		cnt = 0;
		children = new ArrayList<>();
		homes = new ArrayList<>();
		children.add(null);
		homes.add(null);
		for (i = 0; i < n; i++) {
			str = br.readLine().toCharArray();
			for (j = 0; j < m; j++) {
				if (str[j] == CHILD) {
					children.add(new int[] {i, j});
				} else if (str[j] == HOME) {
					homes.add(new int[] {i, j});
					cnt++;
				}
			}
		}
		source = 0;
		sink = cnt * 2 + 1;
		adj = new ArrayList<>();
		for (i = 0; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 1; i <= cnt; i++) {
			addAdj(source, i, 1, 0);
		}
		for (i = 1; i <= cnt; i++) {
			for (j = 1; j <= cnt; j++) {
				addAdj(i, cnt + j, 1, dist(children.get(i), homes.get(j)));
			}
		}
		for (i = 1; i <= cnt; i++) {
			addAdj(cnt + i, sink, 1, 0);
		}
		distance = new int[sink + 1];
		return minCost(source, sink);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m;
		
		q = new ArrayDeque<>();
		prev = new int[MAX];
		path = new Edge[MAX];
		inQueue = new boolean[MAX];
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		while (n != 0) {
			sb.append(solution(n, m, br)).append('\n');
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
		}
		System.out.print(sb);
	}
}
