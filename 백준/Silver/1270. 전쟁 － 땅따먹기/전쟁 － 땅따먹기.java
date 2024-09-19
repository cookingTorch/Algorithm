import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	private static final int MAX_T = 100_000;
	private static final char LINE_BREAK = '\n';
	private static final char[] SYJKGW = {'S', 'Y', 'J', 'K', 'G', 'W', '\n'};

	private static int[] arr;
	private static StringBuilder sb;
	private static BufferedReader br;
	private static HashMap<Long, Integer> map;

	private static void solution() throws IOException {
		int t;
		int i;
		int cnt;
		int half;
		long num;
		StringTokenizer st;

		map.clear();
		st = new StringTokenizer(br.readLine());
		t = Integer.parseInt(st.nextToken());
		half = t >> 1;
		for (i = 0; i < t; i++) {
			num = Long.parseLong(st.nextToken());
			if (map.containsKey(num)) {
				cnt = map.get(num) + 1;
				if (cnt > half) {
					sb.append(num).append(LINE_BREAK);
					return;
				}
				map.put(num, cnt);
			} else {
				map.put(num, 1);
			}
		}
		sb.append(SYJKGW);
	}

	public static void main(String[] args) throws IOException {
		int n;

		arr = new int[MAX_T];
		map = new HashMap<>();
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while( n-- > 0) {
			solution();
		}
		System.out.print(sb);
	}
}
