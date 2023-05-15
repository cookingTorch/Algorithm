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
		
		int n1, k1, n2, k2;
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		n1 = Integer.parseInt(st.nextToken());
		k1 = Integer.parseInt(st.nextToken());
		n2 = Integer.parseInt(st.nextToken());
		k2 = Integer.parseInt(st.nextToken());
		
		sb.append((n1 * k1) + (n2 * k2));
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}