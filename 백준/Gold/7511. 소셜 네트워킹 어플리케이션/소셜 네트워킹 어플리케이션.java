import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final String CONNECTED = "1\n";
	private static final String UNCONNECTED = "0\n";
	private static final String FIRST_SCENARIO = "Scenario ";
	private static final String SCENARIO = "\nScenario ";
	private static final String COLON = ":\n";
	
	private static int[] roots;
	private static StringBuilder sb;
	private static BufferedReader br;
	
	private static final int find(int v) {
		if (roots[v] <= 0) {
			return v;
		}
		return roots[v] = find(roots[v]);
	}
	
	private static final void union(int u, int v) {
		int ru;
		int rv;
		
		ru = find(u);
		rv = find(v);
		if (ru == rv) {
			return;
		}
		if (roots[ru] > roots[rv]) {
			roots[ru] = rv;
		} else {
			if (roots[ru] == roots[rv]) {
				roots[ru]--;
			}
			roots[rv] = ru;
		}
	}
	
	private static final void solution() throws IOException {
		int n;
		int k;
		int m;
		int i;
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		roots = new int[n + 1];
		k = Integer.parseInt(br.readLine());
		for (i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			union(Integer.parseInt(st.nextToken()) + 1, Integer.parseInt(st.nextToken()) + 1);
		}
		m = Integer.parseInt(br.readLine());
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			if (find(Integer.parseInt(st.nextToken()) + 1) == find(Integer.parseInt(st.nextToken()) + 1)) {
				sb.append(CONNECTED);
			} else {
				sb.append(UNCONNECTED);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		int t;
		int testCase;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		testCase = 1;
		sb.append(FIRST_SCENARIO).append(testCase).append(COLON);
		solution();
		for (++testCase; testCase <= t; testCase++) {
			sb.append(SCENARIO).append(testCase).append(COLON);
			solution();
		}
		System.out.print(sb);
	}
}
