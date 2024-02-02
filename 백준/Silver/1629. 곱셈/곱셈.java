import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static long power(long a, long b, long c) {
		long temp;
		
		if (b == 0) {
			return 1 % c;
		}
		if (b % 2 == 0) {
			temp = power(a, b / 2, c) % c;
			return (temp * temp) % c;
		}
		return ((power(a, b - 1, c) % c) * (a % c)) % c;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		long a, b, c;
		
		st = new StringTokenizer(br.readLine());
		a = Long.parseLong(st.nextToken());
		b = Long.parseLong(st.nextToken());
		c = Long.parseLong(st.nextToken());
		System.out.print(power(a, b, c));
	}
}
