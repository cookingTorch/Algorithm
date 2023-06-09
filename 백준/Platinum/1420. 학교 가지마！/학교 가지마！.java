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
			for (Edge next : adj.get(curr)) {
				if (next.capacity - next.flow > 0 && prev[next.to] == -1) {
					queue.add(next.to);
					prev[next.to] = curr;
					path[next.to] = next;
					if (next.to == sink) {
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
		String str;
		StringTokenizer st;
		
		int n, m, size, source = 0, sink = 0, i, j, node, now, right, down;
		boolean flag = false;
		char[][] city;
		
		// 사이즈 입력
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		size = n * m;
		
		// 도시 입력
		city = new char[n][m];
		for (i = 0; i < n; i++) {
			str = br.readLine();
			for (j = 0; j < m; j++) {
				city[i][j] = str.charAt(j);
			}
		}
		
		// 간선 입력
		adj = new ArrayList<>();
		for (i = 0; i < 2 * size; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				if ((now = city[i][j]) != '#') {
					node = i * m + j;
					addEdge(node, node + size, 1);
					if (i < n - 1 && (down = city[i + 1][j]) != '#') {
						if (now != 'H' && down != 'K') {
							addEdge(node + size, node + m, 1);
						}
						if (now != 'K' && down != 'H') {
							addEdge(node + m + size, node, 1);
						}
						if ((now == 'K' && down == 'H') || (now == 'H' && down == 'K')) {
							flag = true;
							break;
						}
					}
					if (j < m - 1 && (right = city[i][j + 1]) != '#') {
						if (now != 'H' && right != 'K') {
							addEdge(node + size, node + 1, 1);
						}
						if (now != 'K' && right != 'H') {
							addEdge(node + 1 + size, node, 1);
						}
						if ((now == 'K' && right == 'H') || (now == 'H' && right == 'K')) {
							flag = true;
							break;
						}
					}
					if (now == 'K') {
						source = node + size;
					}
					else if (now == 'H') {
						sink = node;
					}
				}
			}
			if (flag) {
				break;
			}
		}
		
		// 출력
		if (flag) {
			sb.append("-1");
		}
		else {
			prev = new int[2 * size];
			path = new Edge[2 * size];
			sb.append(maxFlow(source, sink));
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}