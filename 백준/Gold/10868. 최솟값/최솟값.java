import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] tree;
	
	private static void update(int node, int start, int end, int idx, int val) {
		if (start == end) {
			tree[node] = val;
		}
		else {
			int mid = (start + end) / 2;
			if (idx <= mid) {
				update(node * 2, start, mid, idx, val);
			}
			else {
				update(node * 2 + 1, mid + 1, end, idx, val);
			}
			tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
		}
	}
	
	private static int query(int node, int start, int end, int left, int right) {
		if (left <= start && end <= right) {
			return tree[node];
		}
		else if (end < left || right < start) {
			return 1000000001;
		}
		else {
			int mid = (start + end) / 2;
			return Math.min(query(node * 2, start, mid, left, right), query(node * 2 + 1, mid + 1, end, left, right));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, a, b, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		tree = new int[4 * n];
		Arrays.fill(tree, 1000000001);
		for (i = 1; i <= n; i++) {
			update(1, 1, n, i, Integer.parseInt(br.readLine()));
		}
		
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			sb.append(query(1, 1, n, a, b)).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}