import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int[] matched;
	private static boolean[] visited;
	private static ArrayList<ArrayList<Integer>> adj;
	
	private static boolean match(int row) {
		if (visited[row]) {
			return false;
		}
		visited[row] = true;
		for (int col : adj.get(row)) {
			if (matched[col] == -1 || match(matched[col])) {
				matched[col] = row;
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, cnt, num, ans, i, j;
		char[] str;
		int[][] row;
		boolean[][] hole;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		hole = new boolean[n][m];
		for (i = 0; i < n; i++) {
			str = br.readLine().toCharArray();
			for (j = 0; j < m; j++) {
				if (str[j] == '*') {
					hole[i][j] = true;
				}
			}
		}
		adj = new ArrayList<>();
		row = new int[n][m];
		cnt = -1;
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				if (hole[i][j]) {
					if (j == 0 || !hole[i][j - 1]) {
						cnt++;
					}
					row[i][j] = cnt;
					adj.add(new ArrayList<>());
				}
			}
		}
		num = cnt;
		cnt = -1;
		for (j = 0; j < m; j++) {
			for (i = 0; i < n; i++) {
				if (hole[i][j]) {
					if (i == 0 || !hole[i - 1][j]) {
						cnt++;
					}
					adj.get(row[i][j]).add(cnt);
				}
			}
		}
		ans = 0;
		matched = new int[cnt + 1];
		visited = new boolean[num + 1];
		Arrays.fill(matched, -1);
		for (i = 0; i <= num; i++) {
			Arrays.fill(visited, false);
			if (match(i)) {
				ans++;
			}
		}
		System.out.print(ans);
	}
}
