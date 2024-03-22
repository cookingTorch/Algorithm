import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static int idx;
	private static int[] lazy, rangeS, rangeE, ans;
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
		if (lazy[node] != 0) {
			if (start < end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}
			lazy[node] = 0;
		}
	}
	
	private static void update(int node, int start, int end, int left, int right, int diff) {
		int mid;
		
		if (left <= start && end <= right) {
			lazy[node] += diff;
		} else if (!(end < left || right < start)) {
            propagate(node, start, end);
			mid = (start + end) / 2;
			update(node * 2, start, mid, left, right, diff);
			update(node * 2 + 1, mid + 1, end, left, right, diff);
		}
	}
	
	private static void makeAns(int node, int start, int end) {
		int mid;
		
		if (start == end) {
			ans[start] = lazy[node];
			return;
		}
		lazy[node * 2] += lazy[node];
		lazy[node * 2 + 1] += lazy[node];
		mid = (start + end) / 2;
        makeAns(node * 2, start, mid);
        makeAns(node * 2 + 1, mid + 1, end);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, idx, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
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
		lazy = new int[4 * n];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			idx = Integer.parseInt(st.nextToken());
			update(1, 1, n, rangeS[idx], rangeE[idx], Integer.parseInt(st.nextToken()));
		}
		ans = new int[n + 1];
		makeAns(1, 1, n);
		for (i = 1; i <= n; i++) {
			sb.append(ans[rangeS[i]]).append(' ');
		}
		System.out.print(sb);
	}
}
