import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static boolean[] visited;
	private static int[] src;
	private static ArrayList<ArrayList<Integer>> adj;
	
	private static boolean match(int node) {
		for (int dest : adj.get(node)) {
			if (visited[dest]) {
				continue;
			}
			visited[dest] = true;
			if (src[dest] == -1 || match(src[dest])) {
				src[dest] = node;
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, cnt, ans, i, j;
		int[][] row, col;
		char[][] board;
		String str;
		
		n = Integer.parseInt(br.readLine());
		board = new char[n][n];
		for (i = 0; i < n; i++) {
			str = br.readLine();
			for (j = 0; j < n; j++) {
				board[i][j] = str.charAt(j);
			}
		}
		adj = new ArrayList<>();
		row = new int[n][n];
		cnt = -1;
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				if ((j == 0 || board[i][j - 1] == 'X') && board[i][j] == '.') {
					adj.add(new ArrayList<>());
					cnt++;
				}
				row[i][j] = cnt;
			}
		}
		col = new int[n][n];
		cnt = -1;
		for (j = 0; j < n; j++) {
			for (i = 0; i < n; i++) {
				if ((i == 0 || board[i - 1][j] == 'X') && board[i][j] == '.') {
					cnt++;
				}
				col[i][j] = cnt;
				if (board[i][j] == '.') {
					adj.get(row[i][j]).add(col[i][j]);
				}
			}
		}
		src = new int[cnt + 1];
		visited = new boolean[cnt + 1];
		Arrays.fill(src, -1);
		ans = 0;
		for (i = 0; i < adj.size(); i++) {
			Arrays.fill(visited, false);
			if (match(i)) {
				ans++;
			}
		}
		sb.append(ans);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}