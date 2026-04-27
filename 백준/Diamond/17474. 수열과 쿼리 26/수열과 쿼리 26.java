import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	
	private static int n;
	private static int[] max;
	private static int[] smax;
	private static int[] maxCnt;
	private static long[] sum;
	
	private static final void pull(int curr) {
		int left;
		int right;
		
		left = curr << 1;
		right = left | 1;
		sum[curr] = sum[left] + sum[right];
		if (max[left] > max[right]) {
			max[curr] = max[left];
			maxCnt[curr] = maxCnt[left];
			smax[curr] = Math.max(smax[left], max[right]);
		} else if (max[left] < max[right]) {
			max[curr] = max[right];
			maxCnt[curr] = maxCnt[right];
			smax[curr] = Math.max(max[left], smax[right]);
		} else {
			max[curr] = max[left];
			maxCnt[curr] = maxCnt[left] + maxCnt[right];
			smax[curr] = Math.max(smax[left], smax[right]);
		}
	}
	
	private static final void apply(int curr, int x) {
		if (max[curr] <= x) {
			return;
		}
		sum[curr] -= (long) (max[curr] - x) * maxCnt[curr];
		max[curr] = x;
	}
	
	private static final void push(int curr) {
		int left;
		int right;
		
		left = curr << 1;
		right = left | 1;
		apply(left, max[curr]);
		apply(right, max[curr]);
	}
	
	private static final void init(int curr, int left, int right, StringTokenizer st) {
		int mid;
		
		if (left == right) {
			max[curr] = Integer.parseInt(st.nextToken());
			smax[curr] = -1;
			maxCnt[curr] = 1;
			sum[curr] = max[curr];
			return;
		}
		mid = (left + right) >> 1;
		init(curr << 1, left, mid, st);
		init(curr << 1 | 1, mid + 1, right, st);
		pull(curr);
	}
	
	private static final void update(int curr, int left, int right, int start, int end, int x) {
		int mid;
		
		if (right < start || end < left || max[curr] <= x) {
			return;
		}
		if (start <= left && right <= end && smax[curr] < x) {
			apply(curr, x);
			return;
		}
		push(curr);
		mid = (left + right) >> 1;
		update(curr << 1, left, mid, start, end, x);
		update(curr << 1 | 1, mid + 1, right, start, end, x);
		pull(curr);
	}
	
	private static final int queryMax(int curr, int left, int right, int start, int end) {
		int mid;
		int ret;
		
		if (right < start || end < left) {
			return 0;
		}
		if (start <= left && right <= end) {
			return max[curr];
		}
		push(curr);
		mid = (left + right) >> 1;
		ret = queryMax(curr << 1, left, mid, start, end);
		return Math.max(ret, queryMax(curr << 1 | 1, mid + 1, right, start, end));
	}
	
	private static final long querySum(int curr, int left, int right, int start, int end) {
		int mid;
		long ret;
		
		if (right < start || end < left) {
			return 0;
		}
		if (start <= left && right <= end) {
			return sum[curr];
		}
		push(curr);
		mid = (left + right) >> 1;
		ret = querySum(curr << 1, left, mid, start, end);
		return ret + querySum(curr << 1 | 1, mid + 1, right, start, end);
	}
	
	public static void main(String[] args) throws IOException {
		int m;
		int q;
		int l;
		int r;
		int x;
		int type;
		BufferedReader br;
		StringTokenizer st;
		StringBuilder sb;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		max = new int[(n << 2) + 5];
		smax = new int[(n << 2) + 5];
		maxCnt = new int[(n << 2) + 5];
		sum = new long[(n << 2) + 5];
		st = new StringTokenizer(br.readLine());
		init(1, 1, n, st);
		m = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		for (q = 0; q < m; q++) {
			st = new StringTokenizer(br.readLine());
			type = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			if (type == 1) {
				x = Integer.parseInt(st.nextToken());
				update(1, 1, n, l, r, x);
			} else if (type == 2) {
				sb.append(queryMax(1, 1, n, l, r)).append('\n');
			} else {
				sb.append(querySum(1, 1, n, l, r)).append('\n');
			}
		}
		System.out.print(sb);
	}
}