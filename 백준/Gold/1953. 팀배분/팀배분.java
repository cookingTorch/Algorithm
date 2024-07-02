import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final char SPACE = ' ';
	private static final char LINE_BREAK = '\n';
	
	private static boolean[] visited;
	private static String[] input;
	private static PriorityQueue<Integer> blue;
	private static PriorityQueue<Integer> white;
	
	private static final void dfsBlue(StringTokenizer st) {
		int num;
		int next;
		
		num = Integer.parseInt(st.nextToken());
		while (num-- > 0) {
			next = Integer.parseInt(st.nextToken());
			if (visited[next]) {
				continue;
			}
			visited[next] = true;
			white.offer(next);
			dfsWhite(new StringTokenizer(input[next]));
		}
	}
	
	private static final void dfsWhite(StringTokenizer st) {
		int num;
		int next;
		
		num = Integer.parseInt(st.nextToken());
		while (num-- > 0) {
			next = Integer.parseInt(st.nextToken());
			if (visited[next]) {
				continue;
			}
			visited[next] = true;
			blue.offer(next);
			dfsBlue(new StringTokenizer(input[next]));
		}
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int i;
		StringBuilder sb;
		BufferedReader br;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		input = new String[n + 1];
		for (i = 1; i <= n; i++) {
			input[i] = br.readLine();
		}
		blue = new PriorityQueue<>(n);
		white = new PriorityQueue<>(n);
		visited = new boolean[n + 1];
		for (i = 1; i <= n; i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			blue.offer(i);
			dfsBlue(new StringTokenizer(input[i]));
		}
		sb = new StringBuilder();
		sb.append(blue.size()).append(LINE_BREAK);
		while (!blue.isEmpty()) {
			sb.append(blue.poll()).append(SPACE);
		}
		sb.append(LINE_BREAK).append(white.size()).append(LINE_BREAK);
		while (!white.isEmpty()) {
			sb.append(white.poll()).append(SPACE);
		}
		System.out.print(sb);
	}
}
