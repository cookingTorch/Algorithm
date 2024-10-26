import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final char D = 'D';
	private static final char G = 'G';
	private static final char[] DUDUDUNGA = {D, 'U', D, 'U', D, 'U', 'N', G, 'A'};

	public static void main(String[] args) throws IOException {
		int n;
		int[] degree;
		int[][] edges;
		long du;
		long ga;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		degree = new int[n + 1];
		edges = new int[n - 1][2];
		for (int[] edge : edges) {
			st = new StringTokenizer(br.readLine(), " ", false);
			degree[edge[0] = Integer.parseInt(st.nextToken())]++;
			degree[edge[1] = Integer.parseInt(st.nextToken())]++;
		}
		du = ga = 0L;
		for (; n > 0; n--) {
			if (degree[n] > 2) {
				ga += (long) (degree[n]) * (degree[n] - 1) * (degree[n] - 2) / 6L;
			}
			degree[n]--;
		}
		for (int[] edge : edges) {
			du += (long) degree[edge[0]] * degree[edge[1]];
		}
		if (du > 3 * ga) {
			System.out.print(D);
		} else if (du < 3 * ga) {
			System.out.print(G);
		} else {
			System.out.print(DUDUDUNGA);
		}
	}
}
