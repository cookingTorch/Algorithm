import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final String DELIM = " ";

	private static final class Node {
		int sum;
		Node left;
		Node right;

		Node() {
		}

		Node(int sum) {
			this.sum = sum;
		}

		final void init(int start, int end) {
			int mid;

			if (start == end) {
				return;
			}
			left = new Node();
			right = new Node();
			mid = start + end >> 1;
			left.init(start, mid);
			right.init(mid + 1, end);
		}

		final Node attach(int start, int end, int idx) {
			int mid;
			Node newNode;

			newNode = new Node(sum + 1);
			if (start < end) {
				mid = start + end >> 1;
				if (idx <= mid) {
					newNode.left = left.attach(start, mid, idx);
					newNode.right = right;
				} else {
					newNode.left = left;
					newNode.right = right.attach(mid + 1, end, idx);
				}
			}
			return newNode;
		}
	}

	private static int n;
	private static int[] arr;
	private static Node[] pst;

	private static final int getPivot(Node lt, Node rt, int start, int end, int idx) {
		int mid;
		int lSum;

		if (start == end) {
			return arr[start];
		}
		mid = start + end >> 1;
		lSum = rt.left.sum - lt.left.sum;
		if (idx <= lSum) {
			return getPivot(lt.left, rt.left, start, mid, idx);
		}
		return getPivot(lt.right, rt.right, mid + 1, end, idx - lSum);
	}

	private static final long cnt(int start, int end) {
		int pivot;

		if (start >= end) {
			return 0L;
		}
		pivot = getPivot(pst[start - 1], pst[end], 1, n, (end - start >> 1) + 1);
		return cnt(start, pivot - 1) + cnt(pivot + 1, end) + (end - start + 1);
	}

	public static void main(String[] args) throws IOException {
		int i;
		int num;
		int[] pos;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		pos = new int[n + 1];
		arr = new int[n + 1];
		st = new StringTokenizer(br.readLine(), DELIM, false);
		for (i = 1; i <= n; i++) {
			num = Integer.parseInt(st.nextToken());
			pos[num] = i;
			arr[i] = num;
		}
		pst = new Node[n + 1];
		pst[0] = new Node();
		pst[0].init(1, n);
		for (i = 1; i <= n; i++) {
			pst[i] = pst[i - 1].attach(1, n, pos[i]);
		}
		System.out.print(cnt(1, n));
	}
}
