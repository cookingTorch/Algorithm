import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	private static int[] ans = new int[10];
	
	private static void add(int num, int start, int end) {
		int i;
		for (i = start; i <= end; i++) {
			ans[i] += num;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		
		int n, length, tenExp, i;
		
		str = br.readLine();
		n = Integer.parseInt(str);
		length = str.length();
		
		for (i = 0; i < length; i++) {
			tenExp = (int) Math.pow(10, i);
			if (i == 0) {
				add(n / 10, 0, 9);
				add(1, 0, n % 10);
			}
			else if (i == length - 1) {
				add(tenExp, 0, (n / tenExp) - 1);
				add(n % tenExp + 1, n / tenExp, n / tenExp);
			}
			else {
				add((n / (tenExp * 10)) * tenExp, 0, 9);
				add(tenExp, 0, ((n / tenExp) % 10) - 1);
				add(n % tenExp + 1, (n / tenExp) % 10, (n / tenExp) % 10);
			}
		}
		
		for (i = 0; i < length; i++) {
			tenExp = (int) Math.pow(10, i);
			ans[0] -= tenExp;
		}
		
		for (i = 0; i < 10; i++) {
			sb.append(ans[i]).append(" ");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}