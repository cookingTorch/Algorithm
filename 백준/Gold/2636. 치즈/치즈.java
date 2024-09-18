import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	private static final char CHEESE = '1';
	private static final char WALL = '\0';
	private static final char LINE_BREAK = '\n';

	private static int qSize;
	private static int[] d;
	private static char[] map;
	private static boolean[] visited;
	private static ArrayDeque<Integer> q;
	private static ArrayDeque<Integer> q2;

	private static void bfs(int pos) {
		int i;
		int npos;

		q2.addLast(pos);
		while (!q2.isEmpty()) {
			pos = q2.pollFirst();
			for (i = 0; i < 4; i++) {
				npos = pos + d[i];
				if (visited[npos]) {
					continue;
				}
				visited[npos] = true;
				if (map[npos] == WALL) {
					continue;
				}
				if (map[npos] == CHEESE) {
					q.addLast(npos);
				} else {
					q2.addLast(npos);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int i;
		int row;
		int col;
		int size;
		int pos;
		int time;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = (Integer.parseInt(st.nextToken()) << 1) + 3;
		size = (row + 2) * col;
		map = new char[size];
		for (i = 1; i <= row; i++) {
			br.read(map, i * col + 2, col - 4);
			br.read();
		}
		d = new int[] {2, col, -2, -col};
		visited = new boolean[size];
		q = new ArrayDeque<>();
		q2 = new ArrayDeque<>();
		visited[1 * col + 2] = true;
		q.addLast(1 * col + 2);
		for (time = -1; !q.isEmpty(); time++) {
			qSize = q.size();
			for (i = 0; i < qSize; i++) {
				pos = q.pollFirst();
				bfs(pos);
			}
		}
		sb = new StringBuilder();
		System.out.print(sb.append(time).append(LINE_BREAK).append(time == 0 ? 0 : qSize).toString());
	}
}
