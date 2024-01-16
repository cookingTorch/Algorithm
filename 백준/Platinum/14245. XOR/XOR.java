import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static int cnt;
	private static int[] arr, tree, lazy;
	
	private static void init(int node, int start, int end) {
		int mid;
		
		if (start == end) {
			tree[node] = arr[cnt++];
		} else {
			mid = (start + end) / 2;
			init(node * 2, start, mid);
			init(node * 2 + 1, mid + 1, end);
		}
	}
	
	private static void propagate(int node, int start, int end) {
		if (lazy[node] != 0) {
			if (start == end) {
				tree[node] ^= lazy[node];
			} else {
				lazy[node * 2] ^= lazy[node];
				lazy[node * 2 + 1] ^= lazy[node];
			}
			lazy[node] = 0;
		}
	}
	
	private static void update(int node, int start, int end, int left, int right, int diff) {
		int mid;
		
		propagate(node, start, end);
		if (left <= start && end <= right) {
			lazy[node] ^= diff;
			propagate(node, start, end);
		} else if (!(end < left || right < start)) {
			mid = (start + end) / 2;
			update(node * 2, start, mid, left, right, diff);
			update(node * 2 + 1, mid + 1, end, left, right, diff);
		}
	}
	
	private static int find(int node, int start, int end, int idx) {
		int mid;
		
		propagate(node, start, end);
		if (start == idx && idx == end) {
			return tree[node];
		} else if (!(end < idx || idx < start)) {
			mid = (start + end) / 2;
			return find(node * 2, start, mid, idx) + find(node * 2 + 1, mid + 1, end, idx);
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int n, m, t, a, b, c, i;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		tree = new int[4 * n];
		cnt = 0;
		init(1, 1, n);
		m = Integer.parseInt(br.readLine());
		lazy = new int[4 * n];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			t = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken()) + 1;
			if (t == 1) {
				b = Integer.parseInt(st.nextToken()) + 1;
				c = Integer.parseInt(st.nextToken());
				update(1, 1, n, a, b, c);
			} else {
				sb.append(find(1, 1, n, a)).append('\n');
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}