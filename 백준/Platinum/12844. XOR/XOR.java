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
			if ((end - start + 1) % 2 != 0) {
				segTree[node] ^= lazy[node];
			}
			if (start < end) {
				lazy[node * 2] ^= lazy[node];
				lazy[node * 2 + 1] ^= lazy[node];
			}
			lazy[node] = 0;
		}
	}
	
	private static void update(int node, int start, int end, int left, int right, int k) {
		int mid;
		
		propagate(node, start, end);
		if (left <= start && end <= right) {
			lazy[node] ^= k;
			propagate(node, start, end);
		} else if (!(end < left || right < start)) {
			mid = (start + end) / 2;
			update(node * 2, start, mid, left, right, k);
			update(node * 2 + 1, mid + 1, end, left, right, k);
			segTree[node] = segTree[node * 2] ^ segTree[node * 2 + 1];
		}
	}
	
	private static int xor(int node, int start, int end, int left, int right) {
		int mid;
		
		propagate(node, start, end);
		if (left <= start && end <= right) {
			return segTree[node];
		} else if (!(end < left || right < start)) {
			mid = (start + end) / 2;
			return xor(node * 2, start, mid, left, right) ^ xor(node * 2 + 1, mid + 1, end, left, right);
		} else {
			return 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, a, query, i, j, k, l;
		
		n = Integer.parseInt(br.readLine());
		segTree = new int[4 * n];
		lazy = new int[4 * n];
		st = new StringTokenizer(br.readLine());
		for (l = 1; l <= n; l++) {
			a = Integer.parseInt(st.nextToken());
			update(1, 1, n, l, l, a);
		}
		m = Integer.parseInt(br.readLine());
		for (l = 0; l < m; l++) {
			st = new StringTokenizer(br.readLine());
			query = Integer.parseInt(st.nextToken());
			i = Integer.parseInt(st.nextToken());
			j = Integer.parseInt(st.nextToken());
			if (query == 1) {
				k = Integer.parseInt(st.nextToken());
				update(1, 1, n, i + 1, j + 1, k);
			} else {
				sb.append(xor(1, 1, n, i + 1, j + 1)).append('\n');
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}