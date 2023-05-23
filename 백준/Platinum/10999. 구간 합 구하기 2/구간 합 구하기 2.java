import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	private static long[] segmentTree, lazy;
	
	private static void propagate(int node, int start, int end) {
		if (lazy[node] != 0) {
			segmentTree[node] += (end - start + 1) * lazy[node];
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
			segmentTree[node] = segmentTree[node * 2] + segmentTree[node * 2 + 1];
		}
	}
	
	private static long sum(int node, int start, int end, int left, int right) {
		propagate(node, start, end);
		if (left <= start && end <= right) {
			return segmentTree[node];
		}
		else if (!(start > right || end < left)) {
			int mid = (start + end) / 2;
			return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
		}
		else {
			return 0;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int n, m, k, a, b, c, i;
		long num, d;
		
		str = br.readLine();
		st = new StringTokenizer(str);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		segmentTree = new long[4 * n];
		lazy = new long[4 * n];
		for (i = 1; i <= n; i++) {
			str = br.readLine();
			num = Long.parseLong(str);
			update(1, 1, n, i, i, num);
		}
		
		for (i = 0; i < m + k; i++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			a = Integer.parseInt(st.nextToken());
			if (a == 1) {
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				d = Long.parseLong(st.nextToken());
				update(1, 1, n, b, c, d);
			}
			else {
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				sb.append(sum(1, 1, n, b, c)).append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}