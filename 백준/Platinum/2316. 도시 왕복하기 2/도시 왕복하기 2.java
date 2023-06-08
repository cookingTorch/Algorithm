import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	// 간선
	private static class Edge {
		int to, capacity, flow;
		Edge reverse;
		
		Edge(int to, int capacity) {
			this.to = to;
			this.capacity = capacity;
			this.flow = 0;
		}
	}
	
	private static int[] prev;
	private static Edge[] path;
	private static ArrayList<ArrayList<Edge>> adj;
	
	// 간선 추가
	private static void addEdge(int from, int to, int capacity) {
		Edge forward = null, backward;
		for (Edge edge : adj.get(from)) {
			if (edge.to == to) {
				forward = edge;
				forward.capacity = capacity;
				break;
			}
		}
		if (forward == null) {
			forward = new Edge(to, capacity);
			backward = new Edge(from, 0);
			forward.reverse = backward;
			backward.reverse = forward;
			adj.get(from).add(forward);
			adj.get(to).add(backward);
		}
	}
	
	// 증가 경로 탐색
	private static boolean bfs(int source, int sink) {
		int curr;
		Queue<Integer> queue = new LinkedList<>();
		Arrays.fill(prev, -1);
		queue.add(source);
		while (!queue.isEmpty()) {
			curr = queue.poll();
			for (Edge edge : adj.get(curr)) {
				if (edge.capacity - edge.flow > 0 && prev[edge.to] == -1) {
					queue.add(edge.to);
					prev[edge.to] = curr;
					path[edge.to] = edge;
					if (edge.to == sink) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	// 최대 유량 탐색
	private static int maxFlow(int source, int sink) {
		int totalFlow = 0, minFlow, i;
		while (bfs(source, sink)) {
			minFlow = 2;
			for (i = sink; i != source; i = prev[i]) {
				minFlow = Math.min(minFlow, path[i].capacity - path[i].flow);
			}
			for (i = sink; i != source; i = prev[i]) {
				path[i].flow += minFlow;
				path[i].reverse.flow -= minFlow;
			}
			totalFlow += minFlow;
		}
		return totalFlow;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, p, i, city1, city2;
		
		// N, P 입력
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		
		// in - out
		adj = new ArrayList<>();
		for (i = 0; i <= 2 * n; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 3; i <= n; i++) {
			addEdge(i, i + n, 1);
		}
		
		// 간선 입력
		for (i = 0; i < p; i++) {
			st = new StringTokenizer(br.readLine());
			city1 = Integer.parseInt(st.nextToken());
			city2 = Integer.parseInt(st.nextToken());
			addEdge(city1 + n, city2, 1);
			addEdge(city2 + n, city1, 1);
		}
		
		// 출력
		prev = new int[2 * n + 1];
		path = new Edge[2 * n + 1];
		sb.append(maxFlow(1 + n, 2));
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}