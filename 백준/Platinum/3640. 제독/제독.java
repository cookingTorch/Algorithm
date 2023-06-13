import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	// 간선
	private static class Edge {
		int to, capacity, flow, cost;
		Edge reverse;
		
		Edge(int to, int capacity, int cost) {
			this.to = to;
			this.capacity = capacity;
			this.flow = 0;
			this.cost = cost;
		}
	}
	
	// 정점
	private static class Node implements Comparable<Node> {
		int index, dist;
		
		Node(int index) {
			this.index = index;
			this.dist = distance[index];
		}
		
		@Override
		public int compareTo(Node other) {
			return this.dist - other.dist;
		}
	}
	
	private static final int INF = Integer.MAX_VALUE;
	private static int[] prev, distance;
	private static Edge[] path;
	private static ArrayList<ArrayList<Edge>> adj;
	
	// 간선 추가
	private static void addEdge(int from, int to, int capacity, int cost) {
		Edge forward, backward;
		forward = new Edge(to, capacity, cost);
		backward = new Edge(from, 0, -cost);
		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
	}
	
	// 최단 거리
	private static void dijkstra(int source, int sink) {
		int curr;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Arrays.fill(distance, INF);
		distance[source] = 0;
		pq.add(new Node(source));
		while (!pq.isEmpty()) {
			curr = pq.poll().index;
			if (curr == sink) {
				return;
			}
			for (Edge edge : adj.get(curr)) {
				if (edge.capacity - edge.flow > 0 && distance[curr] + edge.cost < distance[edge.to]) {
					prev[edge.to] = curr;
					path[edge.to] = edge;
					distance[edge.to] = distance[curr] + edge.cost;
					pq.add(new Node(edge.to));
				}
			}
		}
	}
	
	// 최소 비용
	private static int minCost(int source, int sink) {
		int totalCost = 0, minFlow, i, j;
		for (i = 0; i < 2; i++) {
			dijkstra(source, sink);
			minFlow = INF;
			for (j = sink; j != source; j = prev[j]) {
				minFlow = Math.min(minFlow, path[j].capacity - path[j].flow);
			}
			for (j = sink; j != source; j = prev[j]) {
				path[j].flow += minFlow;
				path[j].reverse.flow -= minFlow;
			}
			totalCost += minFlow * distance[sink];
		}
		return totalCost;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int v, e, a, b, c, i;
		
		// 테스트 케이스
		while ((str = br.readLine()) != null) {
			st = new StringTokenizer(str);
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			// 간선 입력
			adj = new ArrayList<>();
			for (i = 0; i <= 2 * v; i++) {
				adj.add(new ArrayList<>());
			}
			for (i = 1; i <= v; i++) {
				addEdge(i, i + v, 1, 0);
			}
			for (i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				addEdge(a + v, b, 1, c);
			}
			
			// 출력
			prev = new int[2 * v + 1];
			path = new Edge[2 * v + 1];
			distance = new int[2 * v + 1];
			sb.append(minCost(1 + v, v)).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}