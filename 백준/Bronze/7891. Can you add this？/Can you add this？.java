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
		
		int t, x, y, i;
		
		str = br.readLine();
		t = Integer.parseInt(str);
		
		for (i = 0; i < t; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			sb.append(x + y).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

}