import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static int idx;
	private static int[] tree, rangeS, rangeE;
	private static ArrayList<ArrayList<Integer>> adj;
	
	private static int euler(int node) {
		rangeS[node] = ++idx;
		rangeE[node] = idx;
		for (int child : adj.get(node)) {
			rangeE[node] = Math.max(rangeE[node], euler(child));
		}
		return rangeE[node];
	}
	
	private static void update(int node, int start, int end, int num, int diff) {
		int mid;
		
		if (start <= num && num <= end) {
			tree[node] += diff;
			if (start < end) {
				mid = (start + end) / 2;
				update(node * 2, start, mid, num, diff);
				update(node * 2 + 1, mid + 1, end, num, diff);
			}
		}
	}
	
	private static int sum(int node, int start, int end, int left, int right) {
		int mid;
		
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
		
		int n, m, i, w, query, j;
		
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
		tree = new int[4 * n];
		for (j = 0; j < m; j++) {
			st = new StringTokenizer(br.readLine());
			query = Integer.parseInt(st.nextToken());
			i = Integer.parseInt(st.nextToken());
			if (query == 1) {
				w = Integer.parseInt(st.nextToken());
				update(1, 1, n, rangeS[i], w);
			} else {
				sb.append(sum(1, 1, n, rangeS[i], rangeE[i])).append('\n');
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}