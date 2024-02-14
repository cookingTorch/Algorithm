import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, r, c, ans, size, block, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		size = (int) Math.pow(2, n);
		ans = 0;
		for (i = 0; i < n; i++) {
			size /= 2;
			block = size * size;
			if (r < size && c < size) {
				continue;
			} else if (r < size) {
				c -= size;
				ans += block;
			} else if (c < size) {
				r -= size;
				ans += 2 * block;
			} else {
				r -= size;
				c -= size;
				ans += 3 * block;
			}
		}
		System.out.print(ans);
	}
}
