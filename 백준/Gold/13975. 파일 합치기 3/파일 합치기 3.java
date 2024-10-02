import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int MAX_SIZE = 2_097_152;
	private static final int SHIFT = 21;
	private static final long MOD = (1 << SHIFT) - 1;
	private static final long INF = 10_000_000_001L;
	private static final long INIT = INF << SHIFT;
	private static final char LINE_BREAK = '\n';

	private static int leaf;
	private static long[] tree;
	private static BufferedReader br;

	private static final void init(int k) throws IOException {
		int i;
		int thr;
		int size;
		StringTokenizer st;

		for (leaf = 1; leaf < k; leaf <<= 1);
		size = leaf << 1;
		st = new StringTokenizer(br.readLine());
		thr = leaf + k;
		for (i = leaf; i < thr; i++) {
			tree[i] = Long.parseLong(st.nextToken()) << SHIFT | i;
		}
		for (; i < size; i++) {
			tree[i] = INIT;
		}
		for (i = leaf - 1; i > 0; i--) {
			tree[i] = Math.min(tree[i << 1], tree[i << 1 | 1]);
		}
	}

	private static final void alter(long val) {
		int i;

		i = (int) (tree[1] & MOD);
		tree[i] = val << SHIFT | i;
		for (i >>= 1; i > 0; i >>= 1) {
			tree[i] = Math.min(tree[i << 1], tree[i << 1 | 1]);
		}
	}

	private static final long solution() throws IOException {
		int k;
		long sum;
		long cost;

		k = Integer.parseInt(br.readLine());
		init(k);
		sum = 0;
		while (--k > 0) {
			cost = tree[1] >> SHIFT;
			alter(INF);
			sum += cost += tree[1] >> SHIFT;
			alter(cost);
		}
		return sum;
	}

	public static void main(String[] args) throws IOException {
		int t;
		StringBuilder sb;

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
