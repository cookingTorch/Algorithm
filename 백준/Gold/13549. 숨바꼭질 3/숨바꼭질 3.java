import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	
	private static int[] distance;
	private static Deque<Integer> dq;
	
	private static void update(int curr, int next, int time) {
		if (distance[curr] + time < distance[next]) {
			distance[next] = distance[curr] + time;
			if (time == 0) {
				dq.addFirst(next);
			} else {
				dq.add(next);
			}
		}
	}
	
	private static int dist(int n, int k) {
		int curr;
		
		distance = new int[2 * k];
		Arrays.fill(distance, INF);
		distance[n] = 0;
		dq = new ArrayDeque<>();
		dq.add(n);
		while (!dq.isEmpty()) {
			curr = dq.poll();
			if (curr == k) {
				break;
			}
			if (curr * 2 < 2 * k) {
				update(curr, curr * 2, 0);
			}
			if (curr - 1 >= 0) {
				update(curr, curr - 1, 1);
			}
			if (curr + 1 < 2 * k) {
				update(curr, curr + 1, 1);
			}
		}
		return distance[k];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, k;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		if (k <= n) {
			System.out.println(n - k);
			return;
		}
		System.out.println(dist(n, k));
	}
}