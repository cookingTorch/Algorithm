import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int people, i;
		
		st = new StringTokenizer(br.readLine());
		people = Integer.parseInt(st.nextToken()) * Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < 5; i++) {
			sb.append(Integer.parseInt(st.nextToken()) - people).append(' ');
		}
		System.out.println(sb);
	}
}