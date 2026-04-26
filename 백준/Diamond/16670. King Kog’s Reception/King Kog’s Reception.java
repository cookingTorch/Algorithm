import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static final int MAX_TIME = 1000000;
	private static final long NEG_INF = -(1L << 60);
	private static final String DELIM = " ";

	private static int size;
	private static long[] sum;
	private static long[] best;

	private static final void pull(int node) {
		sum[node] = sum[node << 1] + sum[(node << 1) | 1];
		best[node] = Math.max(best[node << 1] + sum[(node << 1) | 1], best[(node << 1) | 1]);
	}

	private static final void update(int idx, long duration) {
		int node;

		node = size + idx - 1;
		if (duration == 0L) {
			sum[node] = 0L;
			best[node] = NEG_INF;
		} else {
			sum[node] = duration;
			best[node] = idx + duration;
		}
		node >>= 1;
		while (node > 0) {
			pull(node);
			node >>= 1;
		}
	}

	private static final long query(int right) {
		int l;
		int r;
		long leftSum;
		long leftBest;
		long rightSum;
		long rightBest;
		long nextSum;
		long nextBest;

		l = size;
		r = size + right - 1;
		leftSum = 0L;
		leftBest = NEG_INF;
		rightSum = 0L;
		rightBest = NEG_INF;
		while (l <= r) {
			if ((l & 1) == 1) {
				nextSum = leftSum + sum[l];
				nextBest = Math.max(leftBest + sum[l], best[l]);
				leftSum = nextSum;
				leftBest = nextBest;
				l++;
			}
			if ((r & 1) == 0) {
				nextSum = sum[r] + rightSum;
				nextBest = Math.max(best[r] + rightSum, rightBest);
				rightSum = nextSum;
				rightBest = nextBest;
				r--;
			}
			l >>= 1;
			r >>= 1;
		}
		nextSum = leftSum + rightSum;
		nextBest = Math.max(leftBest + rightSum, rightBest);
		return Math.max(nextSum, nextBest);
	}

	public static void main(String[] args) throws IOException {
		int q;
		int i;
		int t;
		int d;
		int eventIdx;
		long finish;
		long wait;
		char command;
		int[] joinTime;
		int[] joinDuration;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		q = Integer.parseInt(br.readLine());
		size = 1;
		while (size < MAX_TIME) {
			size <<= 1;
		}
		sum = new long[size << 1];
		best = new long[size << 1];
		Arrays.fill(best, NEG_INF);
		joinTime = new int[q + 1];
		joinDuration = new int[q + 1];
		for (i = 1; i <= q; i++) {
			st = new StringTokenizer(br.readLine(), DELIM, false);
			command = st.nextToken().charAt(0);
			if (command == '+') {
				t = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken());
				joinTime[i] = t;
				joinDuration[i] = d;
				update(t, d);
			} else if (command == '-') {
				eventIdx = Integer.parseInt(st.nextToken());
				update(joinTime[eventIdx], 0L);
			} else {
				t = Integer.parseInt(st.nextToken());
				finish = query(t);
				wait = finish - t;
				if (wait < 0L) {
					wait = 0L;
				}
				sb.append(wait).append('\n');
			}
		}
		System.out.print(sb);
	}
}