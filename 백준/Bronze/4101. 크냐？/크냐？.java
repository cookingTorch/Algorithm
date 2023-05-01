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
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int a, b;
		
		while (true) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			if (a == 0 && b == 0) {
				break;
			}
			else {
				if (a > b) {
					sb.append("Yes");
				}
				else {
					sb.append("No");
				}
				sb.append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

}