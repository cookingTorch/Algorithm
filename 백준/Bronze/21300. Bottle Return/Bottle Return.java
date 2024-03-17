import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int NUM = 6;
	private static final int COST = 5;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int sum, i;
		
		sum = 0;
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < NUM; i++) {
			sum += Integer.parseInt(st.nextToken()) * COST;
		}
		System.out.print(sum);
	}
}
