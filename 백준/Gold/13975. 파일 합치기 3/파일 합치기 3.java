import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int MAX_K = 1_000_000;
	private static final int MAX_SIZE = 2_097_152;
	private static final long INF = Long.MAX_VALUE;
	private static final char LINE_BREAK = '\n';

	private static int leaf;
	private static int[] arr;
	private static long[] tree;
	private static BufferedReader br;

	private static void init(int k) {
		int i;
		int size;

		for (leaf = 1; leaf < k; leaf <<= 1);
		size = leaf << 1;
		for (i = 0; i < k; i++) {
			tree[leaf + i] = arr[i];
		}
		for (i += leaf; i < size; i++) {
			tree[i] = INF;
		}
		for (i = leaf - 1; i > 0; i--) {
			tree[i] = Math.min(tree[i << 1], tree[i << 1 | 1]);
		}
	}

	private static void alter(long val) {
		int i;

		i = 1;
		while (i < leaf) {
			i <<= 1;
			if (tree[i] != tree[i >> 1]) {
				i |= 1;
			}
		}
		tree[i] = val;
		for (i >>= 1; i > 0; i >>= 1) {
			tree[i] = Math.min(tree[i << 1], tree[i << 1 | 1]);
		}
	}

	private static long solution() throws IOException {
		int k;
		int i;
		long sum;
		long cost;
		StringTokenizer st;

		k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ", false);
		for (i = 0; i < k; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		init(k);
		sum = 0;
		while (--k > 0) {
			cost = tree[1];
			alter(INF);
			sum += cost += tree[1];
			alter(cost);
		}
		return sum;
	}

	public static void main(String[] args) throws IOException {
		int t;
		StringBuilder sb;

		arr = new int[MAX_K];
		tree = new long[MAX_SIZE];
		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while (t-- > 0) {
			sb.append(solution()).append(LINE_BREAK);
		}
		System.out.print(sb.toString());
	}
}
