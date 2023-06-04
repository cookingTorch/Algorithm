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
		
		int n, m, i, j, v, k, qNum;
		
		n = Integer.parseInt(br.readLine());
		tree = new int[4 * n];
		Arrays.fill(tree, 1000000001);
		st = new StringTokenizer(br.readLine());
		for (k = 1; k <= n; k++) {
			update(1, 1, n, k, Integer.parseInt(st.nextToken()));
		}
		
		m = Integer.parseInt(br.readLine());
		for (k = 0; k < m; k++) {
			st = new StringTokenizer(br.readLine());
			qNum = Integer.parseInt(st.nextToken());
			i = Integer.parseInt(st.nextToken());
			if (qNum == 1) {
				v = Integer.parseInt(st.nextToken());
				update(1, 1, n, i, v);
			}
			else {
				j = Integer.parseInt(st.nextToken());
				sb.append(query(1, 1, n, i, j)).append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}