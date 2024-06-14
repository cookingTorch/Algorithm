import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final class Edge implements Comparable<Edge> {
		int u;
		int v;
		int weight;
		
		Edge(int u, int v, int weight) {
			this.u = u;
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	private static int[] roots;
	
	private static final int find(int v) {
		if (roots[v] <= 0) {
			return v;
		}
		return roots[v] = find(roots[v]);
	}
	
	private static final boolean union(int u, int v) {
		int ru;
		int rv;
		
		ru = find(u);
		rv = find(v);
		if (ru == rv) {
			return false;
		}
		if (roots[ru] > roots[rv]) {
			roots[ru] = rv;
		} else {
			if (roots[ru] == roots[rv]) {
				roots[ru]--;
			}
			roots[rv] = ru;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int i;
		int min;
		int max;
		int initVal;
		Edge edge;
		BufferedReader br;
		StringTokenizer st;
		PriorityQueue<Edge> minPq;
		PriorityQueue<Edge> maxPq;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		st.nextToken();
		st.nextToken();
		initVal = Integer.parseInt(st.nextToken()) ^ 1;
		minPq = new PriorityQueue<>();
		maxPq = new PriorityQueue<>(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return Integer.compare(o2.weight, o1.weight);
			}
		});
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			edge = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) ^ 1);
			minPq.offer(edge);
			maxPq.offer(edge);
		}
		roots = new int[n + 1];
		min = initVal;
		for (i = 0; i < n - 1;) {
			edge = minPq.poll();
			if (union(edge.u, edge.v)) {
				min += edge.weight;
				i++;
			}
		}
		roots = new int[n + 1];
		max = initVal;
		for (i = 0; i < n - 1;) {
			edge = maxPq.poll();
			if (union(edge.u, edge.v)) {
				max += edge.weight;
				i++;
			}
		}
		System.out.print(max * max - min * min);
	}
}
