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
	private static ArrayList<LinkedList<Edge>> adj;
	
	private static int dfs(int node) {
		int max1, max2, dist;
		
		max1 = 0;
		max2 = 0;
		for (Edge edge : adj.get(node)) {
			dist = dfs(edge.to) + edge.weight;
			if (dist >= max1) {
				max2 = max1;
				max1 = dist;
			} else if (dist > max2) {
				max2 = dist;
			}
		}
		max = Math.max(max, max1 + max2);
		return max1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, i;
		
		n = Integer.parseInt(br.readLine());
		adj = new ArrayList<>();
		for (i = 0; i <= n; i++) {
			adj.add(new LinkedList<>());
		}
		for (i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			adj.get(Integer.parseInt(st.nextToken())).add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			
		}
		dfs(1);
		System.out.print(max);
	}
}
