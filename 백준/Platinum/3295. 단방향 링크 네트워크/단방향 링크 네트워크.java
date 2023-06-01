import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] linked;
	private static boolean[] visited;
	private static ArrayList<ArrayList<Integer>> adj;
	
	// 매칭
	private static boolean bipartiteMatch(int node) {
		int i, end;
		for (i = 0; i < adj.get(node).size(); i++) {
			end = adj.get(node).get(i);
			if (visited[end]) {
				continue;
			}
			visited[end] = true;
			if (linked[end] == -1 || bipartiteMatch(linked[end])) {
				linked[end] = node;
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int t, testCase, n, m, i, ans;
		
		// 테스트 케이스
		str = br.readLine();
		t = Integer.parseInt(str);
		for (testCase = 0; testCase < t; testCase++) {
			
			// 간선 입력
			str = br.readLine();
			st = new StringTokenizer(str);
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			adj = new ArrayList<>();
			for (i = 0; i < n; i++) {
				adj.add(new ArrayList<>());
			}
			for (i = 0; i < m; i++) {
				str = br.readLine();
				st = new StringTokenizer(str);
				adj.get(Integer.parseInt(st.nextToken())).add(Integer.parseInt(st.nextToken()));
			}
			
			// 이분 매칭
			ans = 0;
			linked = new int[n];
			Arrays.fill(linked, -1);
			for (i = 0; i < n; i++) {
				visited = new boolean[n];
				if (bipartiteMatch(i)) {
					ans++;
				}
			}
			
			// 출력
			sb.append(ans).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}