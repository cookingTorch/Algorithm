import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int x, k, a, b, i;
		
		st = new StringTokenizer(br.readLine());
		Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		for (i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			if (a == x) {
				x = b;
			} else if (b == x) {
				x = a;
			}
		}
		return x;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		System.out.print(solution(br, st));
	}
}
