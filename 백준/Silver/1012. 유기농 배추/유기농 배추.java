import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t, testCase, m, n, k, x, y, cnt, i;
		int[] start, curr;
		int[][] field;
		boolean[][] visited;
		Queue<int[]> q, bfsQ;
		
		// 테스트 케이스
		testCase = Integer.parseInt(br.readLine());
		for (t = 0; t < testCase; t++) {
			
			// 배추 입력
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			field = new int[m][n];
			q = new LinkedList<>();
			for (i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				field[x][y] = 1;
				q.add(new int[] {x, y});
			}
			
			// 각 배추의 위치부터 BFS
			cnt = 0;
			bfsQ = new LinkedList<>();
			visited = new boolean[m][n];
			while (!q.isEmpty()) {
				start = q.poll();
				if (!visited[start[0]][start[1]]) {
					cnt++;
					bfsQ.add(start);
					while (!bfsQ.isEmpty()) {
						curr = bfsQ.poll();
						if (curr[0] > 0 && field[curr[0] - 1][curr[1]] == 1 && !visited[curr[0] - 1][curr[1]]) {
							visited[curr[0] - 1][curr[1]] = true;
							bfsQ.add(new int[] {curr[0] - 1, curr[1]});
						}
						if (curr[0] < m - 1 && field[curr[0] + 1][curr[1]] == 1 && !visited[curr[0] + 1][curr[1]]) {
							visited[curr[0] + 1][curr[1]] = true;
							bfsQ.add(new int[] {curr[0] + 1, curr[1]});
						}
						if (curr[1] > 0 && field[curr[0]][curr[1] - 1] == 1 && !visited[curr[0]][curr[1] - 1]) {
							visited[curr[0]][curr[1] - 1] = true;
							bfsQ.add(new int[] {curr[0], curr[1] - 1});
						}
						if (curr[1] < n - 1 && field[curr[0]][curr[1] + 1] == 1 && !visited[curr[0]][curr[1] + 1]) {
							visited[curr[0]][curr[1] + 1] = true;
							bfsQ.add(new int[] {curr[0], curr[1] + 1});
						}
					}
				}
			}
			
			// 출력
			sb.append(cnt).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}