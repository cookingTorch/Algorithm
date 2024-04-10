import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	
	private static int bfs(int dest) {
		int size, i, j, x, y;
		int[] curr;
		int[][] distance;
		ArrayDeque<int[]> q;
		
		size = 2 * dest;
		distance = new int[size][size];
		for (i = 1; i < size; i++) {
			for (j = 0; j < size; j++) {
				distance[i][j] = INF;
			}
		}
		distance[1][0] = 0;
		q = new ArrayDeque<>();
		q.add(new int[] {1, 0});
		while (!q.isEmpty()) {
			curr = q.poll();
			i = curr[0];
			j = curr[1];
			x = i;
			y = i;
			if (x < size && y < size && distance[i][j] + 1 < distance[x][y]) {
				distance[x][y] = distance[i][j] + 1;
				q.addLast(new int[] {x, y});
			}
			x += j;
			if (x == dest) {
				return distance[i][j] + 1;
			}
			y = j;
			if (x < size && y < size && distance[i][j] + 1 < distance[x][y]) {
				distance[x][y] = distance[i][j] + 1;
				q.addLast(new int[] {x, y});
			}
			x = i - 1;
			if (x == dest) {
				return distance[i][j] + 1;
			}
			if (x < size && y < size && distance[i][j] + 1 < distance[x][y]) {
				distance[x][y] = distance[i][j] + 1;
				q.addLast(new int[] {x, y});
			}
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print(bfs(Integer.parseInt(br.readLine())));
	}
}
