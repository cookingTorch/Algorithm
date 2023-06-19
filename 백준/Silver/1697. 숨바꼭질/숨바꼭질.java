import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, k, ans = 0;
		int[] curr;
		Queue<int[]> q = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		if (k <= n) {
			ans = n - k;
		}
		else {
			q.add(new int[] {k, 0});
			while (!q.isEmpty()) {
				curr = q.poll();
				if (curr[0] == n) {
					ans = curr[1];
					break;
				}
				if (curr[0] % 2 == 0 && !visited.contains(curr[0] / 2)) {
					q.add(new int[] {curr[0] / 2, curr[1] + 1});
					visited.add(curr[0] / 2);
				}
				if (curr[0] > 0 && !visited.contains(curr[0] - 1)) {
					q.add(new int[] {curr[0] - 1, curr[1] + 1});
					visited.add(curr[0] - 1);
				}
				if (!visited.contains(curr[0] + 1)) {
					q.add(new int[] {curr[0] + 1, curr[1] + 1});
					visited.add(curr[0] + 1);
				}
			}
		}
		
		sb.append(ans);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}