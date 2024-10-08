import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static final char SPACE = ' ';
	private static final char[] FAIL = {'-', '1', ' '};

	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int num;
		int[] nge;
		ArrayDeque<Integer> dq;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dq = new ArrayDeque<>(n + 1);
		st = new StringTokenizer(br.readLine(), " ", false);
		for (i = 0; i < n; i++) {
			dq.addLast(Integer.parseInt(st.nextToken()));
		}
		nge = new int[n];
		dq.addFirst(INF);
		while (n-- > 0) {
			num = dq.pollLast();
			while (num >= dq.peekFirst()) {
				dq.pollFirst();
			}
			nge[n] = dq.peekFirst();
			dq.addFirst(num);
		}
		sb = new StringBuilder();
		for (int ans : nge) {
			if (ans == INF) {
				sb.append(FAIL);
			} else {
				sb.append(ans).append(SPACE);
			}
		}
		System.out.print(sb.toString());
	}
}
