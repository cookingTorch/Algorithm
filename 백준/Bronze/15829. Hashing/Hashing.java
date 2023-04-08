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
		
		int n, i;
		long ans = 0;
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		str = br.readLine();
		for (i = 0; i < n; i++) {
			ans += ((((int) str.charAt(i)) - ((int) 'a') + 1) * Math.pow(31, i)) % 1234567891;
			ans = ans % 1234567891;
		}
		
		bw.write(Long.toString(ans));
		
		bw.flush();
		bw.close();

	}

}