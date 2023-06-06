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
	private static void addAdj(int from, int to, int capacity) {
		Edge forward = null, backward;
		for (Edge line : adj.get(from)) {
			if (line.to == to) {
				forward = line;
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
	
	// 증가경로 탐색
	private static boolean bfs(int source, int sink) {
		int curr;
		Queue<Integer> queue = new LinkedList<>();
		Arrays.fill(prev, -1);
		queue.add(source);
		while (!queue.isEmpty()) {
			curr = queue.poll();
			for (Edge line : adj.get(curr)) {
				if (line.capacity - line.flow > 0 && prev[line.to] == -1) {
					queue.add(line.to);
					prev[line.to] = curr;
					path[line.to] = line;
					if (line.to == sink) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	// 최대 유량 계산
	private static int maxFlow(int source, int sink) {
		int minFlow, totalFlow = 0, i;
		while (bfs(source, sink)) {
			minFlow = 101;
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
		
		int n, m, i, j, capacity, source, sink;
		
		// Source, Sink 설정
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		source = 0;
		sink = n + m + 1;
		
		// 간선 입력
		adj = new ArrayList<>();
		for (i = 0; i <= sink; i++) {
			adj.add(new ArrayList<>());
		}
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			addAdj(0, i, Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		for (i = n + 1; i <= n + m; i++) {
			addAdj(i, sink, Integer.parseInt(st.nextToken()));
		}
		for (i = n + 1; i <= n + m; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 1; j <= n; j++) {
				capacity = Integer.parseInt(st.nextToken());
				if (capacity > 0) {
					addAdj(j, i, capacity);
				}
			}
		}
		
		// 출력
		prev = new int[sink + 1];
		path = new Edge[sink + 1];
		sb.append(maxFlow(source, sink));
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}