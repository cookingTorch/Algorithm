import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int MAX_K = 1_000_000;
	private static final long INF = Long.MAX_VALUE;
	private static final char LINE_BREAK = '\n';

	private static int[] arr;
	private static long val;
	private static long[] tree;
	private static BufferedReader br;

	private static void init(int node, int start, int end) {
		int mid;

		if (start == end) {
			tree[node] = arr[start];
			return;
		}
		mid = start + end >> 1;
		init(node << 1, start, mid);
		init(node << 1 | 1, mid + 1, end);
		tree[node] = Math.min(tree[node << 1], tree[node << 1 | 1]);
	}

	private static void alter(int node, int start, int end) {
		int mid;

		if (start == end) {
			tree[node] = val;
			return;
		}
		mid = start + end >> 1;
		if (tree[node << 1] == tree[node]) {
			alter(node << 1, start, mid);
		} else {
			alter(node << 1 | 1, mid + 1, end);
		}
		tree[node] = Math.min(tree[node << 1], tree[node << 1 | 1]);
	}

	private static long solution() throws IOException {
		int k;
		int i;
		int end;
		long sum;
		long cost;
		StringTokenizer st;

		k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ", false);
		for (i = 0; i < k; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		end = --k;
		init(1, 0, end);
		sum = 0;
		while (k-- > 0) {
			cost = tree[1];
			val = INF;
			alter(1, 0, end);
			sum += cost += tree[1];
			val = cost;
			alter(1, 0, end);
		}
		return sum;
	}

	public static void main(String[] args) throws IOException {
		int t;
		StringBuilder sb;

		arr = new int[MAX_K];
		tree = new long[MAX_K << 2];
		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while (t-- > 0) {
			sb.append(solution()).append(LINE_BREAK);
		}
		System.out.print(sb.toString());
	}
}
