import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	private static final char[] PUSH = {'+', '\n'};
	private static final char[] POP = {'-', '\n'};
	private static final char[] PUSH_POP = {'+', '\n', '-', '\n'};
	private static final char[] NO = {'N', 'O'};

	public static void main(String[] args) throws IOException {
		int n;
		int num;
		int next;
		StringBuilder sb;
		ArrayDeque<Integer> stack;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		next = 1;
		stack = new ArrayDeque<>();
		sb = new StringBuilder();
		while (n-- > 0) {
			num = Integer.parseInt(br.readLine());
			if (next > num) {
				for (;;) {
					if (stack.isEmpty()) {
						System.out.print(NO);
						return;
					}
					sb.append(POP);
					if (stack.pollFirst() == num) {
						break;
					}
				}
			} else {
				while (next < num) {
					sb.append(PUSH);
					stack.addFirst(next++);
				}
				sb.append(PUSH_POP);
				next++;
			}
		}
		System.out.print(sb.toString());
	}
}
