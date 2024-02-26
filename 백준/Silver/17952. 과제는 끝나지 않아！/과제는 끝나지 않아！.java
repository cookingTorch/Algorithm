import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, score, time;
		int[] curr;
		Deque<int[]> stack;
		
		n = Integer.parseInt(br.readLine());
		score = 0;
		curr = null;
		stack = new ArrayDeque<>();
		for (time = 1; time <= n; time++) {
			st = new StringTokenizer(br.readLine());
			if (Integer.parseInt(st.nextToken()) == 1) {
				if (curr != null) {
					stack.push(curr);
				}
				curr = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			}
			if (curr == null && !stack.isEmpty()) {
				curr = stack.pop();
			}
			if (curr != null && --curr[1] == 0) {
				score += curr[0];
				if (stack.isEmpty()) {
					curr = null;
				} else {
					curr = stack.pop();
				}
			}
		}
		System.out.print(score);
	}
}
