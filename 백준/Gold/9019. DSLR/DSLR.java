import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int a, b, curr;
	private static int[] prev;
	private static char[] command;
	private static Queue<Integer> q;
	
	private static boolean add(char dslr, int next) {
		if (command[next] == '\0') {
			command[next] = dslr;
			prev[next] = curr;
			if (next == b) {
				return true;
			}
			q.add(next);
		}
		return false;
	}
	
	private static void bfs() {
		command = new char[10000];
		q = new ArrayDeque<>();
		q.add(a);
		while (!q.isEmpty()) {
			curr = q.poll();
			if (add('D', curr * 2 % 10000)
					|| add('S', curr == 0 ? 9999 : curr - 1)
					|| add('L', curr % 1000 * 10 + curr / 1000)
					|| add('R', curr / 10 + curr % 10 * 1000)) {
				break;
			}
		}
	}
	
	private static StringBuilder ans(int curr) {
		if (curr == a) {
			return new StringBuilder();
		}
		return ans(prev[curr]).append(command[curr]);
	}
	
	private static StringBuilder solution(BufferedReader br, StringTokenizer st) throws IOException {
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		bfs();
		return ans(b);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		prev = new int[10000];
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			sb.append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
