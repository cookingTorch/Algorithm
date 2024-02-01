import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	private static int n;
	private static int ans;
	
	private static boolean isPrime(int num) {
		int i;
		
		if (num == 1) {
			return false;
		}
		for (i = 2; i * i <= num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	private static void dfs(int depth, BufferedWriter bw) throws IOException {
		int i;
		
		if (depth == n) {
			bw.write(Integer.toString(ans));
			bw.write("\n");
			return;
		}
		for (i = 1; i < 10; i++) {
			ans *= 10;
			ans += i;
			if (isPrime(ans)) {
				dfs(depth + 1, bw);
			}
			ans /= 10;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		ans = 0;
		dfs(0, bw);
		bw.flush();
		bw.close();
	}
}
