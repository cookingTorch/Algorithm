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
	private static int MAX_CAPACITY = 2;
	
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
		Queue<Integer> q = new LinkedList<>();
		
		Arrays.fill(prev, -1);
		q.add(source);
		while(!q.isEmpty()) {
			curr = q.poll();
			for (Edge line : adj.get(curr)) {
				if (line.capacity - line.flow > 0 && prev[line.to] == -1) {
					prev[line.to] = curr;
					path[line.to] = line;
					if (line.to == sink) {
						return true;
					}
					q.add(line.to);
				}
			}
		}
		return false;
	}
	
	private static int maxFlow(int source, int sink) {
		int flow, totalFlow, i;
		
		totalFlow = 0;
		while (bfs(source, sink)) {
			flow = MAX_CAPACITY;
			for (i = sink; i != source; i = prev[i]) {
				flow = Math.min(flow, path[i].capacity - path[i].flow);
			}
			for (i = sink; i != source; i = prev[i]) {
				path[i].flow += flow;
				path[i].reverse.flow -= flow;
			}
			totalFlow += flow;
		}
		return totalFlow;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, p, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList<>();
		for (i = 0; i <= n; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < p; i++) {
			st = new StringTokenizer(br.readLine());
			addAdj(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 1);
		}
		
		prev = new int[n + 1];
		path = new Edge[n + 1];
		sb.append(maxFlow(1, 2));
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}