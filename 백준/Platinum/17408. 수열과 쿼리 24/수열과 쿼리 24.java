import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static int[][] tree;
	
	private static int[] merge(int[] arr1, int[] arr2) {
		int[] merged = new int[4];
		merged[0] = arr1[0];
		merged[1] = arr1[1];
		merged[2] = arr2[0];
		merged[3] = arr2[1];
		Arrays.sort(merged);
		return new int[] {merged[3], merged[2]};
	}
	
	private static void update(int node, int start, int end, int idx, int val) {
		if (start == end) {
			tree[node][0] = val;
		}
		else {
			int mid = (start + end) / 2;
			if (idx <= mid) {
				update(node * 2, start, mid, idx, val);
			}
			else {
				update(node * 2 + 1, mid + 1, end, idx, val);
			}
			tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
		}
	}
	
	private static int[] query(int node, int start, int end, int left, int right) {
		if (left <= start && end <= right) {
			return tree[node];
		}
		else if (end < left || right < start) {
			return new int[] {0, 0};
		}
		else {
			int mid = (start + end) / 2;
			return merge(query(node * 2, start, mid, left, right), query(node * 2 + 1, mid + 1, end, left, right));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, i, v, l, r, k, qNum;
		int[] ans;
		
		n = Integer.parseInt(br.readLine());
		tree = new int[4 * n][2];
		st = new StringTokenizer(br.readLine());
		for (k = 1; k <= n; k++) {
			update(1, 1, n, k, Integer.parseInt(st.nextToken()));
		}
		
		m = Integer.parseInt(br.readLine());
		for (k = 0; k < m; k++) {
			st = new StringTokenizer(br.readLine());
			qNum = Integer.parseInt(st.nextToken());
			if (qNum == 1) {
				i = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				update(1, 1, n, i, v);
			}
			else {
				l = Integer.parseInt(st.nextToken());
				r = Integer.parseInt(st.nextToken());
				ans = query(1, 1, n, l, r);
				sb.append(ans[0] + ans[1]).append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}