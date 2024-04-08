import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	
	private static int[] lazy, arr;
	
	private static void prop(int node) {
		if (lazy[node] != 0) {
			lazy[node << 1] += lazy[node];
			lazy[node << 1 | 1] += lazy[node];
			lazy[node] = 0;
		}
	}
	
	private static void update(int node, int start, int end, int left, int right) {
		int mid;
		
		if (end < left || right < start) {
			return;
		}
		if (left <= start && end <= right) {
			lazy[node]++;
			return;
		}
		mid = (start + end) >> 1;
		prop(node);
		update(node << 1, start, mid, left, right);
		update(node << 1 | 1, mid + 1, end, left, right);
	}
	
	private static void propAll(int node, int start, int end) {
		int mid;
		
		if (start == end) {
			arr[start] = lazy[node];
			return;
		}
		prop(node);
		mid = (start + end) >> 1;
		propAll(node << 1, start, mid);
		propAll(node << 1 | 1, mid + 1, end);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, h, min, cnt, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		lazy = new int[4 * h];
		for (i = 0; i < n; i += 2) {
			update(1, 0, h - 1, h - Integer.parseInt(br.readLine()), h - 1);
			update(1, 0, h - 1, 0, Integer.parseInt(br.readLine()) - 1);
		}
		arr = new int[h];
		propAll(1, 0, h - 1);
		cnt = 0;
		min = INF;
		for (i = 0; i < h; i++) {
			if (arr[i] < min) {
				cnt = 1;
				min = arr[i];
			} else if (arr[i] == min) {
				cnt++;
			}
		}
		sb.append(min).append(' ').append(cnt);
		System.out.print(sb);
	}
}
