import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int SIZE = 10;
	private static final int MAX = 100;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int paper, i, j, k, x, y, cnt;
		boolean[][] map;
		
		paper = Integer.parseInt(br.readLine());
		map = new boolean[MAX][MAX];
		for (i = 0; i < paper; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			for (j = 0; j < SIZE; j++) {
				for (k = 0; k < SIZE; k++) {
					map[x + j][y + k] = true;
				}
			}
		}
		cnt = 0;
		for (i = 0; i < MAX; i++) {
			for (j = 0; j < MAX; j++) {
				if (map[i][j]) {
					cnt++;
				}
			}
		}
		System.out.print(cnt);
	}
}
