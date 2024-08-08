import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		int cnt;
		int len;
		String str;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		len = str.length();
		cnt = 0;
		while (len-- > 0) {
			switch (str.charAt(len)) {
				case 'a': case 'e': case 'i': case 'o': case 'u':
					cnt++;
			}
		}
		System.out.print(cnt);
	}
}
