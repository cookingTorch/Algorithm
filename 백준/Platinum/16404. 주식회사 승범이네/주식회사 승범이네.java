import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	private static int rangeS = 0;
	private static int[] rangeE, newIdx;
	private static long[] tree, lazy;
	private static ArrayList<ArrayList<Integer>> child = new ArrayList<>();
	
	// DFS
	private static int assignRange(int idx) {
		int i;
		newIdx[idx] = ++rangeS;
		rangeE[idx] = rangeS;
		for (i = 0; i < child.get(idx).size(); i++) {
			rangeE[idx] = Math.max(rangeE[idx], assignRange(child.get(idx).get(i)));
		}
		return rangeE[idx];
	}
	
	// Lazy 적용
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
	
	// 구간 변경
	private static void update(int node, int start, int end, int left, int right, int diff) {
		propagate(node, start, end);
		if (left <= start && end <= right) {
			lazy[node] += diff;
			propagate(node, start, end);
		}
		else if (!(end < left || right < start)) {
			int mid = (start + end) / 2;
			update(node * 2, start, mid, left, right, diff);
			update(node * 2 + 1, mid + 1, end, left, right, diff);
		}
	}
	
	// 노드 탐색
	private static long query(int node, int start, int end, int idx) {
		propagate(node, start, end);
		if (start == end) {
			return tree[node];
		}
		else {
			int mid = (start + end) / 2;
			if (idx <= mid) {
				return query(node * 2, start, mid, idx);
			}
			else {
				return query(node * 2 + 1, mid + 1, end, idx);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int n, m, k, i, w;
		
		// 간선 입력
		str = br.readLine();
		st = new StringTokenizer(str);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		for (k = 0; k <= n; k++) {
			child.add(new ArrayList<>());
		}
		str = br.readLine();
		st = new StringTokenizer(str);
		st.nextToken();
		for (k = 2; k <= n; k++) {
			child.get(Integer.parseInt(st.nextToken())).add(k);
		}
		
		// 새 Index, 범위 부여
		newIdx = new int[n + 1];
		rangeE = new int[n + 1];
		assignRange(1);
		
		// 쿼리 수행
		tree = new long[4 * n + 1];
		lazy = new long[4 * n + 1];
		for (k = 0; k < m; k++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			
			// 1 번 쿼리
			if (Integer.parseInt(st.nextToken()) == 1) {
				i = Integer.parseInt(st.nextToken());
				w = Integer.parseInt(st.nextToken());
				update(1, 1, n, newIdx[i], rangeE[i], w);
			}
			
			// 2 번 쿼리
			else {
				i = Integer.parseInt(st.nextToken());
				sb.append(query(1, 1, n, newIdx[i])).append("\n");
			}
		}

		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

}