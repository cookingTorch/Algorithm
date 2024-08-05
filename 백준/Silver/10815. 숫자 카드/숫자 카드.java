import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	private static final char ONE = '1';
	private static final char ZERO = '0';
	private static final char SPACE = ' ';

	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int i;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		HashSet<Integer> set;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		set = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		m = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < m; i++) {
			if (set.contains(Integer.parseInt(st.nextToken()))) {
				sb.append(ONE).append(SPACE);
			} else {
				sb.append(ZERO).append(SPACE);
			}
		}
		System.out.print(sb);
	}
}
