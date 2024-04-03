import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int START = -10000;
	private static final int END = 10000;
	
	private static int[] tree;
	
	private static void update(int node, int start, int end, int num) {
		int mid;
		
		tree[node]++;
		if (start == end) {
			return;
		}
		mid = (start + end) >> 1;
		if (num > mid) {
			update(node * 2 + 1, mid + 1, end, num);
		} else {
			update(node * 2, start, mid, num);
		}
	}
	
	private static int search(int node, int start, int end, int idx) {
		int mid;
		
		if (start == end) {
			return start;
		}
		mid = (start + end) >> 1;
		if (tree[node * 2] < idx) {
			return search(node * 2 + 1, mid + 1, end, idx - tree[node * 2]);
		}
		return search(node * 2, start, mid, idx);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n, i;
		
		tree = new int[4 * (END - START)];
		n = Integer.parseInt(br.readLine());
		for (i = 0; i < n; i++) {
			update(1, START, END, Integer.parseInt(br.readLine()));
			sb.append(search(1, START, END, (i >> 1) + 1)).append('\n');
		}
		System.out.print(sb);
	}
}
