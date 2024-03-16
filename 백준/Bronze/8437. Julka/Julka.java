import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	private static final BigInteger TWO = new BigInteger("2");
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		BigInteger sum, diff, klaudia, natalia;
		
		sum = new BigInteger(br.readLine());
		diff = new BigInteger(br.readLine());
		natalia = sum.subtract(diff).divide(TWO);
		klaudia = natalia.add(diff);
		sb.append(klaudia).append('\n').append(natalia);
		System.out.print(sb);
	}
}
