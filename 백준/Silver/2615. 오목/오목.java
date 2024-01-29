import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//import java.io.FileInputStream;

public class Main {
	private static boolean finish(int[][] board, int x, int y, int curr) {
		int i;
		
		if (y == 0 || board[x][y - 1] != curr) {
			for (i = 1; i <= 5; i++) {
				if (y + i >= 19 || board[x][y + i] != curr) {
					break;
				}
			}
			if (i == 5) {
				return true;
			}
		}
		
		if (x == 18 || y == 0 || board[x + 1][y - 1] != curr) {
			for (i = 1; i <= 5; i++) {
				if ((x - i < 0 || y + i >= 19) || board[x - i][y + i] != curr) {
					break;
				}
			}
			if (i == 5) {
				return true;
			}
		}
		
		if (x == 0 || y == 0 || board[x - 1][y - 1] != curr) {
			for (i = 1; i <= 5; i++) {
				if ((x + i >= 19 || y + i >= 19) || board[x + i][y + i] != curr) {
					break;
				}
			}
			if (i == 5) {
				return true;
			}
		}
		
		if (x == 0 || board[x - 1][y] != curr) {
			for (i = 1; i <= 5; i++) {
				if (x + i >= 19 || board[x + i][y] != curr) {
					break;
				}
			}
			if (i == 5) {
				return true;
			}
		}
		
		return false;
	}

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("Test5.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int i, j, curr;
		int[][] board;
		
		board = new int[19][19];
		for (i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < 19; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (i = 0; i < 19; i++) {
			for (j = 0; j < 19; j++) {
				if ((curr = board[i][j]) != 0) {
					if (finish(board, i, j, curr)) {
						sb.append(curr).append('\n').append(i + 1).append(' ').append(j + 1);
						System.out.println(sb);
						return;
					}
				}
			}
		}
		System.out.println(0);
	}
}