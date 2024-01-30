import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[] permutation;
	private static boolean[] visited;
	
	private static void dfs(int n, int m, int depth, StringBuilder sb) {
		int i;
		
		if (depth == m) {
			for (int num : permutation) {
				sb.append(num).append(' ');
			}
			sb.append('\n');
			return;
		}
		for (i = 1; i <= n; i++) {
			if (!visited[i]) {
				permutation[depth] = i;
				visited[i] = true;
				dfs(n, m, depth + 1, sb);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		permutation = new int[m];
		visited = new boolean[n + 1];
		dfs(n, m, 0, sb);
		System.out.print(sb);
	}
}
