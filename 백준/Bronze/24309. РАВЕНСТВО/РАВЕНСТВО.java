import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws IOException {
		BigInteger a;
		BigInteger b;
		BigInteger c;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		a = new BigInteger(br.readLine());
		b = new BigInteger(br.readLine());
		c = new BigInteger(br.readLine());
		System.out.print(b.subtract(c).divide(a));
	}
}
