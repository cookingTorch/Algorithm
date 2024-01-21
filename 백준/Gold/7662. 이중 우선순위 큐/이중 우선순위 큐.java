import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	private static void solution(BufferedReader br, StringBuilder sb, StringTokenizer st) throws IOException {
		int k, n, key, i;
		TreeMap<Integer, Integer> tm;
		
		k = Integer.parseInt(br.readLine());
		tm = new TreeMap<>();
		for (i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			if (st.nextToken().charAt(0) == 'I') {
				n = Integer.parseInt(st.nextToken());
				if (tm.containsKey(n)) {
					tm.put(n, tm.get(n) + 1);
				} else {
					tm.put(n, 1);
				}
			} else {
				if (tm.isEmpty()) {
					st.nextToken();
					continue;
				} else if (Integer.parseInt(st.nextToken()) == 1) {
					key = tm.lastKey();
				} else {
					key = tm.firstKey();
				}
				if (tm.get(key) == 1) {
					tm.remove(key);
				} else {
					tm.put(key, tm.get(key) - 1);
				}
			}
		}
		if (tm.isEmpty()) {
			sb.append("EMPTY").append('\n');
		} else {
			sb.append(tm.lastKey()).append(' ').append(tm.firstKey()).append('\n');
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			solution(br, sb, st);
		}
		System.out.print(sb);
	}
}