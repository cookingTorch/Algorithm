import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int n;
	private static int[] works;
	private static int[][] adj;
	private static boolean[] visited;
	
	private static boolean match(int employee) {
		int i, idx;
		
		for (i = 0; i < adj[employee].length; i++) {
			idx = adj[employee][i];
			if (visited[idx] || (works[idx] != -1 && (works[idx] == employee - n || works[idx] == employee + n))) {
				continue;
			}
			visited[idx] = true;
			if (works[idx] == -1 || match(works[idx])) {
				works[idx] = employee;
				return true;
			}
		}
		return false;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int m, max, work, ans, i, j, k;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		adj = new int[2 * n][];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			max = Integer.parseInt(st.nextToken());
			adj[i] = new int[max];
			adj[i + n] = new int[max];
			for (j = 0; j < max; j++) {
				work = Integer.parseInt(st.nextToken()) - 1;
				adj[i][j] = work;
				adj[i + n][j] = work;
			}
		}
		
		works = new int[m];
		Arrays.fill(works, -1);
		ans = 0;
		for (i = 0; i < 2 * n; i++) {
			visited = new boolean[m];
			if (match(i)) {
				ans++;
			}
		}
		
		sb.append(ans);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}