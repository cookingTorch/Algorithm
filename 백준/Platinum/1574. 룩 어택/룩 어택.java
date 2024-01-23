import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static boolean[] visited;
	private static int row[];
	private static ArrayList<ArrayList<Integer>> adj;
	
	private static boolean match(int node) {
		for (int col : adj.get(node)) {
			if (visited[col]) {
				continue;
			}
			visited[col] = true;
			if (row[col] == -1 || match(row[col])) {
				row[col] = node;
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int r, c, n, cnt, i, j;
		boolean[][] board;
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		board = new boolean[r][c];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			board[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
		}
		adj = new ArrayList<>();
		for (i = 0; i < r; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < r; i++) {
			for (j = 0; j < c; j++) {
				if (!board[i][j]) {
					adj.get(i).add(j);
				}
			}
		}
		visited = new boolean[c];
		row = new int[c];
		Arrays.fill(row, -1);
		cnt = 0;
		for (i = 0; i < r; i++) {
			Arrays.fill(visited, false);
			if (match(i)) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}