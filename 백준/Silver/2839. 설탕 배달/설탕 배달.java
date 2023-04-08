import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		
		int n, ans = 0;
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		while (n % 5 != 0) {
			n -= 3;
			ans++;
			if (n < 0) {
				bw.write("-1");
				break;
			}
		}
		if (n >= 0) {
			ans += n / 5;
			bw.write(Integer.toString(ans));
		}
		
		bw.flush();
		bw.close();

	}

}