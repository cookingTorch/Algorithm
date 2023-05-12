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
		
		int n, i, block;
		
		while (true) {
			str = br.readLine();
			n = Integer.parseInt(str);
			if (n == 0) {
				break;
			}
			block = 0;
			for (i = 1; i <= n; i++) {
				block += i;
			}
			sb.append(block).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

}