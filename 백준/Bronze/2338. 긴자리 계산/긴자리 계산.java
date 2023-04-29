import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		
		str = br.readLine();
		BigInteger a = new BigInteger(str);
		str = br.readLine();
		BigInteger b = new BigInteger(str);
		
		sb.append(a.add(b)).append("\n").append(a.subtract(b)).append("\n").append(a.multiply(b));
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

}