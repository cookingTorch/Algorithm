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
		
		int n, k, row, col, cnt, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		adj = new ArrayList<>();
		for (i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			row = Integer.parseInt(st.nextToken()) - 1;
			col = Integer.parseInt(st.nextToken()) - 1;
			adj.get(row).add(col);
		}
		matched = new int[n];
		visited = new boolean[n];
		Arrays.fill(matched, -1);
		cnt = 0;
		for (i = 0; i < n; i++) {
			Arrays.fill(visited, false);
			if (match(i)) {
				cnt++;
			}
		}
		System.out.print(cnt);
	}
}
