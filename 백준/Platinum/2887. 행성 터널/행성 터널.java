import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final int MIN = Integer.MIN_VALUE;
	
	private static class Edge implements Comparable<Edge> {
		int u, v, weight;
		
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
	
	private static int[] root;
	
	private static boolean union(int u, int v) {
		int ru, rv;
		
		if ((ru = find(u)) == (rv = find(v))) {
			return false;
		}
		if (root[ru] > root[rv]) {
			root[ru] = rv;
		} else {
			if (root[ru] == root[rv]) {
				root[ru]--;
			}
			root[rv] = ru;
		}
		return true;
	}
	
	private static int find(int u) {
		if (root[u] <= 0) {
			return u;
		}
		return root[u] = find(root[u]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, ans, i;
		int[][] planets;
		Edge edge;
		PriorityQueue<Edge> pq;
		
		n = Integer.parseInt(br.readLine());
		planets = new int[n + 1][4];
		planets[0] = new int[] {MIN, MIN, MIN, MIN};
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			planets[i][0] = Integer.parseInt(st.nextToken());
			planets[i][1] = Integer.parseInt(st.nextToken());
			planets[i][2] = Integer.parseInt(st.nextToken());
			planets[i][3] = i;
		}
		pq = new PriorityQueue<>();
		Arrays.sort(planets, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		});
		for (i = 1; i < n; i++) {
			pq.add(new Edge(planets[i][3], planets[i + 1][3], planets[i + 1][0] - planets[i][0]));
		}
		Arrays.sort(planets, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1], o2[1]);
			}
		});
		for (i = 1; i < n; i++) {
			pq.add(new Edge(planets[i][3], planets[i + 1][3], planets[i + 1][1] - planets[i][1]));
		}
		Arrays.sort(planets, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		for (i = 1; i < n; i++) {
			pq.add(new Edge(planets[i][3], planets[i + 1][3], planets[i + 1][2] - planets[i][2]));
		}
		root = new int[n + 1];
		ans = 0;
		for (i = 0; i < n - 1;) {
			edge = pq.poll();
			if (union(edge.u, edge.v)) {
				ans += edge.weight;
				i++;
			}
		}
		System.out.print(ans);
	}
}