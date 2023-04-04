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
		
		int a, b, c, abc, i;
		int[] cnt = new int[10];
		String number;
		
		str = br.readLine();
		a = Integer.parseInt(str);
		str = br.readLine();
		b = Integer.parseInt(str);
		str = br.readLine();
		c = Integer.parseInt(str);
		abc = a * b * c;
		number = Integer.toString(abc);
		for (i = 0; i < number.length(); i++) {
			cnt[Integer.parseInt(Character.toString(number.charAt(i)))]++;
		}
		for (i = 0; i < 10; i++) {
			bw.write(Integer.toString(cnt[i]));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		
	}

}