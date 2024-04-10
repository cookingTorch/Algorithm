import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	private static final int MIN = Integer.MIN_VALUE;
	
	private static int bfs(int dest) {
		int size, i, j;
		int[] curr;
		int[][] distance;
		ArrayDeque<int[]> q;
		
		size = dest + 1;
		distance = new int[size][size];
		for (i = 0; i < size; i++) {
			distance[0][i] = MIN;
		}
		distance[1][0] = MIN;
		q = new ArrayDeque<>();
		q.add(new int[] {1, 0});
		while (!q.isEmpty()) {
			curr = q.poll();
			i = curr[0];
			j = curr[1];
			if (distance[i][j] + 1 < distance[i][i]) {
				distance[i][i] = distance[i][j] + 1;
				q.addLast(new int[] {i, i});
			}
			if (i + j == dest) {
				return distance[i][j] + 1 - MIN;
			}
			if (i + j < size && distance[i][j] + 1 < distance[i + j][j]) {
				distance[i + j][j] = distance[i][j] + 1;
				q.addLast(new int[] {i + j, j});
			}
			if (i - 1 == dest) {
				return distance[i][j] + 1 - MIN;
			}
			if (distance[i][j] + 1 < distance[i - 1][j]) {
				distance[i - 1][j] = distance[i][j] + 1;
				q.addLast(new int[] {i - 1, j});
			}
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print(bfs(Integer.parseInt(br.readLine())));
	}
}
