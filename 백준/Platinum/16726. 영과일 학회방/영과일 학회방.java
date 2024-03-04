import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static final char FLOOR = '.';
	
	private static class Edge {
		int to, capacity, flow;
		Edge reverse;
		
		Edge(int to, int capacity) {
			this.to = to;
			this.capacity = capacity;
		}
	}
	
	private static int size, max;
	private static int[] prev;
	private static Edge[] path;
	private static ArrayList<LinkedList<Edge>> adj;
	
	private static void addAdj(int from, int to, int capacity) {
		Edge forward, backward;
		
		forward = new Edge(to, capacity);
		backward = new Edge(from, 0);
		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
	}
	
	private static boolean bfs(int source, int sink) {
		int curr;
		Queue<Integer> q;
		
		Arrays.fill(prev, -1);
		q = new ArrayDeque<>();
		q.add(source);
		while (!q.isEmpty()) {
			curr = q.poll();
			for (Edge edge : adj.get(curr)) {
				if (prev[edge.to] == -1 && edge.capacity > edge.flow) {
					prev[edge.to] = curr;
					path[edge.to] = edge;
					if (edge.to == sink) {
						return true;
					}
					q.add(edge.to);
				}
			}
		}
		return false;
	}
	
	private static int maxFlow(int source, int sink) {
		int flow, total, i;
		
		prev = new int[sink + 1];
		path = new Edge[sink + 1];
		total = 0;
		while (bfs(source, sink)) {
			flow = INF;
			for (i = sink; i != source; i = prev[i]) {
				flow = Math.min(flow, path[i].capacity - path[i].flow);
			}
			for (i = sink; i != source; i = prev[i]) {
				path[i].flow += flow;
				path[i].reverse.flow -= flow;
			}
			total += flow;
		}
		return total;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, cnt, i, j, loc, source, sink;
		char[] str, map;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		size = m;
		max = n * m;
		source = max;
		sink = source + 1;
		map = new char[max];
		cnt = 0;
		for (i = 0; i < n; i++) {
			str = br.readLine().toCharArray();
			for (j = 0; j < m; j++) {
				map[i * size + j] = str[j];
				if (str[j] == FLOOR) {
					cnt++;
				}
			}
		}
		adj = new ArrayList<>();
		for (i = 0; i <= sink; i++) {
			adj.add(new LinkedList<>());
		}
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				loc = i * size + j;
				if (map[loc] == FLOOR) {
					if ((i + j) % 2 == 0) {
						addAdj(source, loc, 1);
						if (i - 1 >= 0 && map[loc - size] == FLOOR) {
							addAdj(loc, loc - size, 1);
						}
						if (i + 1 < n && map[loc + size] == FLOOR) {
							addAdj(loc, loc + size, 1);
						}
						if (j - 1 >= 0 && map[loc - 1] == FLOOR) {
							addAdj(loc, loc - 1, 1);
						}
						if (j + 1 < m && map[loc + 1] == FLOOR) {
							addAdj(loc, loc + 1, 1);
						}
					} else {
						addAdj(loc, sink, 1);
					}
				}
			}
		}
		System.out.print(cnt - maxFlow(source, sink));
	}
}
