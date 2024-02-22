import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
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
		
		int n, m, prev, ans, i;
		int[][] edge;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		edge = new int[m][3];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			edge[i][0] = Integer.parseInt(st.nextToken());
			edge[i][1] = Integer.parseInt(st.nextToken());
			edge[i][2] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(edge, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		root = new int[n + 1];
		ans = 0;
		prev = 0;
		for (i = 0; i < m; i++) {
			if (union(edge[i][0], edge[i][1])) {
				ans += (prev = edge[i][2]);
			}
		}
		System.out.print(ans - prev);
	}
}
