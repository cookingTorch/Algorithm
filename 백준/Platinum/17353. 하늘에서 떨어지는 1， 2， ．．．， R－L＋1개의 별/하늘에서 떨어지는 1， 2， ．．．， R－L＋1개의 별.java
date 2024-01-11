import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static long[] star, lazy, cnt;
	
	private static void propagate(int node, int start, int end) {
		if (lazy[node] != 0) {
			if (start < end) {
				lazy[node * 2] += lazy[node];
				cnt[node * 2] += cnt[node];
				lazy[node * 2 + 1] += lazy[node] + (cnt[node] * (((end - start) / 2) + 1));
				cnt[node * 2 + 1] += cnt[node];
			} else {
				star[node] += lazy[node];
			}
			lazy[node] = 0;
			cnt[node] = 0;
		}
	}
	
	private static void update(int node, int start, int end, int left, int right) {
		int mid;
		
		propagate(node, start, end);
		if (left <= start && end <= right) {
			lazy[node] += 1 + (start - left);
			cnt[node] += 1;
			propagate(node, start, end);
		} else if (!(end < left || right < start)) {
			mid = (start + end) / 2;
			update(node * 2, start, mid, left, right);
			update(node * 2 + 1, mid + 1, end, left, right);
		}
	}
	
	private static long getStar(int node, int start, int end, int idx) {
		int mid;
		
		propagate(node, start, end);
		if (start == idx && idx == end) {
			return star[node];
		} else if (!(end < idx || idx < start)) {
			mid = (start + end) / 2;
			return getStar(node * 2, start, mid, idx) + getStar(node * 2 + 1, mid + 1, end, idx);
		} else {
			return 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, q, l, r, x, query, i;
		int[] a;
		
		n = Integer.parseInt(br.readLine());
		a = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		star = new long[4 * n];
		lazy = new long[4 * n];
		cnt = new long[4 * n];
		q = Integer.parseInt(br.readLine());
		for (i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			query = Integer.parseInt(st.nextToken());
			if (query == 1) {
				l = Integer.parseInt(st.nextToken());
				r = Integer.parseInt(st.nextToken());
				update(1, 1, n, l, r);
			} else {
				x = Integer.parseInt(st.nextToken());
				sb.append(a[x] + getStar(1, 1, n, x)).append('\n');
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}