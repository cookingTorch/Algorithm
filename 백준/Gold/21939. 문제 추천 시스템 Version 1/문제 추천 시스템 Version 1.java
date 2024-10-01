import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static final int MIN = Integer.MIN_VALUE;
	private static final int SHIFT = 17;
	private static final int LEAF = 1 << SHIFT;
	private static final int SIZE = LEAF << 1;
	private static final int MOD = LEAF - 1;
	private static final int RECOMMEND = 9;
	private static final int ADD = 3;
	private static final int SOLVED = 6;
	private static final char MAX = '1';
	private static final char LINE_BREAK = '\n';
	private static final String DELIM = " ";

	private static int[] maxTree;
	private static int[] minTree;

	private static final void init() {
		int i;

		for (i = LEAF; i < SIZE; i++) {
			if (maxTree[i] == 0) {
				maxTree[i] = MIN;
				minTree[i] = INF;
			}
		}
		for (i = LEAF - 1; i != 0; i--) {
			maxTree[i] = Math.max(maxTree[i << 1], maxTree[i << 1 | 1]);
			minTree[i] = Math.min(minTree[i << 1], minTree[i << 1 | 1]);
		}
	}

	private static final void insert(int p, int l) {
		int i;

		maxTree[LEAF + p] = l;
		minTree[LEAF + p] = l;
		for (i = LEAF + p >> 1; i != 0; i--) {
			maxTree[i] = Math.max(maxTree[i << 1], maxTree[i << 1 | 1]);
			minTree[i] = Math.min(minTree[i << 1], minTree[i << 1 | 1]);
		}
	}

	private static final void delete(int p) {
		int i;

		maxTree[LEAF + p] = MIN;
		minTree[LEAF + p] = INF;
		for (i = LEAF + p >> 1; i != 0; i--) {
			maxTree[i] = Math.max(maxTree[i << 1], maxTree[i << 1 | 1]);
			minTree[i] = Math.min(minTree[i << 1], minTree[i << 1 | 1]);
		}
	}

	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int p;
		int l;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		maxTree = new int[SIZE];
		minTree = new int[SIZE];
		while (n-- > 0) {
			st = new StringTokenizer(br.readLine(), DELIM, false);
			p = Integer.parseInt(st.nextToken());
			l = (Integer.parseInt(st.nextToken()) << SHIFT) | p;
			maxTree[LEAF + p] = l;
			minTree[LEAF + p] = l;
		}
		init();
		m = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine(), DELIM, false);
			switch (st.nextToken().length()) {
				case RECOMMEND:
					sb.append((st.nextToken().charAt(0) == MAX ? maxTree[1] : minTree[1]) & MOD)
							.append(LINE_BREAK);
					break;
				case ADD:
					p = Integer.parseInt(st.nextToken());
					insert(p, (Integer.parseInt(st.nextToken()) << SHIFT) | p);
					break;
				case SOLVED:
					delete(Integer.parseInt(st.nextToken()));
					break;
			}
		}
		System.out.print(sb.toString());
	}
}
