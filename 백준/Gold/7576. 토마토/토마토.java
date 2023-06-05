import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static int n, m;
	private static int[][] box;
	private static Queue<int[]> cell = new LinkedList<>();
	
	private static void bfs(int[] node) {
		if (node[0] > 0 && box[node[0] - 1][node[1]] == 0) {
			box[node[0] - 1][node[1]] = box[node[0]][node[1]] + 1;
			cell.add(new int[] {node[0] - 1, node[1]});
		}
		if (node[1] > 0 && box[node[0]][node[1] - 1] == 0) {
			box[node[0]][node[1] - 1] = box[node[0]][node[1]] + 1;
			cell.add(new int[] {node[0], node[1] - 1});
		}
		if (node[0] < n - 1 && box[node[0] + 1][node[1]] == 0) {
			box[node[0] + 1][node[1]] = box[node[0]][node[1]] + 1;
			cell.add(new int[] {node[0] + 1, node[1]});
		}
		if (node[1] < m - 1 && box[node[0]][node[1] + 1] == 0) {
			box[node[0]][node[1] + 1] = box[node[0]][node[1]] + 1;
			cell.add(new int[] {node[0], node[1] + 1});
		}
		if (!cell.isEmpty()) {
			bfs(cell.poll());
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int i, j, tomato, max = 1;
		boolean flag = true;
		
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		box = new int[n][m];
		
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < m; j++) {
				tomato = Integer.parseInt(st.nextToken());
				if (tomato == 1) {
					cell.add(new int[] {i, j});
				}
				box[i][j] = tomato;
			}
		}
		
		if (!cell.isEmpty()) {
			bfs(cell.poll());
		}
		
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				if (box[i][j] > max) {
					max = box[i][j];
				}
				if (box[i][j] == 0) {
					flag = false;
					max = 0;
					break;
				}
			}
			if (!flag) {
				break;
			}
		}
		
		sb.append(max - 1);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}