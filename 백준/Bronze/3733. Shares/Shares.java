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
		
		int n, s;
		
		while ((str = br.readLine())!= null) {
			st = new StringTokenizer(str, " ");
			n = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			sb.append(s / (n + 1)).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

}