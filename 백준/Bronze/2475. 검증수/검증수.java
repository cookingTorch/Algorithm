import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		StringTokenizer st;
		
		int n, sum = 0, ans, i;
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		for (i = 0; i < 5; i++) {
			n = Integer.parseInt(st.nextToken());
			sum += Math.pow(n, 2);
		}
		ans = sum % 10;
		
		bw.write(Integer.toString(ans));
		
		bw.flush();
		bw.close();

	}

}