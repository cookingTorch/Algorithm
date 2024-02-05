import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static int[] tree;
	
	private static void init(int node, int start, int end) {
		int mid;
		
		tree[node] = end - start + 1;
		if (start < end) {
			mid = (start + end) / 2;
			init(node * 2, start, mid);
			init(node * 2 + 1, mid + 1, end);
		}
	}
	
	private static int delete(int node, int start, int end, int idx) {
		int mid;
		
		tree[node]--;
		if (start < end) {
			mid = (start + end) / 2;
			if (idx <= tree[node * 2]) {
				return delete(node * 2, start, mid, idx);
			} else {
				return delete(node * 2 + 1, mid + 1, end, idx - tree[node * 2]);
			}
		}
		return start;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, k, idx, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		tree = new int[4 * n];
		init(1, 1, n);
		
		idx = k;
		sb.append('<');
		for (i = 1; i <= n; i++) {
			sb.append(delete(1, 1, n, idx));
			if (i < n) {
				idx = ((idx + k - 2) % (n - i)) + 1;
				sb.append(", ");
			}
		}
		sb.append('>');
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
