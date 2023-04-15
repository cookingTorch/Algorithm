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
		
		int n, i, temp0 = 0, temp1, bit = 1, cnt = 1;
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		for (i = 0; i < n; i++) {
			temp1 = Integer.parseInt(st.nextToken());
			if (i > 0) {
				if ((temp0 < temp1 && bit == 0) || (temp0 > temp1 && bit == 1)) {
					cnt++;
					bit ^= 1;
				}
			}
			temp0 = temp1;
		}
		
		for (i = 0; i < n; i++) {
			if (cnt <= Math.pow(2, i)) {
				bw.write(Integer.toString(i));
				break;
			}
		}
		
		bw.flush();
		bw.close();

	}

}