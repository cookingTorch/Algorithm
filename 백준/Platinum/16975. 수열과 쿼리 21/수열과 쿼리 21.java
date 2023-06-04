import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	private static long[] tree, lazy;
	
	private static void propagate(int node, int start, int end) {
		if (lazy[node] != 0) {
			tree[node] += (end - start + 1) * lazy[node];
			if (start < end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}
			lazy[node] = 0;
		}
	}
	
	private static void update(int node, int start, int end, int left, int right, long diff) {
		propagate(node, start, end);
		if (left <= start && end <= right) {
			lazy[node] += diff;
			propagate(node, start, end);
		}
		else if (!(start > right || end < left)) {
			int mid = (start + end) / 2;
			update(node * 2, start, mid, left, right, diff);
			update(node * 2 + 1, mid + 1, end, left, right, diff);
			tree[node] = tree[node * 2] + tree[node * 2 + 1];
		}
	}
	
	private static long query(int node, int start, int end, int idx) {
		propagate(node, start, end);
		if (start == end) {
			return tree[node];
		}
		else {
			int mid = (start + end) / 2;
			if (idx <= mid) {
				return query(node * 2, start, mid, idx);
			}
			else {
				return query(node * 2 + 1, mid + 1, end, idx);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, i, j, x, l;
		long k;
		
		n = Integer.parseInt(br.readLine());
		tree = new long[4 * n];
		lazy = new long[4 * n];
		st = new StringTokenizer(br.readLine());
		for (l = 1; l <= n; l++) {
			update(1, 1, n, l, l, Long.parseLong(st.nextToken()));
		}
		
		m = Integer.parseInt(br.readLine());
		for (l = 0; l < m; l++) {
			st = new StringTokenizer(br.readLine());
			if (Integer.parseInt(st.nextToken()) == 1) {
				i = Integer.parseInt(st.nextToken());
				j = Integer.parseInt(st.nextToken());
				k = Long.parseLong(st.nextToken());
				update(1, 1, n, i, j, k);
			}
			else {
				x = Integer.parseInt(st.nextToken());
				sb.append(query(1, 1, n, x)).append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}