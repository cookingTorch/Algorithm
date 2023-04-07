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
		
		int m, n, i, j, flag;
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		for (i = Math.max(m, 2); i <= n; i++) {
			flag = 1;
			for (j = 2; j <= (int) Math.sqrt(i); j++) {
				if (i % j == 0) {
					flag = 0;
					break;
				}
			}
			if (flag == 1) {
				bw.write(Integer.toString(i));
				bw.newLine();
			}
		}

		bw.flush();
		bw.close();
		
	}

}