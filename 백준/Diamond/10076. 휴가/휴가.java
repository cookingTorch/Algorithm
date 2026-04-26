import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static final String DELIM = " ";

	private static int n;
	private static int start;
	private static long day;
	private static long answer;
	private static int nodeCnt;
	private static int valueCnt;
	private static int[] attraction;
	private static int[] values;
	private static int[] roots;
	private static int[] leftChild;
	private static int[] rightChild;
	private static int[] count;
	private static long[] sum;

	private static final int lowerBound(int[] arr, int size, int target) {
		int left;
		int right;
		int mid;

		left = 0;
		right = size;
		while (left < right) {
			mid = (left + right) >> 1;
			if (arr[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}

	private static final int update(int prev, int left, int right, int idx, int value) {
		int curr;
		int mid;

		curr = ++nodeCnt;
		leftChild[curr] = leftChild[prev];
		rightChild[curr] = rightChild[prev];
		count[curr] = count[prev] + 1;
		sum[curr] = sum[prev] + value;
		if (left == right) {
			return curr;
		}
		mid = (left + right) >> 1;
		if (idx <= mid) {
			leftChild[curr] = update(leftChild[prev], left, mid, idx, value);
		} else {
			rightChild[curr] = update(rightChild[prev], mid + 1, right, idx, value);
		}
		return curr;
	}

	private static final long query(int leftRoot, int rightRoot, int left, int right, int cnt) {
		int mid;
		int rightCount;
		long rightSum;

		if (cnt <= 0) {
			return 0L;
		}
		if (count[rightRoot] - count[leftRoot] <= cnt) {
			return sum[rightRoot] - sum[leftRoot];
		}
		if (left == right) {
			return (long) values[left] * cnt;
		}
		mid = (left + right) >> 1;
		rightCount = count[rightChild[rightRoot]] - count[rightChild[leftRoot]];
		rightSum = sum[rightChild[rightRoot]] - sum[rightChild[leftRoot]];
		if (rightCount >= cnt) {
			return query(rightChild[leftRoot], rightChild[rightRoot], mid + 1, right, cnt);
		}
		return rightSum + query(leftChild[leftRoot], leftChild[rightRoot], left, mid, cnt - rightCount);
	}

	private static final long getValue(int left, int right, long cnt) {
		if (cnt <= 0L) {
			return 0L;
		}
		if (cnt > right - left + 1) {
			cnt = right - left + 1;
		}
		return query(roots[left], roots[right + 1], 0, valueCnt - 1, (int) cnt);
	}

	private static final void build() {
		int i;
		int idx;
		int[] sorted;

		sorted = Arrays.copyOf(attraction, n);
		Arrays.sort(sorted);
		values = new int[n];
		valueCnt = 0;
		for (i = 0; i < n; i++) {
			if (i == 0 || sorted[i] != sorted[i - 1]) {
				values[valueCnt++] = sorted[i];
			}
		}
		nodeCnt = 0;
		roots = new int[n + 1];
		leftChild = new int[(n * 20) + 5];
		rightChild = new int[(n * 20) + 5];
		count = new int[(n * 20) + 5];
		sum = new long[(n * 20) + 5];
		for (i = 0; i < n; i++) {
			idx = lowerBound(values, valueCnt, attraction[i]);
			roots[i + 1] = update(roots[i], 0, valueCnt - 1, idx, attraction[i]);
		}
	}

	private static final void solve(int rightLeft, int rightRight, int leftLow, int leftHigh) {
		int i;
		int mid;
		int best;
		int scanLeft;
		int scanRight;
		long remain;
		long minLeft;
		long cnt;
		long value;
		long maxValue;

		if (rightLeft > rightRight || leftLow > leftHigh) {
			return;
		}
		mid = (rightLeft + rightRight) >> 1;
		remain = day - (mid - start);
		minLeft = start - (remain >> 1);
		if (minLeft < 0L) {
			minLeft = 0L;
		}
		scanLeft = Math.max(leftLow, (int) minLeft);
		scanRight = Math.min(leftHigh, start);
		best = scanLeft;
		maxValue = 0L;
		for (i = scanLeft; i <= scanRight; i++) {
			cnt = day - (2L * (start - i) + (mid - start));
			value = getValue(i, mid, cnt);
			if (value > maxValue) {
				maxValue = value;
				best = i;
			}
		}
		answer = Math.max(answer, maxValue);
		solve(rightLeft, mid - 1, leftLow, best);
		solve(mid + 1, rightRight, best, leftHigh);
	}

	private static final void calculate() {
		int rightEnd;

		build();
		rightEnd = n - 1;
		if (day < rightEnd - start) {
			rightEnd = (int) (start + day);
		}
		solve(start, rightEnd, 0, start);
	}

	private static final void reverse() {
		int i;
		int j;
		int temp;

		i = 0;
		j = n - 1;
		while (i < j) {
			temp = attraction[i];
			attraction[i] = attraction[j];
			attraction[j] = temp;
			i++;
			j--;
		}
		start = n - 1 - start;
	}

	public static void main(String[] args) throws IOException {
		int i;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), DELIM, false);
		n = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		day = Long.parseLong(st.nextToken());
		attraction = new int[n];
		st = new StringTokenizer(br.readLine(), DELIM, false);
		for (i = 0; i < n; i++) {
			attraction[i] = Integer.parseInt(st.nextToken());
		}
		answer = 0L;
		calculate();
		reverse();
		calculate();
		System.out.println(answer);
	}
}