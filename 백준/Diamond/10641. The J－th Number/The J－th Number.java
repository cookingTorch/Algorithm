import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static final char LINE_FEED = '\n';
	private static final String DELIM = " ";

	private static final class Range {
		int l;
		int r;

		Range(int l, int r) {
			this.l = l;
			this.r = r;
		}
	}

	private static final class SegTree {
		private final int[] initLazy;
		private final int[] lazy;
		private final long[] initTree;
		private final long[] tree;

		SegTree() {
			initLazy = new int[size << 2];
			lazy = new int[size << 2];
			initTree = new long[size << 2];
			tree = new long[size << 2];
		}

		void init() {
			System.arraycopy(initLazy, 0, lazy, 0, size << 2);
			System.arraycopy(initTree, 0, tree, 0, size << 2);
		}

		private void propagate(int node, int start, int end) {
			if (lazy[node] != 0) {
				tree[node] += (long) lazy[node] * (lens[end] - lens[start - 1]);
				if (start != end) {
					lazy[node << 1] += lazy[node];
					lazy[node << 1 | 1] += lazy[node];
				}
				lazy[node] = 0;
			}
		}

		void update(int node, int start, int end, int l, int r) {
			int mid;

			if (r < start || end < l) {
				return;
			}
			propagate(node, start, end);
			if (l <= start && end <= r) {
				lazy[node]++;
				return;
			}
			mid = start + end >>> 1;
			update(node << 1, start, mid, l, r);
			update(node << 1 | 1, mid + 1, end, l, r);
			tree[node] += lens[Math.min(end, r)] - lens[Math.max(start, l) - 1];
		}

		long query(int node, int start, int end, int l, int r) {
			int mid;

			if (r < start || end < l) {
				return 0L;
			}
			propagate(node, start, end);
			if (l <= start && end <= r) {
				return tree[node];
			}
			mid = start + end >>> 1;
			return query(node << 1, start, mid, l, r) + query(node << 1 | 1, mid + 1, end, l, r);
		}
	}

	private static final class Query implements Comparable<Query> {
		int a;
		int b;
		int v;

		Query(int a, int b, int v) {
			this.a = a;
			this.b = b;
			this.v = v;
		}

		void convert() {
			a = find(a);
			b = find(b);
		}

		void run() {
			segTree.update(1, 1, size, a, b);
		}

		@Override
		public int compareTo(Query o) {
			return v - o.v;
		}
	}

	private static final class Pbs {
		int x;
		int y;
		int left;
		int right;
		int ans;
		long j;
		Pbs next;

		Pbs(int x, int y, long j, Pbs next) {
			this.x = x;
			this.y = y;
			left = 0;
			right = m;
			this.j = j;
			this.next = next;
		}

		void convert() {
			x = find(x);
			y = find(y);
		}

		void validate(int mid) {
			arr[mid] = next;
			if (segTree.query(1, 1, size, x, y) < j) {
				left = mid + 1;
			} else {
				right = mid;
			}
			if (left >= right) {
				ans = queries[right].v;
				cnt++;
				return;
			}
			mid = left + right >>> 1;
			next = arr[mid];
			arr[mid] = this;
		}
	}

	private static int m;
	private static int cnt;
	private static int size;
	private static int[] lens;
	private static SegTree segTree;
	private static Pbs[] arr;
	private static Range[] ranges;
	private static Query[] queries;

	private static void buildRange(int[] edges) {
		int i;
		int edgeCnt;

		edgeCnt = edges.length;
		ranges = new Range[edgeCnt << 1];
		size = 0;
		for (i = 0; i < edgeCnt - 1; i++) {
			ranges[++size] = new Range(edges[i], edges[i]);
			if (edges[i + 1] - edges[i] > 1) {
				ranges[++size] = new Range(edges[i] + 1, edges[i + 1] - 1);
			}
		}
		ranges[++size] = new Range(edges[i], edges[i]);
		lens = new int[size + 1];
		for (i = 1; i <= size; i++) {
			lens[i] = lens[i - 1] + ranges[i].r - ranges[i].l + 1;
		}
	}

	private static int find(int edge) {
		int mid;
		int left;
		int right;

		left = 1;
		right = size + 1;
		while (left < right) {
			mid = left + right >>> 1;
			if (ranges[mid].l < edge) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}

	public static void main(String[] args) throws IOException {
		int q;
		int a;
		int b;
		int v;
		int x;
		int y;
		int i;
		int idx;
		int mid;
		int[] edges;
		long j;
		Pbs[] ans;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), DELIM, false);
		st.nextToken();
		m = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		edges = new int[m + q << 1];
		idx = 0;
		queries = new Query[m];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), DELIM, false);
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			queries[i] = new Query(a, b, v);
			edges[idx++] = a;
			edges[idx++] = b;
		}
		Arrays.sort(queries, 0, m);
		ans = new Pbs[q];
		arr = new Pbs[m];
		mid = m >>> 1;
		for (i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine(), DELIM, false);
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			j = Long.parseLong(st.nextToken());
			arr[mid] = new Pbs(x, y, j, arr[mid]);
			ans[i] = arr[mid];
			edges[idx++] = x;
			edges[idx++] = y;
		}
		buildRange(Arrays.stream(edges).distinct().sorted().toArray());
		for (i = 0; i < m; i++) {
			queries[i].convert();
		}
		for (i = 0; i < q; i++) {
			ans[i].convert();
		}
		segTree = new SegTree();
		cnt = 0;
		while (cnt != q) {
			segTree.init();
			for (i = 0; i < m; i++) {
				queries[i].run();
				while (arr[i] != null) {
					arr[i].validate(i);
				}
			}
		}
		sb = new StringBuilder();
		for (i = 0; i < q; i++) {
			sb.append(ans[i].ans).append(LINE_FEED);
		}
		System.out.print(sb.toString());
	}
}
