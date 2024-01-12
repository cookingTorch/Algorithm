import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static int idx;
	private static int[] rangeS, rangeE, forward, backward, lazy;
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
			forward[node] += (end - start + 1) * lazy[node];
			if (start < end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}
			lazy[node] = 0;
		}
	}
	
	private static void updateF(int node, int start, int end, int left, int right, int diff) {
		int mid;
		
		propagate(node, start, end);
		if (left <= start && end <= right) {
			lazy[node] += diff;
			propagate(node, start, end);
		} else if (!(end < left || right < start)) {
			mid = (start + end) / 2;
			updateF(node * 2, start, mid, left, right, diff);
			updateF(node * 2 + 1, mid + 1, end, left, right, diff);
			forward[node] = forward[node * 2] + forward[node * 2 + 1];
		}
	}
	
	private static void updateB(int node, int start, int end, int idx, int diff) {
		int mid;
		
		if (start <= idx && idx <= end) {
			backward[node] += diff;
			if (start < end) {
				mid = (start + end) / 2;
				updateB(node * 2, start, mid, idx, diff);
				updateB(node * 2 + 1, mid + 1, end, idx, diff);
			}
		}
	}
	
	private static int getValue(int node, int start, int end, int idx) {
		int mid;
		
		propagate(node, start, end);
		if (start == idx && idx == end) {
			return forward[node];
		} else if (!(end < idx || idx < start)) {
			mid = (start + end) / 2;
			return getValue(node * 2, start, mid, idx) + getValue(node * 2 + 1, mid + 1, end, idx);
		} else {
			return 0;
		}
	}
	
	private static int sum(int node, int start, int end, int left, int right) {
		int mid;
		
		if (left <= start && end <= right) {
			return backward[node];
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
		
		boolean down;
		int n, m, q, i, w, j;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		adj = new ArrayList<>();
		for (i = 0; i <= n; i++) {
			adj.add(new ArrayList<>());
		}
		st.nextToken();
		for (i = 2; i <= n; i++) {
			adj.get(Integer.parseInt(st.nextToken())).add(i);
		}
		
		idx = 0;
		rangeS = new int[n + 1];
		rangeE = new int[n + 1];
		euler(1);
		
		forward = new int[4 * n];
		backward = new int[4 * n];
		lazy = new int[4 * n];
		down = true;
		for (j = 0; j < m; j++) {
			st = new StringTokenizer(br.readLine());
			q = Integer.parseInt(st.nextToken());
			if (q == 1) {
				i = Integer.parseInt(st.nextToken());
				w = Integer.parseInt(st.nextToken());
				if (down) {
					updateF(1, 1, n, rangeS[i], rangeE[i], w);
				} else {
					updateB(1, 1, n, rangeS[i], w);
				}
			} else if (q == 2) {
				i = Integer.parseInt(st.nextToken());
				sb.append(getValue(1, 1, n, rangeS[i]) + sum(1, 1, n, rangeS[i], rangeE[i])).append('\n');
			} else {
				down ^= true;
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}