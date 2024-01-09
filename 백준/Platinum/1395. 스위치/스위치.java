import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static int[] segTree, lazy;
	
	private static void propagate(int node, int start, int end) {
		if (lazy[node] != 0) {
			segTree[node] = (end - start + 1) - segTree[node];
			if (start < end) {
				lazy[node * 2] ^= 1;
				lazy[node * 2 + 1] ^= 1;
			}
			lazy[node] = 0;
		}
	}
	
	private static void update(int node, int start, int end, int left, int right) {
		int mid;
		
		propagate(node, start, end);
		if (left <= start && end <= right) {
			lazy[node] ^= 1;
			propagate(node, start, end);
		} else if (!(end < left || right < start)) {
			mid = (start + end) / 2;
			update(node * 2, start, mid, left, right);
			update(node * 2 + 1, mid + 1, end, left, right);
			segTree[node] = segTree[node * 2] + segTree[node * 2 + 1];
		}
	}
	
	private static int sum(int node, int start, int end, int left, int right) {
		int mid;
		
		propagate(node, start, end);
		if (left <= start && end <= right) {
			return segTree[node];
		} else if (!(end < left || right < start)) {
			mid = (start + end) / 2;
			return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
		} else {
			return 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, o, s, t, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		segTree = new int[4 * n];
		lazy = new int[4 * n];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			o = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			if (o == 0) {
				update(1, 1, n, s, t);
			} else {
				sb.append(sum(1, 1, n, s, t)).append('\n');
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}