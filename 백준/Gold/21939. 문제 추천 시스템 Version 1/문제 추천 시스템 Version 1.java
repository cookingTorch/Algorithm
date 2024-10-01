import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE >> 1;
	private static final int MAX_P = 100_001;
	private static final int SHIFT = 17;
	private static final int MOD = (1 << SHIFT) - 1;
	private static final int RECOMMEND = 9;
	private static final int ADD = 3;
	private static final int SOLVED = 6;
	private static final char MAX = '1';
	private static final char MIN = '-';
	private static final char LINE_BREAK = '\n';

	private static int[] maxLazy;
	private static int[] minLazy;
	private static char[] times;
	private static StringBuilder sb;

	private static final class Range {
		int left;
		int right;
		int val;

		Range(int left, int val) {
			this.left = left;
			this.right = INF;
			this.val = val;
		}
	}

	private static final void init(int node, int start, int end) {
		int mid;

		minLazy[node] = INF;
		if (start == end) {
			return;
		}
		mid = start + end >> 1;
		init(node << 1, start, mid);
		init(node << 1 | 1, mid + 1, end);
	}

	private static final void update(int node, int start, int end, int left, int right, int val) {
		int mid;

		if (right < start || end < left) {
			return;
		}
		if (left <= start && end <= right) {
			maxLazy[node] = Math.max(maxLazy[node], val);
			minLazy[node] = Math.min(minLazy[node], val);
			return;
		}
		mid = start + end >> 1;
		update(node << 1, start, mid, left, right, val);
		update(node << 1 | 1, mid + 1, end, left, right, val);
	}

	private static final void prop(int node, int start, int end) {
		int mid;

		if (start == end) {
			if (times[start] == MAX) {
				sb.append(maxLazy[node] & MOD).append(LINE_BREAK);
			} else if (times[start] == MIN) {
				sb.append(minLazy[node] & MOD).append(LINE_BREAK);
			}
			return;
		}
		maxLazy[node << 1] = Math.max(maxLazy[node], maxLazy[node << 1]);
		maxLazy[node << 1 | 1] = Math.max(maxLazy[node], maxLazy[node << 1 | 1]);
		minLazy[node << 1] = Math.min(minLazy[node], minLazy[node << 1]);
		minLazy[node << 1 | 1] = Math.min(minLazy[node], minLazy[node << 1 | 1]);
		mid = start + end >> 1;
		prop(node << 1, start, mid);
		prop(node << 1 | 1, mid + 1, end);
	}

	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int p;
		int i;
		int time;
		Range[] ranges;
		ArrayList<Range> rangeList;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		ranges = new Range[MAX_P];
		while (n-- > 0) {
			st = new StringTokenizer(br.readLine(), " ", false);
			p = Integer.parseInt(st.nextToken());
			ranges[p] = new Range(0, (Integer.parseInt(st.nextToken()) << SHIFT) | p);
		}
		m = Integer.parseInt(br.readLine());
		rangeList = new ArrayList<>(m + 1);
		time = 1;
		times = new char[m + 1];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ", false);
			switch (st.nextToken().length()) {
				case RECOMMEND:
					times[time++] = st.nextToken().charAt(0);
					break;
				case ADD:
					p = Integer.parseInt(st.nextToken());
					ranges[p] = new Range(time, (Integer.parseInt(st.nextToken()) << SHIFT) | p);
					break;
				case SOLVED:
					p = Integer.parseInt(st.nextToken());
					if (ranges[p].left < time) {
						ranges[p].right = time - 1;
						rangeList.add(ranges[p]);
					}
					ranges[p] = null;
					break;
			}
		}
		maxLazy = new int[time << 2];
		minLazy = new int[time << 2];
		init(1, 1, time);
		for (Range range : ranges) {
			if (range == null) {
				continue;
			}
			update(1, 1, time, range.left, range.right, range.val);
		}
		for (Range range : rangeList) {
			update(1, 1, time, range.left, range.right, range.val);
		}
		sb = new StringBuilder();
		prop(1, 1, time);
		System.out.print(sb.toString());
	}
}
