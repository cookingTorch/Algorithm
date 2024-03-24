import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int n, m;
	private static int[] unique, ans;
	private static StringBuilder sb;
	
	private static void dfs(int start, int depth) {
		int i;
		
		if (depth == m) {
			for (i = 0; i < m; i++) {
				sb.append(ans[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		for (i = start; i < n; i++) {
			ans[depth] = unique[i];
			dfs(i, depth + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int idx, i;
		int[] arr;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		unique = new int[n];
		unique[0] = arr[0];
		idx = 0;
		for (i = 1; i < n; i++) {
			if (arr[i] == unique[idx]) {
				continue;
			}
			unique[++idx] = arr[i];
		}
		n = idx + 1;
		sb = new StringBuilder();
		ans = new int[m];
		dfs(0, 0);
		System.out.print(sb);
	}
}
