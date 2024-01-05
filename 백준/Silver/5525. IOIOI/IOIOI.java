import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int n, m, i, temp, result, ans;
		char ch = 'I';
		String s;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		s = br.readLine();
		ans = 0;
		
		for (i = 0; i < m; i++) {
			ch = 'I';
			temp = 0;
			while (i + temp < m && s.charAt(i + temp) == ch) {
				temp++;
				if (ch == 'I') {
					ch = 'O';
				} else {
					ch = 'I';
				}
			}
			if (temp > 0) {
				i += temp - 1;
			}
			if (ch == 'I') {
				temp--;
			}
			temp = (temp - 1) / 2;
			result = temp - n + 1;
			if (result > 0) {
				ans += result;
			}
		}
		
		sb.append(ans);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}