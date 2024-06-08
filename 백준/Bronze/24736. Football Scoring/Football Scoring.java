import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int[] arr = new int[] {6, 3, 2, 1, 2};
	
	public static void main(String[] args) throws IOException {
		int i;
		int score;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		score = 0;
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < 5; i++) {
			score += arr[i] * Integer.parseInt(st.nextToken());
		}
		sb = new StringBuilder();
		sb.append(score).append(' ');
		score = 0;
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < 5; i++) {
			score += arr[i] * Integer.parseInt(st.nextToken());
		}
		System.out.print(sb.append(score));
	}
}
