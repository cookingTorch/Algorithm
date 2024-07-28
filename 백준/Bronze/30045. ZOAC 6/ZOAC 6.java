import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		int i;
		int n;
		int cnt;
		int len;
		String str;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		cnt = 0;
		n = Integer.parseInt(br.readLine());
		while (n-- > 0) {
			str = br.readLine();
			len = str.length();
			for (i = 1; i < len; i++) {
				if ((str.charAt(i) == '1' && str.charAt(i - 1) == '0') || (str.charAt(i) == 'I' && str.charAt(i - 1) == 'O')) {
					cnt++;
					break;
				}
			}
		}
		System.out.print(cnt);
	}
}
