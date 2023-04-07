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
		
		int n, i, j, sum, flag = 0;
		String num;
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		for (i = 1; i < n; i++) {
			num = Integer.toString(i);
			sum = i;
			for (j = 0; j < num.length(); j++) {
				sum += Integer.parseInt(Character.toString(num.charAt(j)));
			}
			if (sum == n) {
				bw.write(Integer.toString(i));
				flag = 1;
				break;
			}
		}
		
		if (flag == 0) {
			bw.write("0");
		}
		
		bw.flush();
		bw.close();

	}

}