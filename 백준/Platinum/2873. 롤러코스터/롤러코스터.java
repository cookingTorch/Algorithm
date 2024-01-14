import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static char prev;
	private static int minX, minY, x, y, r, c;
	
	private static void right(StringBuilder sb) {
		sb.append('R');
		prev = 'R';
		y++;
	}
	
	private static void left(StringBuilder sb) {
		sb.append('L');
		prev = 'L';
		y--;
	}
	
	private static void up(StringBuilder sb) {
		sb.append('U');
		prev = 'U';
		x--;
	}
	
	private static void down(StringBuilder sb) {
		sb.append('D');
		prev = 'D';
		x++;
	}
	
	private static void odd(StringBuilder sb) {
		if (r % 2 == 1) {
			while (x < r - 1 || y < c - 1) {
				if ((y == c - 1 && x % 2 == 0) || (y == 0 && x % 2 == 1)) {
					down(sb);
				} else if (x % 2 == 0) {
					right(sb);
				} else {
					left(sb);
				}
			}
		} else {
			while (x < r - 1 || y < c - 1) {
				if ((x == r - 1 && y % 2 == 0) || (x == 0 && y % 2 == 1)) {
					right(sb);
				} else if (y % 2 == 0) {
					down(sb);
				} else {
					up(sb);
				}
			}
		}
	}
	
	private static void even(StringBuilder sb) {
		int end;
		
		end = minX;
		if (minX % 2 == 0) {
			end++;
		}
		prev = 'D';
		while (x < r - 1 || y < c - 1) {
			if (x == end - 1) {
				prev = 'R';
				while (x < end || y < c - 1) {
					if (prev == 'R') {
						if (x < end) {
							if (x + 1 == minX && y == minY) {
								right(sb);
							} else {
								down(sb);
							}
						} else {
							if (x - 1 == minX && y == minY) {
								right(sb);
							} else {
								up(sb);
							}
						}
					} else {
						right(sb);
					}
				}
				prev = 'R';
			} else {
				if ((y == 0 && prev == 'L') || (y == c - 1 && prev == 'R')) {
					down(sb);
				} else if (y == 0 || prev == 'R') {
					right(sb);
				} else {
					left(sb);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int min, joy, i, j;
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		min = 1001;
		minX = 0;
		minY = 0;
		for (i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < c; j++) {
				joy = Integer.parseInt(st.nextToken());
				if ((i + j) % 2 == 1 && joy < min) {
					min = joy;
					minX = i;
					minY = j;
				}
			}
		}
		x = 0;
		y = 0;
		if (r % 2 == 1 || c % 2 == 1) {
			odd(sb);
		} else {
			even(sb);
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}