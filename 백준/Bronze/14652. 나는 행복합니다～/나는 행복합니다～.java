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
		
		int length, k, n, m;
		
		str = br.readLine();
		st = new StringTokenizer(str);
		st.nextToken();
		length = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		n = k / length;
		m = k % length;
		
		sb.append(n).append(" ").append(m);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}