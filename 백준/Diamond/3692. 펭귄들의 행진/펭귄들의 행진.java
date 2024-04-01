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
	private static final int MAX_N = 100;
	private static final int MAX_SIZE = 2 * MAX_N + 1;
	
	private static final class Edge {
		int to, capacity, flow;
		Edge reverse;
		
		Edge(int to, int capacity) {
			this.to = to;
			this.capacity = capacity;
		}
	}
	
	private static int size;
	private static int[] level, work;
	private static double[][] ices;
	private static Queue<Integer> q;
	private static ArrayList<Edge> edgeList;
	private static ArrayList<ArrayList<Edge>> adj;
	
	private static boolean canJump(double x1, double y1, double x2, double y2, double d) {
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)) <= d;
	}
	
	private static void addAdj(int from, int to, int capacity) {
		Edge forward, backward;
		
		forward = new Edge(to, capacity);
		backward = new Edge(from, 0);
		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
		edgeList.add(forward);
		edgeList.add(backward);
	}
	
	private static boolean bfs(int source, int sink) {
		int curr;
		
		Arrays.fill(level, 0, size, INF);
		level[source] = 0;
		q.add(source);
		while (!q.isEmpty()) {
			curr = q.poll();
			for (Edge edge : adj.get(curr)) {
				if (level[edge.to] == INF && edge.capacity > edge.flow) {
					level[edge.to] = level[curr] + 1;
					q.add(edge.to);
				}
			}
		}
		return level[sink] != INF;
	}
	
	private static int dfs(int curr, int sink, int max) {
		int flow;
		Edge edge;
		ArrayList<Edge> edges;
		
		if (curr == sink) {
			return max;
		}
		edges = adj.get(curr);
		for (; work[curr] < edges.size(); work[curr]++) {
			edge = edges.get(work[curr]);
			if (level[edge.to] == level[curr] + 1 && edge.capacity > edge.flow) {
				flow = dfs(edge.to, sink, Math.min(max, edge.capacity - edge.flow));
				if (flow > 0) {
					edge.flow += flow;
					edge.reverse.flow -= flow;
					return flow;
				}
			}
		}
		return 0;
	}
	
	private static int dinic(int source, int sink) {
		int total, flow;
		
		total = 0;
		while (bfs(source, sink)) {
			work = new int[size];
			do {
				flow = dfs(source, sink, INF);
				total += flow;
			} while (flow != 0);
		}
		return total;
	}
	
	private static void solution(BufferedReader br, StringBuilder sb, StringTokenizer st) throws IOException {
		int n, source, penguin, sum, i, j;
		double d;
		boolean flag;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		d = Double.parseDouble(st.nextToken());
		source = 2 * n;
		size = source + 1;
		for (i = 0; i < size; i++) {
			adj.set(i, new ArrayList<>());
		}
		edgeList = new ArrayList<>();
		sum = 0;
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			ices[i][0] = Double.parseDouble(st.nextToken());
			ices[i][1] = Double.parseDouble(st.nextToken());
			if ((penguin = Integer.parseInt(st.nextToken())) > 0) {
				addAdj(source, i, penguin);
				sum += penguin;
			}
			addAdj(i, i + n, Integer.parseInt(st.nextToken()));
		}
		for (i = 0; i < n; i++) {
			for (j = i + 1; j < n; j++) {
				if (canJump(ices[i][0], ices[i][1], ices[j][0], ices[j][1], d)) {
					addAdj(i + n, j, INF);
					addAdj(j + n, i, INF);
				}
			}
		}
		flag = false;
		for (i = 0; i < n; i++) {
			for (Edge edge : edgeList) {
				edge.flow = 0;
			}
			if (dinic(source, i) == sum) {
				sb.append(i).append(' ');
				flag = true;
			}
		}
		if (!flag) {
			sb.append("-1");
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase, i;
		
		ices = new double[MAX_N][2];
		adj = new ArrayList<>(MAX_SIZE);
		for (i = 0; i < MAX_SIZE; i++) {
			adj.add(new ArrayList<>());
		}
		level = new int[MAX_SIZE];
		q = new ArrayDeque<>();
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			solution(br, sb, st);
			sb.append('\n');
		}
		System.out.print(sb);
	}
}
