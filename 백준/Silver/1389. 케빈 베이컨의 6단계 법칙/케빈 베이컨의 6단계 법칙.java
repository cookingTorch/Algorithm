import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	
	private static int n;
	private static ArrayList<ArrayList<Integer>> adj;
	
	private static int spfa(int start) {
		int curr, dist, ans;
		int[] distance;
		boolean[] inQueue;
		Queue<Integer> q;
		
		distance = new int[n];
		Arrays.fill(distance, INF);
		distance[start] = 0;
		q = new LinkedList<>();
		inQueue = new boolean[n];
		q.add(start);
		inQueue[start] = true;
		while (!q.isEmpty()) {
			curr = q.poll();
			inQueue[curr] = false;
			dist = distance[curr] + 1;
			for (int next : adj.get(curr)) {
				if (dist < distance[next]) {
					distance[next] = dist;
					if (!inQueue[next]) {
						q.add(next);
					}
				}
			}
		}
		ans = 0;
		for (int kb : distance) {
			ans += kb;
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int m, a, b, min, temp, ans, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		adj = new ArrayList<>();
		for (i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken()) - 1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		ans = 0;
		min = INF;
		for (i = 0; i < n; i++) {
			temp = spfa(i);
			if (temp < min) {
				min = temp;
				ans = i;
			}
		}
		System.out.println(ans + 1);
	}
}