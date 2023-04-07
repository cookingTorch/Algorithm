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
		
		int n, cnt = 0, i = 665;
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		while (cnt < n) {
			i++;
			if (Integer.toString(i).contains("666")) {
				cnt++;
			}
		}
		
		bw.write(Integer.toString(i));
		
		bw.flush();
		bw.close();
		
	}

}