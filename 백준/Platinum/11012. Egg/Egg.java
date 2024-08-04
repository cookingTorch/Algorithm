import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final int SIZE = 100_000;
	private static final int INITIAL_TREE = SIZE + 1;
	private static final char LINE_BREAK = '\n';

	private static final class Point implements Comparable<Point> {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.x, o.x);
		}
	}

	private static final class Node {
		int val;
		int tree;
		Node left;
		Node right;

		Node(int tree) {
			this.tree = tree;
		}

		Node(Node node, int tree) {
			this.val = node.val;
			this.tree = tree;
			this.left = node.left;
			this.right = node.right;
		}

		private static Node getInitialTree(int start, int end) {
			int mid;
			Node node;

			node = new Node(INITIAL_TREE);
			if (start == end) {
				return node;
			}
			mid = start + end >> 1;
			node.left = getInitialTree(start, mid);
			node.right = getInitialTree(mid + 1, end);
			return node;
		}

		private Node insert(int start, int end, int idx, int tree) {
			int mid;
			Node node;

			if (this.tree == tree) {
				node = this;
			} else {
				node = new Node(this, tree);
			}
			if (start == end) {
				node.val++;
				return node;
			}
			mid = start + end >> 1;
			if (idx <= mid) {
				node.left = node.left.insert(start, mid, idx, tree);
			} else {
				node.right = node.right.insert(mid + 1, end, idx, tree);
			}
			node.val = node.left.val + node.right.val;
			return node;
		}

		private static int query(Node l, Node r, int start, int end, int b, int t) {
			int mid;

			if (t < start || end < b) {
				return 0;
			}
			if (b <= start && end <= t) {
				return r.val - l.val;
			}
			mid = start + end >> 1;
			return query(l.left, r.left, start, mid, b, t) + query(l.right, r.right, mid + 1, end, b, t);
		}
	}

	private static BufferedReader br;

	private static int solution(Node[] trees) throws IOException {
		int n;
		int m;
		int x;
		int y;
		int l;
		int r;
		int b;
		int t;
		int i;
		int val;
		Point point;
		StringTokenizer st;
		PriorityQueue<Point> pq;

		for (i = 0; i < INITIAL_TREE; i++) {
			trees[i] = trees[INITIAL_TREE];
		}
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		pq = new PriorityQueue<>();
		while (n-- > 0) {
			st = new StringTokenizer(br.readLine());
			pq.offer(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		point = pq.poll();
		for (i = 0; i < INITIAL_TREE; i++) {
			trees[i] = trees[(i + INITIAL_TREE) % (INITIAL_TREE + 1)];
			while (point != null && point.x == i) {
				trees[i] = trees[i].insert(0, SIZE, point.y, i);
				point = pq.poll();
			}
		}
		val = 0;
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			l = (Integer.parseInt(st.nextToken()) + INITIAL_TREE) % (INITIAL_TREE + 1);
			r = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			val += Node.query(trees[l], trees[r], 0, SIZE, b, t);
		}
		return val;
	}

	public static void main(String[] args) throws IOException {
		int t;
		Node[] trees;
		StringBuilder sb;

		trees = new Node[INITIAL_TREE + 1];
		trees[INITIAL_TREE] = Node.getInitialTree(0, SIZE);
		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while (t-- > 0) {
			sb.append(solution(trees)).append(LINE_BREAK);
		}
		System.out.print(sb);
	}
}
