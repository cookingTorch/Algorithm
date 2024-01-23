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
		
		int n, m, cnt, ans, start, i, j;
		int[][] row;
		boolean[][] board;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		board = new boolean[n][n];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			board[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
		}
		adj = new ArrayList<>();
		row = new int[n][n];
		cnt = -1;
		for (start = 0; start < n; start++) {
			i = 0;
			j = start;
			while (i < n && j < n) {
				if ((i == 0 || board[i - 1][j - 1] == true) && board[i][j] == false) {
					adj.add(new ArrayList<>());
					cnt++;
				}
				row[i][j] = cnt;
				i++;
				j++;
			}
		}
		for (start = 1; start < n; start++) {
			i = start;
			j = 0;
			while (i < n && j < n) {
				if ((j == 0 || board[i - 1][j - 1] == true) && board[i][j] == false) {
					adj.add(new ArrayList<>());
					cnt++;
				}
				row[i][j] = cnt;
				i++;
				j++;
			}
		}
		cnt = -1;
		for (start = 0; start < n; start++) {
			i = 0;
			j = start;
			while (i < n && j >= 0) {
				if ((i == 0 || board[i - 1][j + 1] == true) && board[i][j] == false) {
					cnt++;
				}
				if (board[i][j] == false) {
					adj.get(row[i][j]).add(cnt);
				}
				i++;
				j--;
			}
		}
		for (start = 1; start < n; start++) {
			i = start;
			j = n - 1;
			while (i < n && j >= 0) {
				if ((j == n - 1 || board[i - 1][j + 1] == true) && board[i][j] == false) {
					cnt++;
				}
				if (board[i][j] == false) {
					adj.get(row[i][j]).add(cnt);
				}
				i++;
				j--;
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