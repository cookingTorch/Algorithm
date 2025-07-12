import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final String DELIM = " ";

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
			return weight - o.weight;
		}
	}

	private static int[] roots;

	private static int find(int v) {
		if (roots[v] <= 0) {
			return v;
		}
		return roots[v] = find(roots[v]);
	}

	private static boolean union(int u, int v) {
		if ((u = find(u)) == (v = find(v))) {
			return false;
		}
		if (roots[u] > roots[v]) {
			roots[u] = v;
		} else {
			if (roots[u] == roots[v]) {
				roots[u]--;
			}
			roots[v] = u;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		int v;
		int e;
		int cnt;
		int ans;
		Edge edge;
		PriorityQueue<Edge> pq;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), DELIM, false);
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		pq = new PriorityQueue<>(e);
		while (e-- > 0) {
			st = new StringTokenizer(br.readLine(), DELIM, false);
			pq.offer(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		roots = new int[v + 1];
		ans = 0;
		for (cnt = 1; cnt < v;) {
			edge = pq.poll();
			if (union(edge.u, edge.v)) {
				ans += edge.weight;
				cnt++;
			}
		}
		System.out.print(ans);
	}
}
