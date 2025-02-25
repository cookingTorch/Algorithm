import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final char LINE_BREAK = '\n';
	private static final String DELIM = " ";

	private static int[] arr;
	private static long[] lazy;
	private static long[] tree;

	private static final long gcd(long a, long b) {
		long r;

		while (b != 0L) {
			r = a % b;
			a = b;
			b = r;
		}
		return a;
	}

	private static final void initLazy(int idx, int start, int end) {
		int mid;

		if (start == end) {
			lazy[idx] = arr[start];
			return;
		}
		mid = start + end >> 1;
		initLazy(idx << 1, start, mid);
		initLazy(idx << 1 | 1, mid + 1, end);
	}

	private static final long initTree(int idx, int start, int end) {
		int mid;

		if (start == end) {
			return tree[idx] = arr[start] - arr[start + 1];
		}
		mid = start + end >> 1;
		return tree[idx] = gcd(initTree(idx << 1, start, mid), initTree(idx << 1 | 1, mid + 1, end));
	}

	private static final long getNum(int idx, int start, int end, int pos) {
		int mid;

		if (start == end) {
			return lazy[idx];
		}
		if (lazy[idx] != 0L) {
			lazy[idx << 1] += lazy[idx];
			lazy[idx << 1 | 1] += lazy[idx];
			lazy[idx] = 0L;
		}
		mid = start + end >> 1;
		if (pos <= mid) {
			return getNum(idx << 1, start, mid, pos);
		}
		return getNum(idx << 1 | 1, mid + 1, end, pos);
	}

	private static final long rangeGcd(int idx, int start, int end, int left, int right) {
		int mid;

		if (left <= start && end <= right) {
			return tree[idx];
		}
		mid = start + end >> 1;
		if (right <= mid) {
			return rangeGcd(idx << 1, start, mid, left, right);
		}
		if (left > mid) {
			return rangeGcd(idx << 1 | 1, mid + 1, end, left, right);
		}
		return gcd(rangeGcd(idx << 1, start, mid, left, right), rangeGcd(idx << 1 | 1, mid + 1, end, left, right));
	}

	private static final void add(int idx, int start, int end, int left, int right, int t) {
		int mid;

		if (right < start || end < left) {
			return;
		}
		if (left <= start && end <= right) {
			lazy[idx] += t;
			return;
		}
		mid = start + end >> 1;
		add(idx << 1, start, mid, left, right, t);
		add(idx << 1 | 1, mid + 1, end, left, right, t);
	}

	private static final long update(int idx, int start, int end, int pos, int t) {
		int mid;

		if (start == end) {
			return tree[idx] += t;
		}
		mid = start + end >> 1;
		if (pos <= mid) {
			return tree[idx] = gcd(update(idx << 1, start, mid, pos, t), tree[idx << 1 | 1]);
		}
		return tree[idx] = gcd(tree[idx << 1], update(idx << 1 | 1, mid + 1, end, pos, t));
	}

	public static void main(String[] args) throws IOException {
		int n;
		int q;
		int t;
		int a;
		int b;
		int i;
		int lazyEnd;
		int treeEnd;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine(), DELIM, false);
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		lazy = new long[n << 2];
		tree = new long[n << 2];
		lazyEnd = n - 1;
		treeEnd = n - 2;
		initLazy(1, 0, lazyEnd);
		initTree(1, 0, treeEnd);
		q = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while (q-- > 0) {
			st = new StringTokenizer(br.readLine(), DELIM, false);
			t = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken()) - 1;
			if (t == 0) {
				if (a == b) {
					sb.append(getNum(1, 0, lazyEnd, a)).append(LINE_BREAK);
				} else {
					sb.append(gcd(getNum(1, 0, lazyEnd, a), Math.abs(rangeGcd(1, 0, treeEnd, a, b - 1)))).append(LINE_BREAK);
				}
			} else {
				add(1, 0, lazyEnd, a, b, t);
				if (a > 0) {
					update(1, 0, treeEnd, a - 1, -t);
				}
				if (b < lazyEnd) {
					update(1, 0, treeEnd, b, t);
				}
			}
		}
		System.out.print(sb.toString());
	}
}
