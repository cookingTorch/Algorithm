import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static int[] ans;
	private static ArrayList<ArrayList<Integer>> adj;
	
	private static void prop(int node) {
		for (int child : adj.get(node)) {
			ans[child] += ans[node];
			prop(child);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		st.nextToken();
		adj = new ArrayList<>(n + 1);
		adj.add(null);
		adj.add(new ArrayList<>());
		for (i = 2; i <= n; i++) {
			adj.add(new ArrayList<>());
			adj.get(Integer.parseInt(st.nextToken())).add(i);
		}
		ans = new int[n + 1];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			ans[Integer.parseInt(st.nextToken())] += Integer.parseInt(st.nextToken());
		}
		prop(1);
		for (i = 1; i <= n; i++) {
			sb.append(ans[i]).append(' ');
		}
		System.out.print(sb);
	}
}
