import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int r, c, cnt;
	private static int[] cheese;
	private static boolean[] visited;
	private static Queue<Integer> q;
	
	private static boolean bfs() {
		int curr;
		boolean flag;
		
		cnt = 0;
		flag = false;
		visited = new boolean[r * c];
		q = new ArrayDeque<>();
		q.add(0);
		visited[0] = true;
		while (!q.isEmpty()) {
			curr = q.poll();
			if (cheese[curr] == 1) {
				cheese[curr] = 0;
				cnt++;
				flag = true;
			} else {
				if (curr % c != c - 1 && !visited[curr + 1]) {
					q.add(curr + 1);
					visited[curr + 1] = true;
				}
				if (curr + c < r * c && !visited[curr + c]) {
					q.add(curr + c);
					visited[curr + c] = true;
				}
				if (curr % c != 0 && !visited[curr - 1]) {
					q.add(curr - 1);
					visited[curr - 1] = true;
				}
				if (curr - c >= 0 && !visited[curr - c]) {
					q.add(curr - c);
					visited[curr - c] = true;
				}
			}
		}
		return flag;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int ans, i, j;
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		cheese = new int[r * c];
		for (i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < c; j++) {
				cheese[i * c + j] = Integer.parseInt(st.nextToken());
			}
		}
		for (i = 0, ans = 0; bfs(); i++, ans = cnt);
		sb.append(i).append('\n').append(ans);
		System.out.print(sb);
	}
}
