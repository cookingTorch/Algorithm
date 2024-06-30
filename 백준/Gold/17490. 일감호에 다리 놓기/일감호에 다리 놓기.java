import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final String NO = "NO";
	private static final String YES = "YES";
	
	private static final class Edge implements Comparable<Edge> {
		int v;
		long weight;
		
		Edge(int v, long weight) {
			this.v = v;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
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
		int u;
		int v;
		int thr;
		int temp;
		int island;
		long k;
		boolean[] constructions;
		Edge edge;
		BufferedReader br;
		StringTokenizer st;
		PriorityQueue<Edge> pq;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		if (m < 2) {
			System.out.print(YES);
			return;
		}
		k = Long.parseLong(st.nextToken());
		pq = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			pq.offer(new Edge(i, Integer.parseInt(st.nextToken())));
		}
		constructions = new boolean[n + 1];
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			if (u > v) {
				temp = u;
				u = v;
				v = temp;
			}
			if (u == 1 && v == n) {
				constructions[n] = true;
			} else {
				constructions[u] = true;
			}
		}
		thr = n;
		roots = new int[n + 2];
		for (i = 1; i < n; i++) {
			if (constructions[i]) {
				continue;
			}
			union(i, i + 1);
			thr--;
		}
		if (!constructions[n] && union(n, 1)) {
			thr--;
		}
		island = n + 1;
		for (i = 0; i < thr;) {
			edge = pq.poll();
			if (union(island, edge.v)) {
				if ((k -= edge.weight) < 0) {
					break;
				}
				i++;
			}
		}
		System.out.print(i == thr ? YES : NO);
	}
}
