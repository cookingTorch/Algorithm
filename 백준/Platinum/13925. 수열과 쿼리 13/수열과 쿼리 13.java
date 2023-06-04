import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	private static long mod = 1000000007;
	private static long[] tree;
	private static long[][] lazy;
	
	private static void propagate(int node, int start, int end) {
		if (lazy[node][0] != 1 || lazy[node][1] != 0) {
			tree[node] = (tree[node] * lazy[node][0]) % mod;
			tree[node] = (tree[node] + ((end - start + 1) * lazy[node][1])) % mod;
			if (start < end) {
				lazy[node * 2][0] = (lazy[node][0] * lazy[node * 2][0]) % mod;
				lazy[node * 2][1] = (lazy[node][0] * lazy[node * 2][1] + lazy[node][1]) % mod;
				lazy[node * 2 + 1][0] = (lazy[node][0] * lazy[node * 2 + 1][0]) % mod;
				lazy[node * 2 + 1][1] = (lazy[node][0] * lazy[node * 2 + 1][1] + lazy[node][1]) % mod;
			}
			lazy[node][0] = 1;
			lazy[node][1] = 0;
		}
	}
	
	private static void update(int node, int start, int end, int left, int right, long diff1, long diff2) {
		propagate(node, start, end);
		if (left <= start && end <= right) {
			lazy[node][0] = (diff1 * lazy[node][0]) % mod;
			lazy[node][1] = (diff1 * lazy[node][1] + diff2) % mod;
			propagate(node, start, end);
		}
		else if (!(start > right || end < left)) {
			int mid = (start + end) / 2;
			update(node * 2, start, mid, left, right, diff1, diff2);
			update(node * 2 + 1, mid + 1, end, left, right, diff1, diff2);
			tree[node] = (tree[node * 2] + tree[node * 2 + 1]) % mod;
		}
	}
	
	private static long sum(int node, int start, int end, int left, int right) {
		propagate(node, start, end);
		if (left <= start && end <= right) {
			return tree[node] % mod;
		}
		else if (!(start > right || end < left)) {
			int mid = (start + end) / 2;
			return (sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right)) % mod;
		}
		else {
			return 0;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, x, y, i, qNum;
		long num, v;
		
		n = Integer.parseInt(br.readLine());
		tree = new long[4 * n];
		lazy = new long[4 * n][2];
		for (i = 0; i < 4 * n; i++) {
			lazy[i][0] = 1;
		}
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			num = Long.parseLong(st.nextToken());
			update(1, 1, n, i, i, 1, num);
		}
		
		m = Integer.parseInt(br.readLine());
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			qNum = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			if (qNum < 4) {
				v = Long.parseLong(st.nextToken());
				if (qNum == 1) {
					update(1, 1, n, x, y, 1, v);
				}
				else if (qNum == 2) {
					update(1, 1, n, x, y, v, 0);
				}
				else {
					update(1, 1, n, x, y, 0, v);
				}
			}
			else {
				sb.append(sum(1, 1, n, x, y) % mod).append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}