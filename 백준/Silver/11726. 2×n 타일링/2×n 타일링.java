import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		int n = Integer.parseInt(str1);
		long[] tile = new long[1001];
		tile[0] = 1;
		tile[1] = 1;
		for(int i = 2; i <= n; i++) {
			tile[i] = (tile[i - 1] + tile[i - 2]) % 10007; 
		}
		System.out.println(tile[n] % 10007);
	}

} 
