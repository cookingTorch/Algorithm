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
		StringTokenizer st;
		
		BigInteger a, b;
		
		st = new StringTokenizer(br.readLine());
		a = new BigInteger(st.nextToken());
		b = new BigInteger(st.nextToken());
		
		sb.append((a.multiply(b)).toString());
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}