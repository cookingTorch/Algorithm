import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	
	private static long[] segTree;
	
	private static void update(int node, int start, int end, int idx, int diff) {
		int mid = (start + end) / 2;
		segTree[node] += diff;
		if (start < end) {
			if (idx <= mid) {
				update(node * 2, start, mid, idx, diff);
			}
			else {
				update(node * 2 + 1, mid + 1, end, idx, diff);
			}
		}
	}
	
	private static long query(int node, int start, int end, int left, int right) {
		if (left <= start && end <= right) {
			return segTree[node];
		}
		else if (end < left || right < start) {
			return 0;
		}
		else {
			int mid = (start + end) / 2;
			return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int n, m, ansIdx = 0, num, k, i, j, v, l, q2Idx = 0;
		int[] arr;
		long[] ans;
		ArrayList<int[]> query1 = new ArrayList<>(), query2 = new ArrayList<>();
		
		str = br.readLine();
		n = Integer.parseInt(str);
		arr = new int[n + 1];
		segTree = new long[4 * n + 1];
		str = br.readLine();
		st = new StringTokenizer(str);
		for (l = 1; l <= n; l++) {
			arr[l] = Integer.parseInt(st.nextToken());
			update(1, 1, n, l, arr[l]);
		}
		
		str = br.readLine();
		m = Integer.parseInt(str);
		query1.add(new int[2]);
		for (l = 0; l < m; l++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			num = Integer.parseInt(st.nextToken());
			if (num == 1) {
				i = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				query1.add(new int[] {i, v});
			}
			else {
				k = Integer.parseInt(st.nextToken());
				i = Integer.parseInt(st.nextToken());
				j = Integer.parseInt(st.nextToken());
				query2.add(new int[] {ansIdx++, k, i, j});
			}
		}
		ans = new long[ansIdx];
		
		Collections.sort(query2, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1], o2[1]);
			}
		});
		
		for (l = 0; l < query1.size(); l++) {
			if (l > 0) {
				i = query1.get(l)[0];
				v = query1.get(l)[1];
				update(1, 1, n, i, v - arr[i]);
				arr[i] = v;
			}
			while (q2Idx < query2.size() && (k = query2.get(q2Idx)[1]) == l) {
				i = query2.get(q2Idx)[2];
				j = query2.get(q2Idx)[3];
				ansIdx = query2.get(q2Idx++)[0];
				ans[ansIdx] = query(1, 1, n, i, j);
			}
		}
		
		for (l = 0; l < ans.length; l++) {
			sb.append(ans[l]).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}