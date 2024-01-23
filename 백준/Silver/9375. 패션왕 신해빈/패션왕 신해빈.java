import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	private static int count(BufferedReader br, StringTokenizer st) throws IOException {
		int n, cnt, i;
		String type;
		HashMap<String, Integer> map;
		
		map = new HashMap<>();
		n = Integer.parseInt(br.readLine());
		for (i = 0; i< n; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			type = st.nextToken();
			if (map.containsKey(type)) {
				map.put(type, map.get(type) + 1);
			} else {
				map.put(type, 1);
			}
		}
		cnt = 1;
		for (int value : map.values()) {
			cnt *= value + 1;
		}
		return cnt - 1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			sb.append(count(br, st)).append('\n');
		}
		System.out.println(sb);
	}
}