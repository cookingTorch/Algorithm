import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	private static final int MAX_N = 100001;
	private static final boolean[] VISITED = new boolean[MAX_N];
	
	private static int cnt;
	private static int[] selection;
	private static boolean flag;
	private static boolean[] visited;
	private static HashSet<Integer> set;
	
	private static int dfs(int node) {
		int start;
		
		if (visited[node]) {
			return 0;
		}
		if (set.contains(node)) {
			flag = true;
			return node;
		}
		set.add(node);
		start = dfs(selection[node]);
		if (start == node) {
			cnt--;
			visited[node] = true;
			flag = false;
		} else if (flag) {
			cnt--;
		}
		visited[node] = true;
		return start;
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int n, i;
		
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			selection[i] = Integer.parseInt(st.nextToken());
		}
		visited = Arrays.copyOf(VISITED, n + 1);
		cnt = n;
		for (i = 1; i <= n; i++) {
			if (!visited[i]) {
				set = new HashSet<>();
				flag = false;
				dfs(i);
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		selection = new int[MAX_N];
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			sb.append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
