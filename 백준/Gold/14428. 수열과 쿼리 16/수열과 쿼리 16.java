import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	private static int[][] tree;
	
	private static void update(int node, int start, int end, int idx, int val) {
		if (start == end) {
			tree[node][0] = idx;
			tree[node][1] = val;
		}
		else {
			int mid = (start + end) / 2;
			if (idx <= mid) {
				update(node * 2, start, mid, idx, val);
			}
			else {
				update(node * 2 + 1, mid + 1, end, idx, val);
			}
			if (tree[node * 2][1] > tree[node * 2 + 1][1]) {
				tree[node][0] = tree[node * 2 + 1][0];
				tree[node][1] = tree[node * 2 + 1][1];
			}
			else {
				tree[node][0] = tree[node * 2][0];
				tree[node][1] = tree[node * 2][1];
			}
		}
	}
	
	private static int[] query(int node, int start, int end, int left, int right) {
		if (left <= start && end <= right) {
			return tree[node];
		}
		else if (end < left || right < start) {
			return new int[] {100001, 1000000001};
		}
		else {
			int mid = (start + end) / 2;
			int[] arr1, arr2;
			arr1 = query(node * 2, start, mid, left, right);
			arr2 = query(node * 2 + 1, mid + 1, end, left, right);
			if (arr2[1] < arr1[1]) {
				return arr2;
			}
			else {
				return arr1;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, i, j, v, k, qNum;
		
		n = Integer.parseInt(br.readLine());
		tree = new int[4 * n][2];
		for (i = 0; i < 4 * n; i++) {
			tree[i][0] = 100001;
			tree[i][1] = 1000000001;
		}
		st = new StringTokenizer(br.readLine());
		for (k = 1; k <= n; k++) {
			update(1, 1, n, k, Integer.parseInt(st.nextToken()));
		}
		
		m = Integer.parseInt(br.readLine());
		for (k = 0; k < m; k++) {
			st = new StringTokenizer(br.readLine());
			qNum = Integer.parseInt(st.nextToken());
			i = Integer.parseInt(st.nextToken());
			if (qNum == 1) {
				v = Integer.parseInt(st.nextToken());
				update(1, 1, n, i, v);
			}
			else {
				j = Integer.parseInt(st.nextToken());
				sb.append(query(1, 1, n, i, j)[0]).append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}