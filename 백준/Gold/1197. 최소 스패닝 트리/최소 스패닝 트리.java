import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	private static int[] parent;
	
	private static boolean union(int node1, int node2) {
		int parent1, parent2;
		
		if ((parent1 = find(node1)) == (parent2 = find(node2))) {
			return false;
		}
		parent[parent2] = parent[parent1];
		return true;
	}
	
	private static int find(int node) {
		if (parent[node] == node) {
			return node;
		}
		return parent[node] = find(parent[node]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int v, e, ans, i;
		int[][] edge;
		
		st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		edge = new int[e][3];
		for (i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			edge[i][0] = Integer.parseInt(st.nextToken()) - 1;
			edge[i][1] = Integer.parseInt(st.nextToken()) - 1;
			edge[i][2] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(edge, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		parent = new int[v];
		for (i = 0; i < v; i++) {
			parent[i] = i;
		}
		ans = 0;
		for (i = 0; i < e; i++) {
			if (union(edge[i][0], edge[i][1])) {
				ans += edge[i][2];
			}
		}
		System.out.print(ans);
	}
}
