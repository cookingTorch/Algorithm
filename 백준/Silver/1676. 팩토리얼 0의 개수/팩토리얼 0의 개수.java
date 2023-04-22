import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		
		int n, i, num, cnt2 = 0, cnt5 = 0;
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		for (i = 1; i <= n; i++) {
			num = i;
			while (num >= 2) {
				if (num % 2 == 0) {
					cnt2++;
					num /= 2;
				}
				else {
					break;
				}
			}
			num = i;
			while (num >= 5) {
				if (num % 5 == 0) {
					cnt5++;
					num /= 5;
				}
				else {
					break;
				}
			}
		}
		
		bw.write(Integer.toString(Math.min(cnt2, cnt5)));
		
		bw.flush();
		bw.close();

	}

}