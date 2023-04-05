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
		String str;
		StringTokenizer st;
		
		int num1, num2, i, gcd = 0, lcm = 0;
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		num1 = Integer.parseInt(st.nextToken());
		num2 = Integer.parseInt(st.nextToken());
		
		for (i = Math.min(num1, num2); i > 0; i--) {
			if (num1 % i == 0 && num2 % i == 0) {
				gcd = i;
				break;
			}
		}
		for (i = Math.max(num1, num2); i <= num1 * num2; i++) {
			if (i % num1 == 0 && i % num2 == 0) {
				lcm = i;
				break;
			}
		}
		
		bw.write(Integer.toString(gcd) + "\n" + Integer.toString(lcm));
		
		bw.flush();
		bw.close();

	}

}