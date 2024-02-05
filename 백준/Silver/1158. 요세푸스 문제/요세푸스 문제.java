import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, k, curr, i;
		Queue<Integer> q, ans;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		q = new ArrayDeque<>();
		for (i = 1; i <= n; i++) {
			q.add(i);
		}
		ans = new ArrayDeque<>();
		while (!q.isEmpty()) {
			for (i = 0; i < k - 1; i++) {
				curr = q.poll();
				q.add(curr);
			}
			curr = q.poll();
			ans.add(curr);
		}
		sb.append('<').append(ans.poll());
		for (i = 1; i < n; i++) {
			sb.append(", ").append(ans.poll());
		}
		sb.append('>');
		System.out.print(sb);
	}
}
