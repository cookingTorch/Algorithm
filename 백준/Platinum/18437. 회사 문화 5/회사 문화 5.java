import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int idx;
	private static int[] rangeS, rangeE, tree, lazy;
	private static ArrayList<ArrayList<Integer>> adj;
	
	private static int euler(int node) {
		rangeS[node] = ++idx;
		rangeE[node] = idx;
		for (int child : adj.get(node)) {
			rangeE[node] = Math.max(rangeE[node], euler(child));
		}
		return rangeE[node];
	}
	
	private static void propagate(int node, int start, int end) {
		if (lazy[node] != -1) {
			tree[node] = (end - start + 1) * lazy[node];
			if (start < end) {
				lazy[node * 2] = lazy[node];
				lazy[node * 2 + 1] = lazy[node];
			}
			lazy[node] = -1;
		}
	}
	
	private static void update(int node, int start, int end, int left, int right, int diff) {
		int mid;
		
		propagate(node, start, end);
		if (left <= start && end <= right) {
			lazy[node] = diff;
			propagate(node, start, end);
		} else if (!(end < left || right < start)) {
			mid = (start + end) / 2;
			update(node * 2, start, mid, left, right, diff);
			update(node * 2 + 1, mid + 1, end, left, right, diff);
			tree[node] = tree[node * 2] + tree[node * 2 + 1];
		}
	}
	
	private static int sum(int node, int start, int end, int left, int right) {
		int mid;
		
		propagate(node, start, end);
		if (left <= start && end <= right) {
			return tree[node];
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
		
		int n, m, i, q, j;
		
		n = Integer.parseInt(br.readLine());
		adj = new ArrayList<>();
		for (i = 0; i <= n; i++) {
			adj.add(new ArrayList<>());
		}
		st = new StringTokenizer(br.readLine());
		st.nextToken();
		for (i = 2; i <= n; i++) {
			adj.get(Integer.parseInt(st.nextToken())).add(i);
		}
		idx = 0;
		rangeS = new int[n + 1];
		rangeE = new int[n + 1];
		euler(1);
		tree = new int[4 * n];
		lazy = new int[4 * n];
		Arrays.fill(lazy, -1);
		m = Integer.parseInt(br.readLine());
		update(1, 1, n, 1, n, 1);
		for (j = 0; j < m; j++) {
			st = new StringTokenizer(br.readLine());
			q = Integer.parseInt(st.nextToken());
			i = Integer.parseInt(st.nextToken());
			if (q == 1) {
				update(1, 1, n, rangeS[i] + 1, rangeE[i], 1);
			} else if (q == 2) {
				update(1, 1, n, rangeS[i] + 1, rangeE[i], 0);
			} else {
				sb.append(sum(1, 1, n, rangeS[i] + 1, rangeE[i])).append('\n');
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}