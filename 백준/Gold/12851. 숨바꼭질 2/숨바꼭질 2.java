import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	
	private static StringBuilder sb;
	
	private static final void bfs(int start, int end) {
		int max;
		int curr;
		int next;
		int size;
		int[] cnt;
		int[] distance;
		ArrayDeque<Integer> q;
		
		size = end << 1;
		distance = new int[size + 1];
		Arrays.fill(distance, INF);
		distance[start] = 0;
		cnt = new int[size + 1];
		cnt[start] = 1;
		max = INF;
		q = new ArrayDeque<>();
		q.addLast(start);
		while (!q.isEmpty()) {
			curr = q.pollFirst();
			next = curr << 1;
			if (curr > 0 && next <= size) {
				if (distance[curr] + 1 < distance[next]) {
					distance[next] = distance[curr] + 1;
					cnt[next] = cnt[curr];
					q.addLast(next);
					if (next == end) {
						max = distance[end];
					}
				} else if (distance[curr] + 1 == distance[next]) {
					cnt[next] += cnt[curr];
				}
			}
			if (distance[curr] >= max) {
				continue;
			}
			next = curr + 1;
			if (next <= size) {
				if (distance[curr] + 1 < distance[next]) {
					distance[next] = distance[curr] + 1;
					cnt[next] = cnt[curr];
					q.addLast(next);
					if (next == end) {
						max = distance[end];
					}
				} else if (distance[curr] + 1 == distance[next]) {
					cnt[next] += cnt[curr];
				}
			}
			next = curr - 1;
			if (next >= 0) {
				if (distance[curr] + 1 < distance[next]) {
					distance[next] = distance[curr] + 1;
					cnt[next] = cnt[curr];
					q.addLast(next);
					if (next == end) {
						max = distance[end];
					}
				} else if (distance[curr] + 1 == distance[next]) {
					cnt[next] += cnt[curr];
				}
			}
		}
		sb.append(distance[end]).append('\n').append(cnt[end]);
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int k;
		StringTokenizer st;
		
		st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		sb = new StringBuilder();
		if (n < k) {
			bfs(n, k);
		} else {
			sb.append(n - k).append("\n1");
		}
		System.out.print(sb);
	}
}
