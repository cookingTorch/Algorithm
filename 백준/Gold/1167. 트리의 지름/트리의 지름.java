import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	private static class Edge {
		int to, weight;
		
		Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
	
	private static int max;
	private static boolean[] visited;
	private static ArrayList<LinkedList<Edge>> adj;
	
	private static int dfs(int node) {
		int max1, max2, dist;
		
		visited[node] = true;
		max1 = 0;
		max2 = 0;
		for (Edge edge : adj.get(node)) {
			if (!visited[edge.to]) {
				dist = dfs(edge.to) + edge.weight;
				if (dist >= max1) {
					max2 = max1;
					max1 = dist;
				} else if (dist > max2) {
					max2 = dist;
				}
			}
		}
		max = Math.max(max, max1 + max2);
		return max1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int v, p1, p2, i;
		
		v = Integer.parseInt(br.readLine());
		adj = new ArrayList<>();
		for (i = 0; i <= v; i++) {
			adj.add(new LinkedList<>());
		}
		for (i = 0; i < v; i++) {
			st = new StringTokenizer(br.readLine());
			p1 = Integer.parseInt(st.nextToken());
			while ((p2 = Integer.parseInt(st.nextToken())) != -1) {
				adj.get(p1).add(new Edge(p2, Integer.parseInt(st.nextToken())));
			}
		}
		max = 0;
		visited = new boolean[v + 1];
		dfs(1);
		System.out.print(max);
	}

}
