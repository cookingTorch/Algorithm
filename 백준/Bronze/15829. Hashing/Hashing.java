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
		
		int n, i, j;
		long ri, ans = 0;
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		str = br.readLine();
		for (i = 0; i < n; i++) {
			ri = 1;
			for (j = 1; j <= i; j++) {
				ri = (ri * 31) % 1234567891;
			}
			ans += ((((int) str.charAt(i)) - ((int) 'a') + 1) * ri) % 1234567891;
			ans = ans % 1234567891;
		}
		
		bw.write(Long.toString(ans));
		
		bw.flush();
		bw.close();

	}

}