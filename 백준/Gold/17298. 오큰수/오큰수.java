import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static final int FAIL = -1;
	private static final char SPACE = ' ';

	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int[] arr;
		int[] ans;
		ArrayDeque<Integer> stack;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		stack = new ArrayDeque<>(n + 1);
		arr = new int[n + 1];
		st = new StringTokenizer(br.readLine(), " ", false);
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		arr[n] = INF;
		ans = new int[n];
		stack.addFirst(n);
		while (n-- > 0) {
			while (arr[n] >= arr[stack.peekFirst()]) {
				stack.pollFirst();
			}
			ans[n] = arr[stack.peekFirst()];
			stack.addFirst(n);
		}
		sb = new StringBuilder();
		for (int num : ans) {
			sb.append(num == INF ? FAIL : num).append(SPACE);
		}
		System.out.print(sb.toString());
	}
}
