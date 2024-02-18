import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int n, m;
	private static int[] arr, ans;
	private static boolean[] visited;
	
	private static StringBuilder dfs(int depth) {
		int prev, i;
		StringBuilder sb;
		
		sb = new StringBuilder();
		if (depth == m) {
			for (i = 0; i < m; i++) {
				sb.append(ans[i]).append(' ');
			}
			return sb.append('\n');
		}
		prev = 0;
		for (i = 0; i < n; i++) {
			if (visited[i] || arr[i] == prev) {
				continue;
			}
			ans[depth] = arr[i];
			visited[i] = true;
			sb.append(dfs(depth + 1));
			visited[i] = false;
			prev = arr[i];
		}
		return sb;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		visited = new boolean[n];
		ans = new int[m];
		System.out.print(dfs(0));
	}
}
