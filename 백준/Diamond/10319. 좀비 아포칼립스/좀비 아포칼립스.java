import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int MAX_CAPACITY = 101;
	
	private static class Edge {
		int to, time, capacity, flow;
		Edge reverse;
		
		Edge(int to, int time, int capacity) {
			this.to = to;
			this.time = time;
			this.capacity = capacity;
			this.flow = 0;
		}
	}
	
	private static int[][][] prev;
	private static Edge[][] path;
	private static ArrayList<ArrayList<ArrayList<Edge>>> adj;
	
	private static void addEdge(int from, int to, int fromTime, int toTime, int capacity) {
		Edge forward, backward;
		
		forward = new Edge(to, toTime, capacity);
		backward = new Edge(from, fromTime, 0);
		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).get(fromTime).add(forward);
		adj.get(to).get(toTime).add(backward);
	}
	
	private static void addAdj(int from, int to, int capacity, int time, int maxTime) {
		int fromTime;
		
		for (fromTime = 0; fromTime <= maxTime - time; fromTime++) {
			addEdge(from, to, fromTime, fromTime + time, capacity);
		}
	}
	
	private static void addTime(int node, int capacity, int maxTime) {
		int fromTime;
		
		for (fromTime = 0; fromTime < maxTime; fromTime++) {
			addEdge(node, node, fromTime, fromTime + 1, capacity);
		}
	}
	
	private static void addSink(int from, int sink, int capacity, int maxTime) {
		int fromTime;
		
		for (fromTime = 0; fromTime <= maxTime; fromTime++) {
			addEdge(from, sink, fromTime, 0, capacity);
		}
	}
	
	private static boolean equal(int[] a, int[] b) {
		return a[0] == b[0] && a[1] == b[1];
	}
	
	private static boolean bfs(int[] source, int[] sink, int maxTime) {
		int node, time, i, j;
		int[] minus, curr;
		Queue<int[]> q = new LinkedList<>();
		
		minus = new int[] {-1, -1};
		for (i = 0; i < sink[0] + 1; i++) {
			for (j = 0; j <= maxTime; j++) {
				prev[i][j] = new int[] {-1, -1};
			}
		}
		q.add(source);
		while (!q.isEmpty()) {
			curr = q.poll();
			node = curr[0];
			time = curr[1];
			for (Edge line : adj.get(node).get(time)) {
				if (line.capacity - line.flow > 0 && equal(prev[line.to][line.time], minus)) {
					prev[line.to][line.time] = curr;
					path[line.to][line.time] = line;
					if (line.to == sink[0]) {
						return true;
					}
					q.add(new int[] {line.to, line.time});
				}
			}
		}
		return false;
	}
	
	private static int maxFlow(int[] source, int[] sink, int maxTime) {
		int flow, totalFlow;
		int[] i;
		
		totalFlow = 0;
		while (bfs(source, sink, maxTime)) {
			flow = MAX_CAPACITY;
			for (i = sink; !equal(i, source); i = prev[i[0]][i[1]]) {
				flow = Math.min(flow, path[i[0]][i[1]].capacity - path[i[0]][i[1]].flow);
			}
			for (i = sink; !equal(i, source); i = prev[i[0]][i[1]]) {
				path[i[0]][i[1]].flow += flow;
				path[i[0]][i[1]].reverse.flow -= flow;
			}
			totalFlow += flow;
		}
		return totalFlow;
	}
	
	private static void solution(BufferedReader br, StringBuilder sb, StringTokenizer st) throws IOException {
		int n, i, g, s, m, r, a, b, p, t, source, sink, j, k;
		int[] hospital;
		
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		i = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(br.readLine());
		hospital = new int[m];
		for (j = 0; j < m; j++) {
			hospital[j] = Integer.parseInt(br.readLine());
		}
		
		source = 0;
		sink = n + 1;
		
		adj = new ArrayList<>();
		for (j = 0; j <= sink; j++) {
			adj.add(new ArrayList<>());
			for (k = 0; k <= s; k++) {
				adj.get(j).add(new ArrayList<>());
			}
		}
		addEdge(source, i, 0, 0, g);
		for (j = 1; j <= n; j++) {
			addTime(j, g, s);
		}
		r = Integer.parseInt(br.readLine());
		for (j = 0; j < r; j++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			p = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			addAdj(a, b, p, t, s);
		}
		for (j = 0; j < m; j++) {
			addSink(hospital[j], sink, g, s);
		}
		
		prev = new int[sink + 1][s + 1][2];
		path = new Edge[sink + 1][s + 1];
		sb.append(maxFlow(new int[] {source, 0}, new int[] {sink, 0}, s)).append('\n');
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			solution(br, sb, st);
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}