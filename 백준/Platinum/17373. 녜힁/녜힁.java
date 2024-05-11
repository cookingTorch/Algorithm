import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final class Node {
		int val;
		Node left;
		Node right;
		
		Node() {
		}
		
		Node(int val) {
			this.val = val;
		}
		
		Node(Node left, Node right) {
			this.left = left;
			this.right = right;
			val = left.val + right.val;
		}
		
		static Node getInitialTree() {
			Node tree;
			
			tree = new Node();
			tree.init(0, m - 1);
			return tree;
		}
		
		final void init(int start, int end) {
			int mid;
			
			if (start == end) {
				val = 1;
				return;
			}
			mid = start + end >> 1;
			left = new Node();
			right = new Node();
			left.init(start, mid);
			right.init(mid + 1, end);
			val = left.val + right.val;
		}
		
		final Node delete(int start, int end, int num) {
			int mid;
			
			if (start == end) {
				return new Node(0);
			}
			mid = start + end >> 1;
			if (num <= mid) {
				return new Node(left.delete(start, mid, num), right);
			}
			return new Node(left, right.delete(mid + 1, end, num));
		}
		
		final int query(int start, int end, int num) {
			int mid;
			
			if (start == end) {
				return start;
			}
			mid = start + end >> 1;
			if (left.val >= num) {
				return left.query(start, mid, num);
			}
			return right.query(mid + 1, end, num - left.val);
		}
	}
	
	private static int n;
	private static int m;
	private static int[] arr;
	private static int[] last;
	private static int[] first;
	private static long[] back;
	private static Node[] trees;
	private static BufferedReader br;
	
	private static final void buildArrays() throws IOException {
		int i;
		int[] byIdx;
		long longM;
		boolean[] visited;
		StringTokenizer st;
		
		arr = new int[n];
		first = new int[m];
		last = new int[m];
		for (i = 0; i < m; i++) {
			first[i] = -1;
			last[i] = -1;
		}
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken()) - 1;
			if (first[arr[i]] == -1) {
				first[arr[i]] = i;
			}
			last[arr[i]] = i;
		}
		visited = new boolean[m];
		byIdx = new int[n];
		byIdx[n - 1] = m;
		for (i = n - 1; i > 0; i--) {
			if (first[arr[i]] == -1) {
				byIdx[i - 1] = m;
				continue;
			}
			if (visited[arr[i]]) {
				byIdx[i - 1] = byIdx[i];
				continue;
			}
			visited[arr[i]] = true;
			byIdx[i - 1] = byIdx[i] - 1;
		}
		longM = m;
		back = new long[m];
		for (i = 0; i < m; i++) {
			if (first[i] == -1) {
				back[i] = longM;
				continue;
			}
			back[i] = byIdx[first[i]];
		}
		for (i = 1; i < m; i++) {
			back[i] += back[i - 1];
		}
	}
	
	private static final void buildTrees() {
		int i;
		
		trees = new Node[n];
		trees[n - 1] = Node.getInitialTree();
		for (i = n - 1; i > 0; i--) {
			if (last[arr[i]] == i) {
				trees[i - 1] = trees[i].delete(0, m - 1, arr[i]);
			} else {
				trees[i - 1] = trees[i];
			}
		}
	}
	
	private static int getFirstNum(long k) {
		int mid;
		int left;
		int right;
		
		left = 0;
		right = m;
		while (left < right) {
			mid = left + right >> 1;
			if (back[mid] < k) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}
	
	public static void main(String[] args) throws IOException {
		int q;
		int i;
		int num;
		int firstNum;
		int secondNum;
		long k;
		StringBuilder sb;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		buildArrays();
		buildTrees();
		sb = new StringBuilder();
		for (i = 0; i < q; i++) {
			k = Long.parseLong(br.readLine());
			if (k > back[m - 1]) {
				sb.append("-1 -1\n");
				continue;
			}
			firstNum = getFirstNum(k);
			if (firstNum != 0) {
				num = (int) (k - back[firstNum - 1]);
			} else {
				num = (int) k;
			}
			if (first[firstNum] == -1) {
				secondNum = num - 1;
			} else {
				secondNum = trees[first[firstNum]].query(0, m - 1, num);
			}
			sb.append(firstNum + 1).append(' ').append(secondNum + 1).append('\n');
		}
		System.out.print(sb);
	}
}
