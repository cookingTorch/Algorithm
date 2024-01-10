import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static int idx;
	private static int[] tree, lazy, rangeS, rangeE, salary;
	private static ArrayList<ArrayList<Integer>> adj;
	
	private static int assign(int node) {
		rangeS[node] = ++idx;
		rangeE[node] = idx;
		for (int child : adj.get(node)) {
			rangeE[node] = Math.max(rangeE[node], assign(child));
		}
		return rangeE[node];
	}
	
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
	
	private static void update(int node, int start, int end, int left, int right, int diff) {
		int mid;
		
		propagate(node, start, end);
		if (left <= start && end <= right) {
			lazy[node] += diff;
			propagate(node, start, end);
		} else if (!(end < left || right < start)) {
			mid = (start + end) / 2;
			update(node * 2, start, mid, left, right, diff);
			update(node * 2 + 1, mid + 1, end, left, right, diff);
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
		
		char query;
		int n, m, a, x, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		adj = new ArrayList<>();
		for (i = 0; i <= n; i++) {
			adj.add(new ArrayList<>());
		}
		salary = new int[n + 1];
		salary[1] = Integer.parseInt(br.readLine());
		for (i = 2; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			salary[i] = Integer.parseInt(st.nextToken());
			adj.get(Integer.parseInt(st.nextToken())).add(i);
		}
		idx = 0;
		rangeS = new int[n + 1];
		rangeE = new int[n + 1];
		assign(1);
		tree = new int[n * 4];
		lazy = new int[n * 4];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			query = st.nextToken().charAt(0);
			a = Integer.parseInt(st.nextToken());
			if (query == 'p') {
				x = Integer.parseInt(st.nextToken());
				update(1, 1, n, rangeS[a] + 1, rangeE[a], x);
			} else {
				sb.append(salary[a] + sum(1, 1, n, rangeS[a], rangeS[a])).append('\n');
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}