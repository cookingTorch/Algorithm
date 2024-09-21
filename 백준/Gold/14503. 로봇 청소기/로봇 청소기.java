import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final char WALL = '1';
	private static final char EMPTY = '0';

	private static int clean(int pos, int d, int[] delta, char[] map) {
		int i;
		int cnt;

		map[pos]--;
		cnt = 1;
		for (;;) {
			for (i = 4; i != 0 && map[pos + delta[d = (d + 3) & 3]] != EMPTY; i--);
			if (i != 0) {
				map[pos += delta[d]]--;
				cnt++;
			} else if (map[pos += delta[(d + 2) & 3]] == WALL) {
				break;
			}
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int r;
		int c;
		int d;
		char[] map;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken()) << 1;
		st = new StringTokenizer(br.readLine(), " ", false);
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		map = new char[n * m];
		br.read(map, 0, n * m - 1);
		System.out.print(clean(r * m + (c << 1), d, new int[] {-m, 2, m, -2}, map));
	}
}
