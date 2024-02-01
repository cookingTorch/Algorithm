import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static final int MAX_TIME = 100;
	
	private static int[] matched;
	private static boolean[] visited;
	private static ArrayList<ArrayList<Integer>> adj;
	
	private static boolean match(int time) {
		if (visited[time]) {
			return false;
		}
		visited[time] = true;
		for (int piece : adj.get(time)) {
			if (matched[piece] == -1 || match(matched[piece])) {
				matched[piece] = time;
				return true;
			}
		}
		return false;
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int n, m, start, end, num, piece, cnt, i, j, k;
		boolean[][] inAdj;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		adj = new ArrayList<>();
		for (i = 0; i <= MAX_TIME; i++) {
			adj.add(new ArrayList<>());
		}
		inAdj = new boolean[MAX_TIME + 1][n];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			num = Integer.parseInt(st.nextToken());
			for (j = 0; j < num; j++) {
				piece = Integer.parseInt(st.nextToken()) - 1;
				for (k = start + 1; k <= end; k++) {
					if (!inAdj[k][piece]) {
						adj.get(k).add(piece);
						inAdj[k][piece] = true;
					}
				}
			}
		}
		cnt = 0;
		matched = new int[n];
		visited = new boolean[MAX_TIME + 1];
		Arrays.fill(matched, -1);
		for (i = 1; i <= MAX_TIME; i++) {
			Arrays.fill(visited, false);
			if (match(i) && ++cnt == n) {
				break;
			}
		}
		if (cnt == n) {
			return i;
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			sb.append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
