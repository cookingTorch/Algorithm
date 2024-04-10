import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	private static int bfs(int dest) {
		int i, j, dist;
		int[] curr;
		boolean[][] visited;
		ArrayDeque<int[]> q;
		
		visited = new boolean[dest][dest];
		q = new ArrayDeque<>();
		q.add(new int[] {1, 0, 0});
		while (!q.isEmpty()) {
			curr = q.pollFirst();
			i = curr[0];
			j = curr[1];
			dist = curr[2] + 1;
			if (i + j == dest) {
				return dist;
			}
			if (!visited[i][i]) {
				visited[i][i] = true;
				q.addLast(new int[] {i, i, dist});
			}
			if (i + j < dest && !visited[i + j][j]) {
				visited[i + j][j] = true;
				q.addLast(new int[] {i + j, j, dist});
			}
			if (i > 1 && !visited[i - 1][j]) {
				visited[i - 1][j] = true;
				q.addLast(new int[] {i - 1, j, dist});
			}
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print(bfs(Integer.parseInt(br.readLine())));
	}
}
