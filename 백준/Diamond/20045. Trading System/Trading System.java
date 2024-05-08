import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
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
			this.val = left.val + right.val;
		}
		
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
			Node child;
			
			if (start == end) {
				return new Node(val + 1);
			}
			mid = start + end >> 1;
			if (num <= mid) {
				child = left.add(start, mid, num);
				return new Node(child, right);
			} else {
				child = right.add(mid + 1, end, num);
				return new Node(left, child);
			}
		}
		
		final int nth(int start, int end, int num) {
			int mid;
			
			if (start == end) {
				return start;
			}
			mid = start + end >> 1;
			if (left.val >= num) {
				return left.nth(start, mid, num);
			}
			return right.nth(mid + 1, end, num - left.val);
		}
	}
	
	private static final class Result implements Comparable<Result> {
		int end;
		int rank;
		long ans;
		
		Result(int end) {
			this.end = end;
			this.rank = 1;
			calc();
		}
		
		final Result calc() {
			ans = prefix[end] - unique[trees[end - 1].nth(0, max, rank)];
			return this;
		}
		
		@Override
		public int compareTo(Result o) {
			return Long.compare(o.ans, this.ans);
		}
	}
	
	private static int max;
	private static long[] prefix;
	private static long[] unique;
	private static Node[] trees;
	
	private static final int convert(long num) {
		int mid;
		int left;
		int right;
		
		left = 0;
		right = max;
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
	
	public static void main(String[] args) throws IOException {
		int n;
		int k;
		int i;
		Result result;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		PriorityQueue<Result> pq;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		prefix = new long[n + 1];
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			prefix[i] = prefix[i - 1] + Integer.parseInt(st.nextToken());
		}
		unique = Arrays.stream(prefix).distinct().sorted().toArray();
		max = unique.length - 1;
		trees = new Node[n + 1];
		trees[0] = new Node();
		trees[0].init(0, max);
		trees[0] = trees[0].add(0, max, convert(0L));
		pq = new PriorityQueue<>();
		for (i = 1; i <= n; i++) {
			trees[i] = trees[i - 1].add(0, max, convert(prefix[i]));
			pq.add(new Result(i));
		}
		sb = new StringBuilder();
		for (i = 0; i < k; i++) {
			result = pq.poll();
			sb.append(result.ans).append(' ');
			if (++result.rank <= result.end) {
				pq.offer(result.calc());
			}
		}
		System.out.print(sb);
	}
}
