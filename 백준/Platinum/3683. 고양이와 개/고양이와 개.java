import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] match;
	private static boolean[] visited;
	private static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
	
	// 매칭 판단
	private static boolean bipartiteMatch(int x) {
		visited[x] = true;
		for (int y : adj.get(x)) {
			if (match[y] == -1 || (!visited[match[y]] && bipartiteMatch(match[y]))) {
				match[y] = x;
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
		
		int t, testCase, v, i, j, cnt;
		ArrayList<int[]> catVote = new ArrayList<>(), dogVote = new ArrayList<>();
		
		// 테스트 케이스
		str = br.readLine();
		t = Integer.parseInt(str);
		for (testCase = 0; testCase < t; testCase++) {
			
			// 투표 입력
			str = br.readLine();
			st = new StringTokenizer(str);
			st.nextToken();
			st.nextToken();
			v = Integer.parseInt(st.nextToken());
			catVote.clear();
			dogVote.clear();
			for (i = 0; i < v; i++) {
				str = br.readLine();
				st = new StringTokenizer(str);
				if (str.charAt(0) == 'C') {
					catVote.add(new int[] {Integer.parseInt(st.nextToken().substring(1)), Integer.parseInt(st.nextToken().substring(1))});
				}
				else {
					dogVote.add(new int[] {Integer.parseInt(st.nextToken().substring(1)), Integer.parseInt(st.nextToken().substring(1))});
				}
			}
			
			// 간선 입력
			adj.clear();
			for (i = 0; i < catVote.size(); i++) {
				adj.add(new ArrayList<>());
			}
			for (i = 0; i < catVote.size(); i++) {
				for (j = 0; j < dogVote.size(); j++) {
					if (catVote.get(i)[0] == dogVote.get(j)[1] || catVote.get(i)[1] == dogVote.get(j)[0]) {
						adj.get(i).add(j);
					}
				}
			}
			
			// 이분 매칭
			match = new int[dogVote.size()];
			visited = new boolean[catVote.size()];
			Arrays.fill(match, -1);
			cnt = 0;
			for (i = 0; i < catVote.size(); i++) {
				Arrays.fill(visited, false);
				if (bipartiteMatch(i)) {
					cnt++;
				}
			}
			
			// MVC 제거
			sb.append(v - cnt).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}