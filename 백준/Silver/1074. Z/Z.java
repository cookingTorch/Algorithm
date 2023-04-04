import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int zOrder(int n, int r, int c) {
		
		if (n == 2) {
			if (r == 1 && c == 1) {
				return 1;
			}
			else if (r == 1 && c == 2){
				return 2;
			}
			else if (r == 2 && c == 1){
				return 3;
			}
			else {
				return 4;
			}
		}
		
		else {
			if (r <= n / 2 && c <= n / 2) {
				return zOrder(n / 2, r, c);
			}
			else if (r <= n / 2 && c > n / 2) {
				return ((n * n) / 4) + zOrder(n / 2, r, c - (n / 2));
			}
			else if (r > n / 2 && c <= n / 2) {
				return (((n * n) / 4) * 2) + zOrder(n / 2, r - (n / 2), c);
			}
			else {
				return (((n * n) / 4) * 3) + zOrder(n / 2, r - (n / 2), c - (n / 2));
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		StringTokenizer st;
		
		int n, r, c;
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		System.out.println(zOrder((int)Math.pow(2, n), r + 1, c + 1) - 1);

	}

}
