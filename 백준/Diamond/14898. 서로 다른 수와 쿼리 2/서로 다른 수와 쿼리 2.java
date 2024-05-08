import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static final class Node {
		int val;
		Node left;
		Node right;
		
		final void init(int start, int end) {
			int mid;
			
			if (start == end) {
				return;
			}
			mid = start + end >> 1;
			left = new Node();
			right = new Node();
			left.init(start, mid);
			right.init(mid + 1, end);
		}
		
		final Node delete(int start, int end, int idx) {
			int mid;
			Node node;
			
			node = new Node();
			if (start != end) {
				mid = start + end >> 1;
				if (idx <= mid) {
					node.left = left.delete(start, mid, idx);
					node.right = right;
				} else {
					node.left = left;
					node.right = right.delete(mid + 1, end, idx);
				}
			}
			node.val = val - 1;
			return node;
		}
		
		final Node add(int start, int end, int idx) {
			int mid;
			Node node;
			
			node = new Node();
			if (start != end) {
				mid = start + end >> 1;
				if (idx <= mid) {
					node.left = left.add(start, mid, idx);
					node.right = right;
				} else {
					node.left = left;
					node.right = right.add(mid + 1, end, idx);
				}
			}
			node.val = val + 1;
			return node;
		}
		
		final Node update(int start, int end, int delIdx, int addIdx) {
			int mid;
			Node node;
			
			if (delIdx == -1) {
				return add(start, end, addIdx);
			}
			node = new Node();
			if (start != end) {
				mid = start + end >> 1;
				if (addIdx <= mid) {
					node.left = left.update(start, mid, delIdx, addIdx);
					node.right = right;
				} else if (delIdx <= mid) {
					node.left = left.delete(start, mid, delIdx);
					node.right = right.add(mid + 1, end, addIdx);
				} else {
					node.left = left;
					node.right = right.update(mid + 1, end, delIdx, addIdx);
				}
			}
			node.val = val;
			return node;
		}
		
		final int query(int start, int end, int l, int r) {
			int mid;
			
			if (end < l || r < start) {
				return 0;
			}
			if (l <= start && end <= r) {
				return val;
			}
			mid = start + end >> 1;
			return left.query(start, mid, l, r) + right.query(mid + 1, end, l, r);
		}
	}
	
	private static final int getIdx(int[] unique, int num, int right) {
		int mid;
		int left;
		
		left = 0;
		while (left < right) {
			mid = left + right >> 1;
			if (unique[mid] < num) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}
	
	private static final int[] getPrev(int[] arr, int size) {
		int i;
		int idx;
		int len;
		int[] work;
		int[] prev;
		int[] unique;
		
		prev = new int[size];
		unique = Arrays.stream(arr).distinct().sorted().toArray();
		len = unique.length;
		work = new int[len];
		for (i = 0; i < len; i++) {
			work[i] = -1;
		}
		for (i = 0; i < size; i++) {
			idx = getIdx(unique, arr[i], len);
			prev[i] = work[idx];
			work[idx] = i;
		}
		return prev;
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int q;
		int l;
		int r;
		int i;
		int end;
		int ans;
		int[] arr;
		int[] prev;
		Node[] trees;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		prev = getPrev(arr, n);
		end = n - 1;
		trees = new Node[n + 1];
		trees[0] = new Node();
		trees[0].init(0, end);
		for (i = 0; i < n; i++) {
			trees[i + 1] = trees[i].update(0, end, prev[i], i);
		}
		sb = new StringBuilder();
		q = Integer.parseInt(br.readLine());
		for (i = 0, ans = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			l = ans + Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			ans = trees[r].query(0, end, l - 1, r - 1);
			sb.append(ans).append('\n');
		}
		System.out.print(sb);
	}
}
