import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	private static int cnt;
	private static int[] prefix, tree, lazy;
	
	private static int init(int node, int start, int end) {
		int mid;
		
		if (start == end) {
			tree[node] = prefix[cnt++];
		} else {
			mid = (start + end) / 2;
			tree[node] = Math.min(init(node * 2, start, mid), init(node * 2 + 1, mid + 1, end));
		}
		return tree[node];
	}
	
	private static void propagate(int node, int start, int end) {
		if (lazy[node] != 0) {
			tree[node] += lazy[node];
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
			tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int m, idx, len, end, ans, i;
		int[] arr;
		String str;
		
		str = br.readLine();
		len = str.length();
		arr = new int[len + 1];
		prefix = new int[len + 1];
		for (i = 1; i <= len; i++) {
			arr[i] = (-2 * (str.charAt(i - 1) - '(')) + 1;
			prefix[i] = prefix[i - 1] + arr[i];
		}
		tree = new int[4 * len];
		cnt = 1;
		init(1, 1, len);
		lazy = new int[4 * len];
		m = Integer.parseInt(br.readLine());
		end = prefix[len];
		ans = 0;
		for (i = 0; i < m; i++) {
			idx = Integer.parseInt(br.readLine());
			arr[idx] *= -1;
			update(1, 1, len, idx, len, arr[idx] * 2);
			end += arr[idx] * 2;
			if (end == 0 && tree[1] >= 0) {
				ans++;
			}
		}
		sb.append(ans);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}