import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int MAX_X = 500000;
	private static final char ADD = '1';
	private static final char XOR = '2';
	private static final char DELETE = '3';
	private static final char SMALL = '4';
	private static final char NTH = '5';
	
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
		
		final Node add(int start, int end, int num) {
			int mid;
			Node node;
			
			node = new Node();
			if (start != end) {
				mid = start + end >> 1;
				if (num <= mid) {
					node.left = left.add(start, mid, num);
					node.right = right;
				} else {
					node.left = left;
					node.right = right.add(mid + 1, end, num);
				}
			}
			node.val = val + 1;
			return node;
		}
	}
	
	private static Node[] trees;
	
	private static final int xor(Node l, Node r, int start, int end, int bit, int num) {
		int mid;
		
		if (start == end) {
			return start;
		}
		mid = start + end >> 1;
		if ((r.left.val - l.left.val != 0 && (bit & num) != 0) || r.right.val - l.right.val == 0) {
			return xor(l.left, r.left, start, mid, bit >> 1, num);
		}
		return xor(l.right, r.right, mid + 1, end, bit >> 1, num);
	}
	
	private static final int small(Node l, Node r, int start, int end, int num) {
		int mid;
		
		if (start > num) {
			return 0;
		}
		if (end <= num) {
			return r.val - l.val;
		}
		mid = start + end >> 1;
		return small(l.left, r.left, start, mid, num) + small(l.right, r.right, mid + 1, end, num);
	}
	
	private static final int nth(Node l, Node r, int start, int end, int num) {
		int mid;
		int val;
		
		if (start == end) {
			return start;
		}
		mid = start + end >> 1;
		val = r.left.val - l.left.val;
		if (val >= num) {
			return nth(l.left, r.left, start, mid, num);
		}
		return nth(l.right, r.right, mid + 1, end, num - val);
	}
	
	public static void main(String[] args) throws IOException {
		int m;
		int i;
		int idx;
		int max;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		m = Integer.parseInt(br.readLine());
		trees = new Node[m + 1];
		trees[0] = new Node();
		for (max = 1; max <= MAX_X; max <<= 1);
		max--;
		trees[0].init(0, max);
		idx = 0;
		sb = new StringBuilder();
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken().charAt(0)) {
			case ADD:
				trees[idx + 1] = trees[idx].add(0, max, Integer.parseInt(st.nextToken()));
				idx++;
				break;
			case XOR:
				sb.append(xor(trees[Integer.parseInt(st.nextToken()) - 1], trees[Integer.parseInt(st.nextToken())], 0, max, (max + 1) >> 1, Integer.parseInt(st.nextToken()))).append('\n');
				break;
			case DELETE:
				idx -= Integer.parseInt(st.nextToken());
				break;
			case SMALL:
				sb.append(small(trees[Integer.parseInt(st.nextToken()) - 1], trees[Integer.parseInt(st.nextToken())], 0, max, Integer.parseInt(st.nextToken()))).append('\n');
				break;
			case NTH:
				sb.append(nth(trees[Integer.parseInt(st.nextToken()) - 1], trees[Integer.parseInt(st.nextToken())], 0, max, Integer.parseInt(st.nextToken()))).append('\n');
			}
		}
		System.out.print(sb);
	}
}
