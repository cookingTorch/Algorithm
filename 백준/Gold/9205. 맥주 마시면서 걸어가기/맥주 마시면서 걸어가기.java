import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static final int CONNECTED = 1000;
	private static final String HAPPY = "happy";
	private static final String SAD = "sad";
	
	private static int end;
	private static boolean flag;
	private static boolean[] visited;
	private static ArrayList<ArrayList<Integer>> adj;
	
	private static void dfs(int node) {
		if (visited[node] || flag) {
			return;
		}
		if (node == end) {
			flag = true;
			return;
		}
		visited[node] = true;
		for (int next : adj.get(node)) {
			dfs(next);
		}
	}
	
	private static boolean solution(BufferedReader br, StringTokenizer st) throws IOException {
		int n, distance, i, j;
		int[][] stores;
		
		n = Integer.parseInt(br.readLine()) + 2;
		stores = new int[n][];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			stores[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		}
		adj = new ArrayList<>(n);
		for (i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < n; i++) {
			for (j = i + 1; j < n; j++) {
				distance = Math.abs(stores[i][0] - stores[j][0]) + Math.abs(stores[i][1] - stores[j][1]);
				if (distance <= CONNECTED) {
					adj.get(i).add(j);
					adj.get(j).add(i);
				}
			}
		}
		end = n - 1;
		flag = false;
		visited = new boolean[n];
		dfs(0);
		return flag;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			sb.append(solution(br, st) ? HAPPY : SAD).append('\n');
		}
		System.out.print(sb);
	}
}
