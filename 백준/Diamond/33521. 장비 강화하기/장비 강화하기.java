import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final char LINE_FEED = '\n';
	private static final String DELIM = " ";

	private static final class Equipment implements Comparable<Equipment> {
		int a;
		int b;
		int idx;

		Equipment(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Equipment o) {
			if (a == o.a) {
				return b - o.b;
			}
			return a - o.a;
		}
	}

	private static final class Pbs {
		int left;
		int right;
		int ans;
		long x;
		long y;
		Pbs next;

		Pbs(long x, long y, Pbs next) {
			left = 1;
			right = n + 1;
			this.x = x;
			this.y = y;
			this.next = next;
		}

		void validate(int mid) {
			arr[mid] = next;
			if (sum <= x && (treeB[1] <= y || mid - (query(1, 1, n, y, 0) - 1) <= x - sum)) {
				left = mid + 1;
			} else {
				right = mid;
			}
			if (left >= right) {
				ans = right - 1;
				cnt++;
				return;
			}
			mid = left + right >>> 1;
			next = arr[mid];
			arr[mid] = this;
		}
	}

	private static int n;
	private static int cnt;
	private static long sum;
	private static int[] initTree;
	private static int[] tree;
	private static long[] initTreeB;
	private static long[] treeB;
	private static Pbs[] arr;

	private static void update(int node, int start, int end, int idx, long b) {
		int mid;

		tree[node]++;
		treeB[node] += b;
		if (start == end) {
			return;
		}
		mid = start + end >>> 1;
		if (idx <= mid) {
			update(node << 1, start, mid, idx, b);
		} else {
			update(node << 1 | 1, mid + 1, end, idx, b);
		}
	}

	private static int query(int node, int start, int end, long y, int idx) {
		int mid;

		if (start == end) {
			return idx + 1;
		}
		mid = start + end >>> 1;
		if (treeB[node << 1] > y) {
			return query(node << 1, start, mid, y, idx);
		}
		return query(node << 1 | 1, mid + 1, end, y - treeB[node << 1], idx + tree[node << 1]);
	}

	public static void main(String[] args) throws IOException {
		int q;
		int i;
		int mid;
		Pbs[] ans;
		Equipment[] equipments;
		PriorityQueue<Equipment> pq;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		equipments = new Equipment[n + 1];
		pq = new PriorityQueue<>((o1, o2) -> o1.b - o2.b);
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine(), DELIM, false);
			equipments[i] = new Equipment(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			pq.offer(equipments[i]);
		}
		Arrays.sort(equipments, 1, n + 1);
		for (i = 1; i <= n; i++) {
			pq.poll().idx = i;
		}
		q = Integer.parseInt(br.readLine());
		ans = new Pbs[q];
		arr = new Pbs[n + 1];
		mid = n + 2 >>> 1;
		for (i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine(), DELIM, false);
			arr[mid] = new Pbs(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()), arr[mid]);
			ans[i] = arr[mid];
		}
		initTree = new int[n << 2];
		tree = new int[n << 2];
		initTreeB = new long[n << 2];
		treeB = new long[n << 2];
		cnt = 0;
		while (cnt != q) {
			sum = 0L;
			System.arraycopy(initTree, 0, tree, 0, n << 2);
			System.arraycopy(initTreeB, 0, treeB, 0, n << 2);
			for (i = 1; i <= n; i++) {
				sum += equipments[i].a;
				update(1, 1, n, equipments[i].idx, equipments[i].b);
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
