import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static int cnt;
	private static ArrayList<ArrayList<Integer>> adj;
	
	private static boolean isEarly(int node, int parent) {
		boolean early;
		
		early = false;
		for (int next : adj.get(node)) {
			if (next != parent && !isEarly(next, node)) {
				early = true;
			}
		}
		if (early) {
			cnt++;
		}
		return early;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, u, v, i;
		
		n = Integer.parseInt(br.readLine());
		adj = new ArrayList<>(n + 1);
		adj.add(null);
		for (i = 1; i <= n; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			adj.get(u).add(v);
			adj.get(v).add(u);
		}
		cnt = 0;
		isEarly(1, 0);
		System.out.print(cnt);
	}
}
