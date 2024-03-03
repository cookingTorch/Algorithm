import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final int ISLAND = -1;
	private static final int WATER = -2;
	
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
	
	private static int n, m;
	private static int[] roots;
	private static int[][] map;
	private static PriorityQueue<Edge> pq;
	
	private static void dfs(int x, int y, int num) {
		if (x < 0 || x >= n || y < 0 || y >= m || map[x][y] != ISLAND) {
			return;
		}
		map[x][y] = num;
		dfs(x + 1, y, num);
		dfs(x, y + 1, num);
		dfs(x - 1, y, num);
		dfs(x, y - 1, num);
	}
	
	private static int numbering() {
		int i, j, num;
		
		num = 0;
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				if (map[i][j] == ISLAND) {
					dfs(i, j, ++num);
				}
			}
		}
		return num;
	}
	
	private static void buildBridge(int x, int y, int num) {
		int i;
		
		if (x - 2 >= 0 && map[x - 1][y] == WATER && map[x - 2][y] == WATER) {
			for (i = x - 2; i >= 0; i--) {
				if (map[i][y] != WATER) {
					pq.add(new Edge(num, map[i][y], x - i - 1));
					break;
				}
			}
		}
		if (x + 2 < n && map[x + 1][y] == WATER && map[x + 2][y] == WATER) {
			for (i = x + 2; i < n; i++) {
				if (map[i][y] != WATER) {
					pq.add(new Edge(num, map[i][y], i - x - 1));
					break;
				}
			}
		}
		if (y - 2 >= 0 && map[x][y - 1] == WATER && map[x][y - 2] == WATER) {
			for (i = y - 2; i >= 0; i--) {
				if (map[x][i] != WATER) {
					pq.add(new Edge(num, map[x][i], y - i - 1));
					break;
				}
			}
		}
		if (y + 2 < m && map[x][y + 1] == WATER && map[x][y + 2] == WATER) {
			for (i = y + 2; i < m; i++) {
				if (map[x][i] != WATER) {
					pq.add(new Edge(num, map[x][i], i - y - 1));
					break;
				}
			}
		}
	}
	
	private static int find(int v) {
		if (roots[v] <= 0) {
			return v;
		}
		return roots[v] = find(roots[v]);
	}
	
	private static boolean union(int u, int v) {
		int ru, rv;
		
		if ((ru = find(u)) == (rv = find(v))) {
			return false;
		}
		if (ru < rv) {
			roots[rv] = ru;
		} else {
			if (ru == rv) {
				roots[rv]--;
			}
			roots[ru] = rv;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int i, j, islands, ans;
		Edge edge;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()) - 2;
			}
		}
		islands = numbering();
		pq = new PriorityQueue<>();
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				if (map[i][j] != WATER) {
					buildBridge(i, j, map[i][j]);
				}
			}
		}
		roots = new int[islands + 1];
		ans = 0;
		for (i = 0; !pq.isEmpty() && i < islands - 1;) {
			edge = pq.poll();
			if (union(edge.u, edge.v)) {
				ans += edge.weight;
				i++;
			}
		}
		if (i < islands - 1) {
			System.out.print("-1");
			return;
		}
		System.out.print(ans);
	}
}
