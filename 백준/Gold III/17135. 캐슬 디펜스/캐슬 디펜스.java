import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int n, m, d;
	private static int[] archer;
	private static int[][] map;
	
	private static int[] bfs(int[] start) {
		int[] curr;
		Queue<int[]> q;
		
		q = new ArrayDeque<>();
		q.add(start);
		while (!q.isEmpty()) {
			curr = q.poll();
			if (curr[2] > d) {
				return null;
			}
			if (map[curr[0]][curr[1]] == 1) {
				return curr;
			}
			if (curr[1] > 0) {
				q.add(new int[] {curr[0], curr[1] - 1, curr[2] + 1});
			}
			if (curr[0] > 0) {
				q.add(new int[] {curr[0] - 1, curr[1], curr[2] + 1});
			}
			if (curr[1] < m - 1) {
				q.add(new int[] {curr[0], curr[1] + 1, curr[2] + 1});
			}
		}
		return null;
	}
	
	private static int solution() {
		int castle, kill, i;
		int[][] dead;
		
		kill = 0;
		for (castle = n; castle > 0; castle--) {
			dead = new int[3][];
			for (i = 0; i < 3; i++) {
				dead[i] = bfs(new int[] {castle - 1, archer[i], 1});
			}
			for (i = 0; i < 3; i++) {
				if (dead[i] != null) {
					if (map[dead[i][0]][dead[i][1]] == 1) {
						map[dead[i][0]][dead[i][1]] = 0;
						kill++;
					}
				}
			}
		}
		return kill;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int max, i, j;
		int[][] origin;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		origin = new int[n][m];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < m; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		max = 0;
		map = new int[n][];
		archer = new int[3];
		for (archer[0] = 0; archer[0] < m; archer[0]++) {
			for (archer[1] = archer[0] + 1; archer[1] < m; archer[1]++) {
				for (archer[2] = archer[1] + 1; archer[2] < m; archer[2]++) {
					for (i = 0; i < n; i++) {
						map[i] = Arrays.copyOf(origin[i], m);
					}
					max = Math.max(max, solution());
				}
			}
		}
		System.out.print(max);
	}
}
