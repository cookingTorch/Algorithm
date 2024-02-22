import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static final boolean A = true;
	private static final boolean B = false;
	
	private static int n, sum, cnt;
	private static int[] populations;
	private static boolean[] districts, visited;
	private static ArrayList<ArrayList<Integer>> adj;
	
	private static void dfs(int node, boolean district) {
		if (visited[node] || districts[node] != district) {
			return;
		}
		visited[node] = true;
		cnt++;
		for (int next : adj.get(node)) {
			dfs(next, district);
		}
	}
	
	private static int calc(int bit) {
		int cntA, startA, populationA, i;
		
		cntA = 0;
		startA = 0;
		populationA = 0;
		for (i = 0; i < n; i++, bit >>= 1) {
			if ((bit & 1) == 1) {
				districts[i] = A;
				cntA++;
				startA = i;
				populationA += populations[i];
			} else {
				districts[i] = B;
			}
		}
		visited = new boolean[n];
		cnt = 0;
		dfs(startA, A);
		if (cnt != cntA) {
			return INF;
		}
		cnt = 0;
		dfs(n - 1, B);
		if (cnt != n - cntA) {
			return INF;
		}
		return Math.abs(sum - populationA - populationA);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int bit, max, num, node, min, i, j;
		
		n = Integer.parseInt(br.readLine());
		sum = 0;
		populations = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			populations[i] = Integer.parseInt(st.nextToken());
			sum += populations[i];
		}
		adj = new ArrayList<>();
		for (i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			for (j = 0; j < num; j++) {
				node = Integer.parseInt(st.nextToken()) - 1;
				adj.get(i).add(node);
				adj.get(node).add(i);
			}
		}
		min = INF;
		max = 1 << (n - 1);
		districts = new boolean[n];
		for (bit = 1; bit < max; bit++) {
			min = Math.min(min, calc(bit));
		}
		System.out.print(min == INF ? -1 : min);
	}
}
