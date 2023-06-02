import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] matched;
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
			if (matched[end] == -1 || bipartiteMatch(matched[end])) {
				matched[end] = node;
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
		
		int n, m, work, i, j, ans;
		
		// 간선 입력
		str = br.readLine();
		st = new StringTokenizer(str);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		adj = new ArrayList<>();
		for (i = 0; i < n + 1; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 1; i < n + 1; i++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			work = Integer.parseInt(st.nextToken());
			for (j = 0; j < work; j++) {
				adj.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		// 이분 매칭
		ans = 0;
		matched = new int[m + 1];
		Arrays.fill(matched, -1);
		for (i = 1; i < n + 1; i++) {
			visited = new boolean[m + 1];
			if (bipartiteMatch(i)) {
				ans++;
			}
		}
		
		// 출력
		sb.append(ans);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}