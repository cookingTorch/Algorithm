import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	private static int factorial(int n) {
		
		int i, ans = 1;
		
		for (i = 2; i <= n; i++) {
			ans = ans * i;
		}
		
		return ans;
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		StringTokenizer st;
		
		int n, k, ans;
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		ans = factorial(n) / (factorial(k) * factorial(n - k));
		bw.write(Integer.toString(ans));
		
		bw.flush();
		bw.close();

	}

}