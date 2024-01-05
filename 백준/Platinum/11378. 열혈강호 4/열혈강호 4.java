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
		Edge forward = null, backward;
		
		for (Edge line : adj.get(from)) {
			if (line.to == to) {
				forward = line;
				forward.capacity += capacity;
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
	
	private static boolean bfs(int source, int sink) {
		int curr;
		Queue<Integer> queue = new LinkedList<>();
		
		Arrays.fill(prev, -1);
		queue.add(source);
		while(!queue.isEmpty()) {
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
	
	private static int maxFlow(int source, int sink) {
		int minFlow, totalFlow = 0, i;
		
		while (bfs(source, sink)) {
			minFlow = 1001;
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
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, k, source, sink, temp, max, i, j;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		source = 0;
		sink = n + m + 1;
		temp = n + m + 2;
		
		adj = new ArrayList<>();
		for (i = 0; i < n + m + 3; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 1; i <= n; i++) {
			addAdj(source, i, 1);
		}
		addAdj(source, temp, k);
		for (i = 1; i <= n; i++) {
			addAdj(temp, i, k);
		}
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			max = Integer.parseInt(st.nextToken());
			for (j = 0; j < max; j++) {
				addAdj(i, n + Integer.parseInt(st.nextToken()), 1);
			}
		}
		for (i = n + 1; i <= n + m; i++) {
			addAdj(i, sink, 1);
		}
		
		prev = new int[n + m + 3];
		path = new Edge[n + m + 3];
		sb.append(maxFlow(source, sink));
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}