import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		
		long n, ans, i;
		
		str = br.readLine();
		n = Long.parseLong(str);
		ans = n;
		for (i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				ans = (ans / i) * (i - 1);
				while (n % i == 0) {
					n /= i;
				}
			}
		}
		if (n > 1) {
			ans = (ans / n) * (n - 1);
		}
		
		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}