import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		int i;
		int len;
		int cnt;
		int cntY;
		String str;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		len = str.length();
		cnt = 0;
		cntY = 0;
		for (i = 0; i < len; i++) {
			switch (str.charAt(i)) {
				case 'a': case 'e': case 'i': case 'o': case 'u':
					cnt++;
				case 'y':
					cntY++;
			}
		}
		System.out.print(new StringBuilder().append(cnt).append(' ').append(cntY));
	}
}
