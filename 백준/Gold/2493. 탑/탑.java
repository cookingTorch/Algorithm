import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, curr, i;
		int[] tower;
		Deque<int[]> towers;
		
		n = Integer.parseInt(br.readLine());
		towers = new ArrayDeque<>();
		tower = new int[] {0, INF};
		towers.push(tower);
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			curr = Integer.parseInt(st.nextToken());
			while (tower[1] < curr) {
				towers.pop();
				tower = towers.peek();
			}
			sb.append(tower[0]).append(' ');
			tower = new int[] {i, curr};
			towers.push(tower);
		}
		System.out.print(sb);
	}
}
