import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	private static final int FAIL = -1;

	private static int bfs(int start, int end, int n, int[] arr) {
		int cnt;
		int size;
		int curr;
		int dist;
		int next;
		boolean[] visited;
		ArrayDeque<Integer> q;

		if (start == end) {
			return 0;
		}
		visited = new boolean[n];
		q = new ArrayDeque<>();
		visited[start] = true;
		q.addLast(start);
		for (cnt = 1; !q.isEmpty(); cnt++) {
			size = q.size();
			while (size-- > 0) {
				curr = q.pollFirst();
				dist = arr[curr];
				for (next = curr - dist; next >= 0; next -= dist) {
					if (visited[next]) {
						continue;
					}
					if (next == end) {
						return cnt;
					}
					visited[next] = true;
					q.addLast(next);
				}
				for (next = curr + dist; next < n; next += dist) {
					if (visited[next]) {
						continue;
					}
					if (next == end) {
						return cnt;
					}
					visited[next] = true;
					q.addLast(next);
				}
			}
		}
		return FAIL;
	}

	public static void main(String[] args) throws IOException {
		int n;
		int a;
		int b;
		int i;
		int[] arr;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine(), " ", false);
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ", false);
		System.out.print(bfs(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, n, arr));
	}
}
