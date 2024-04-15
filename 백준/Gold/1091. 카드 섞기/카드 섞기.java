import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final String IMPOSSIBLE = "-1";
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1, st2;
		
		int n, ans, i;
		int[] p, s, prev, cards, temp;
		
		n = Integer.parseInt(br.readLine());
		p = new int[n];
		s = new int[n];
		prev = new int[n];
		st1 = new StringTokenizer(br.readLine());
		st2 = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			p[i] = Integer.parseInt(st1.nextToken());
			s[i] = Integer.parseInt(st2.nextToken());
			prev[i] = i;
		}
		for (i = 0; i < n; i++) {
			if (p[prev[i]] != i % 3) {
				break;
			}
		}
		if (i == n) {
			System.out.print("0");
			return;
		}
		cards = new int[n];
		for (ans = 1;; temp = prev, prev = cards, cards = temp, ans++) {
			for (i = 0; i < n; i++) {
				cards[s[i]] = prev[i];
			}
			for (i = 0; i < n; i++) {
				if (p[cards[i]] != i % 3) {
					break;
				}
			}
			if (i == n) {
				System.out.print(ans);
				return;
			}
			for (i = 0; i < n; i++) {
				if (cards[i] % 3 != i % 3) {
					break;
				}
			}
			if (i == n) {
				System.out.print(IMPOSSIBLE);
				return;
			}
		}
	}
}
