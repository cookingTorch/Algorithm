import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static final int MAX_BIT = 29;
	private static final String DELIM = " ";

	private static int nodeCnt;
	private static int[] arr;
	private static int[] leftChild;
	private static int[] rightChild;

	private static final void insert(int value, int bit) {
		int i;
		int curr;

		curr = 1;
		for (i = bit; i >= 0; i--) {
			if ((value & (1 << i)) == 0) {
				if (leftChild[curr] == 0) {
					leftChild[curr] = ++nodeCnt;
					leftChild[nodeCnt] = 0;
					rightChild[nodeCnt] = 0;
				}
				curr = leftChild[curr];
			} else {
				if (rightChild[curr] == 0) {
					rightChild[curr] = ++nodeCnt;
					leftChild[nodeCnt] = 0;
					rightChild[nodeCnt] = 0;
				}
				curr = rightChild[curr];
			}
		}
	}

	private static final int query(int value, int bit) {
		int i;
		int curr;
		int ret;

		curr = 1;
		ret = 0;
		for (i = bit; i >= 0; i--) {
			if ((value & (1 << i)) == 0) {
				if (leftChild[curr] != 0) {
					curr = leftChild[curr];
				} else {
					ret += 1 << i;
					curr = rightChild[curr];
				}
			} else {
				if (rightChild[curr] != 0) {
					curr = rightChild[curr];
				} else {
					ret += 1 << i;
					curr = leftChild[curr];
				}
			}
		}
		return ret;
	}

	private static final int getMinXor(int left1, int right1, int left2, int right2, int bit) {
		int i;
		int ret;

		nodeCnt = 1;
		leftChild[1] = 0;
		rightChild[1] = 0;
		if (right1 - left1 <= right2 - left2) {
			for (i = left1; i <= right1; i++) {
				insert(arr[i], bit);
			}
			ret = Integer.MAX_VALUE;
			for (i = left2; i <= right2; i++) {
				ret = Math.min(ret, query(arr[i], bit));
			}
		} else {
			for (i = left2; i <= right2; i++) {
				insert(arr[i], bit);
			}
			ret = Integer.MAX_VALUE;
			for (i = left1; i <= right1; i++) {
				ret = Math.min(ret, query(arr[i], bit));
			}
		}
		return ret;
	}

	private static final long solve(int left, int right, int bit) {
		int mid;

		if (left >= right || bit < 0) {
			return 0L;
		}
		mid = left;
		while (mid <= right && (arr[mid] & (1 << bit)) == 0) {
			mid++;
		}
		if (mid == left || mid > right) {
			return solve(left, right, bit - 1);
		}
		return solve(left, mid - 1, bit - 1) + solve(mid, right, bit - 1) + getMinXor(left, mid - 1, mid, right, bit);
	}

	public static void main(String[] args) throws IOException {
		int n;
		int i;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine(), DELIM, false);
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		leftChild = new int[(n * (MAX_BIT + 1)) + 2];
		rightChild = new int[(n * (MAX_BIT + 1)) + 2];
		System.out.println(solve(0, n - 1, MAX_BIT));
	}
}