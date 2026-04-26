import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static final String DELIM = " ";

	private static int producerCnt;
	private static int consumerCnt;
	private static int[] producerPrice;
	private static int[] producerDate;
	private static int[] consumerPrice;
	private static int[] consumerDate;
	private static int[] low;
	private static int[] high;
	private static long answer;

	private static final long pack(int a, int b) {
		return ((long) a << 32) | b;
	}

	private static final int getFirst(long value) {
		return (int) (value >> 32);
	}

	private static final int getSecond(long value) {
		return (int) value;
	}

	private static final int lowerBoundPrice(int target) {
		int left;
		int right;
		int mid;

		left = 0;
		right = producerCnt;
		while (left < right) {
			mid = (left + right) >> 1;
			if (producerPrice[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}

	private static final int lowerBoundDate(int target) {
		int left;
		int right;
		int mid;

		left = 0;
		right = producerCnt;
		while (left < right) {
			mid = (left + right) >> 1;
			if (producerDate[mid] < target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	private static final long getProfit(int producerIdx, int consumerIdx) {
		return (long) (consumerPrice[consumerIdx] - producerPrice[producerIdx]) * (consumerDate[consumerIdx] - producerDate[producerIdx]);
	}

	private static final void solve(int cLeft, int cRight, int pLeft, int pRight) {
		int i;
		int mid;
		int best;
		int start;
		int end;
		long profit;
		long maxProfit;

		if (cLeft > cRight) {
			return;
		}
		mid = (cLeft + cRight) >> 1;
		start = Math.max(pLeft, low[mid]);
		end = Math.min(pRight, high[mid]);
		best = start;
		maxProfit = 0L;
		for (i = start; i <= end; i++) {
			profit = getProfit(i, mid);
			if (profit > maxProfit) {
				maxProfit = profit;
				best = i;
			}
		}
		answer = Math.max(answer, maxProfit);
		solve(cLeft, mid - 1, pLeft, best);
		solve(mid + 1, cRight, best, pRight);
	}

	public static void main(String[] args) throws IOException {
		int m;
		int n;
		int i;
		int p;
		int d;
		int q;
		int e;
		int minDate;
		int maxDate;
		int validCnt;
		int left;
		int right;
		long[] producers;
		long[] consumers;
		int[] tempPrice;
		int[] tempDate;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), DELIM, false);
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		producers = new long[m];
		consumers = new long[n];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), DELIM, false);
			p = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			producers[i] = pack(p, d);
		}
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), DELIM, false);
			q = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			consumers[i] = pack(q, e);
		}
		Arrays.sort(producers);
		producerPrice = new int[m];
		producerDate = new int[m];
		minDate = Integer.MAX_VALUE;
		producerCnt = 0;
		for (i = 0; i < m; i++) {
			p = getFirst(producers[i]);
			d = getSecond(producers[i]);
			if (d < minDate) {
				producerPrice[producerCnt] = p;
				producerDate[producerCnt++] = d;
				minDate = d;
			}
		}
		Arrays.sort(consumers);
		tempPrice = new int[n];
		tempDate = new int[n];
		maxDate = 0;
		consumerCnt = 0;
		for (i = n - 1; i >= 0; i--) {
			q = getFirst(consumers[i]);
			e = getSecond(consumers[i]);
			if (e > maxDate) {
				tempPrice[consumerCnt] = q;
				tempDate[consumerCnt++] = e;
				maxDate = e;
			}
		}
		consumerPrice = new int[consumerCnt];
		consumerDate = new int[consumerCnt];
		low = new int[consumerCnt];
		high = new int[consumerCnt];
		validCnt = 0;
		for (i = consumerCnt - 1; i >= 0; i--) {
			q = tempPrice[i];
			e = tempDate[i];
			left = lowerBoundDate(e);
			right = lowerBoundPrice(q) - 1;
			if (left <= right) {
				consumerPrice[validCnt] = q;
				consumerDate[validCnt] = e;
				low[validCnt] = left;
				high[validCnt++] = right;
			}
		}
		consumerCnt = validCnt;
		answer = 0L;
		if (producerCnt > 0 && consumerCnt > 0) {
			solve(0, consumerCnt - 1, 0, producerCnt - 1);
		}
		System.out.println(answer);
	}
}