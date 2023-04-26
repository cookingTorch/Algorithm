import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		BigInteger n, m;
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		n = new BigInteger(st.nextToken());
		m = new BigInteger(st.nextToken());
		
		sb.append(n.divide(m).toString()).append("\n").append(n.remainder(m).toString());
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

}