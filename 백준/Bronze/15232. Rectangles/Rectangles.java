import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final char STAR = '*';
	private static final char LINE_BREAK = '\n';

	public static void main(String[] args) throws IOException {
		int r;
		int c;
		int i;
		int max;
		int size;
		char[] map;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		r = Integer.parseInt(br.readLine());
		c = Integer.parseInt(br.readLine());
		size = c + 1;
		max = r * size;
		map = new char[max];
		for (i = 0; i < c; i++) {
			map[i] = STAR;
		}
		map[c] = LINE_BREAK;
		for (i = size; i < max; i += size) {
			System.arraycopy(map, 0, map, i, size);
		}
		System.out.print(map);
	}
}
