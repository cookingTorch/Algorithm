import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int cnt;
	private static StringBuilder sb;
	
	private static void move(int size, int from, int to, int mid) {
		if (size == 1) {
			cnt++;
			sb.append(from).append(' ').append(to).append('\n');
			return;
		}
		move(size - 1, from, mid, to);
		cnt++;
		sb.append(from).append(' ').append(to).append('\n');
		move(size - 1, mid, to, from);
	}

	public static void main(String[] args) throws IOException {
		int n;
		
		n = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
		cnt = 0;
		sb = new StringBuilder();
		move(n, 1, 3, 2);
		System.out.print(new StringBuilder().append(cnt).append('\n').append(sb));
	}
}
