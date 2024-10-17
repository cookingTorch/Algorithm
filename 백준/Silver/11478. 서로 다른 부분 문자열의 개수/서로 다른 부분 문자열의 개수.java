import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
	public static void main(String[] args) throws IOException {
		int to;
		int len;
		int cnt;
		int from;
		String str;
		String sub;
		HashSet<String> set;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		len = (str = br.readLine()).length();
		set = new HashSet<>();
		cnt = 0;
		for (from = 0; from < len; from++) {
			for (to = from + 1; to <= len; to++) {
				sub = str.substring(from, to);
				if (!set.contains(sub)) {
					set.add(sub);
					cnt++;
				}
			}
		}
		System.out.print(cnt);
	}
}
