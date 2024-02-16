import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	
	private static int m, curr, end;
	private static int[] distance;
	private static char[] map;
	private static Queue<Integer> q;
	
	private static boolean add(int num) {
		if (map[num] == '1' && distance[curr] + 1 < distance[num]) {
			distance[num] = distance[curr] + 1;
			if (num == end) {
				return true;
			}
			q.add(num);
		}
		return false;
	}
	
	private static int bfs(int start) {
		Arrays.fill(distance, INF);
		distance[start] = 1;
		q = new ArrayDeque<>();
		q.add(start);
		while (!q.isEmpty()) {
			curr = q.poll();
			if (add(curr - 1) || add(curr + 1) || add(curr - m) || add(curr + m)) {
				return distance[end];
			}
		}
		return distance[end];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, i, j;
		char[] str;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()) + 2;
		m = Integer.parseInt(st.nextToken()) + 2;
		map = new char[n * m];
		for (i = 1; i < n - 1; i++) {
			str = br.readLine().toCharArray();
			for (j = 1; j < m - 1; j++) {
				map[i * m + j] = str[j - 1];
			}
		}
		distance = new int[n * m];
		end = (n - 2) * m + m - 2;
		System.out.print(bfs(m + 1));
	}
}
