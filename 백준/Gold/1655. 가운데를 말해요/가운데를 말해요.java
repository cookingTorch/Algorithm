import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int DIFF = 10000;
	private static final int SIZE = 20000;
	
	private static int[] tree;
	
	private static void add(int node, int start, int end, int num) {
		int mid;
		
		tree[node]++;
		if (start == end) {
			return;
		}
		mid = (start + end) / 2;
		if (num > mid) {
			add(node * 2 + 1, mid + 1, end, num);
		} else {
			add(node * 2, start, mid, num);
		}
	}
	
	private static int getNum(int node, int start, int end, int idx) {
		int mid;
		
		if (start == end) {
			return start - DIFF;
		}
		mid = (start + end) / 2;
		if (tree[node * 2] < idx) {
			return getNum(node * 2 + 1, mid + 1, end, idx - tree[node * 2]);
		}
		return getNum(node * 2, start, mid, idx);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n, i;
		
		tree = new int[4 * SIZE];
		n = Integer.parseInt(br.readLine());
		for (i = 0; i < n; i++) {
			add(1, 0, SIZE, Integer.parseInt(br.readLine()) + DIFF);
			sb.append(getNum(1, 0, SIZE, (i >> 1) + 1)).append('\n');
		}
		System.out.print(sb);
	}
}
