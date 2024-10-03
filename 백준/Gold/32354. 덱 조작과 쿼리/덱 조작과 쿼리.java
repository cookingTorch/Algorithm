import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int PUSH = 4;
	private static final int POP = 3;
	private static final int RESTORE = 7;
	private static final int PRINT = 5;
	private static final char LINE_BREAK = '\n';

	private static final class Node {
		long sum;
		Node next;

		Node() {}

		Node(long val, Node next) {
			this.sum = next.sum + val;
			this.next = next;
		}
	}

	public static void main(String[] args) throws IOException {
		int n;
		int i;
		Node[] nodes;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		nodes = new Node[n + 1];
		nodes[0] = new Node();
		sb = new StringBuilder();
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine(), " ", false);
			switch (st.nextToken().length()) {
				case PUSH:
					nodes[i] = new Node(Long.parseLong(st.nextToken()), nodes[i - 1]);
					break;
				case POP:
					nodes[i] = nodes[i - 1].next;
					break;
				case RESTORE:
					nodes[i] = nodes[Integer.parseInt(st.nextToken())];
					break;
				case PRINT:
					nodes[i] = nodes[i - 1];
					sb.append(nodes[i].sum).append(LINE_BREAK);
					break;
			}
		}
		System.out.print(sb.toString());
	}
}
