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
		
		int n, i, j, num, ans;
		
		str = br.readLine();
		n = Integer.parseInt(str);
		ans = n;
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		for (i = 0; i < n; i++) {
			num = Integer.parseInt(st.nextToken());
			if (num == 1) {
				ans--;
			}
			for (j = 2; j < num; j++) {
				if (num % j == 0) {
					ans--;
					break;
				}
			}
		}
		
		bw.write(Integer.toString(ans));
		
		bw.flush();
		bw.close();
	}

}