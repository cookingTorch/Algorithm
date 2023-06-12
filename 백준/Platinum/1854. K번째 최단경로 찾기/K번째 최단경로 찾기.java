import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	// 간선
	private static class Edge {
		int to, weight;
		
		Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
	
	// 정점
	private static class Node implements Comparable<Node> {
		int to, dist;
		
		Node(int index, int value) {
			this.to = index;
			this.dist = value;
		}

		@Override
		public int compareTo(Node other) {
			return this.dist - other.dist;
		}
	}
	
	private static ArrayList<ArrayList<Edge>> adj;
	private static ArrayList<PriorityQueue<Integer>> distance;
	
	// 다익스트라
	private static void dijkstra(int start, int k) {
		int curr;
		Node node;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		distance.get(start).add(0);
		pq.add(new Node(start, distance.get(start).peek()));
		while (!pq.isEmpty()) {
			node = pq.poll();
			curr = node.to;
			for (Edge edge : adj.get(curr)) {
				if (distance.get(edge.to).size() < k || node.dist + edge.weight < distance.get(edge.to).peek()) {
					distance.get(edge.to).add(node.dist + edge.weight);
					if (distance.get(edge.to).size() > k) {
						distance.get(edge.to).remove();
					}
					pq.add(new Node(edge.to, node.dist + edge.weight));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, k, a, b, c, i;
		
		// N, M, K
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		// 간선 입력
		adj = new ArrayList<>();
		for (i = 0; i <= n; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			adj.get(a).add(new Edge(b, c));
		}
		
		// 경로 탐색
		distance = new ArrayList<>();
		for (i = 0; i <= n; i++) {
			distance.add(new PriorityQueue<>(Comparator.reverseOrder()));
		}
		dijkstra(1, k);
		
		// 출력
		for (i = 1; i <= n; i++) {
			if (distance.get(i).size() < k) {
				sb.append("-1").append("\n");
			}
			else {
				sb.append(distance.get(i).peek()).append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}