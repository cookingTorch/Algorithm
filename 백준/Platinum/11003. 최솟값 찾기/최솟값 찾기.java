import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, l, num, i;
		Deque<int[]> dq;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		dq = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		num = Integer.parseInt(st.nextToken());
		dq.addLast(new int[] {0, num});
		sb.append(num).append(' ');
		for (i = 1; i < n; i++) {
			if (!dq.isEmpty() && dq.peekFirst()[0] <= i - l) {
				dq.pollFirst();
			}
			num = Integer.parseInt(st.nextToken());
			while (!dq.isEmpty() && dq.peekLast()[1] > num) {
				dq.pollLast();
			}
			dq.addLast(new int[] {i, num});
			sb.append(dq.peekFirst()[1]).append(' ');
		}
		System.out.print(sb);
	}
}
