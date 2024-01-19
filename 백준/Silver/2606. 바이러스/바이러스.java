import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static boolean[] visited;
	private static ArrayList<ArrayList<Integer>> adj;
	
	private static int virus(int com) {
		int val;
		
		if (visited[com]) {
			return 0;
		}
		visited[com] = true;
		val = 1;
		for (int next : adj.get(com)) {
			val += virus(next);
		}
		return val;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int comCnt, adjCnt, com1, com2, i;
		
		comCnt = Integer.parseInt(br.readLine());
		adjCnt = Integer.parseInt(br.readLine());
		adj = new ArrayList<>();
		for (i = 0; i < comCnt; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < adjCnt; i++) {
			st = new StringTokenizer(br.readLine());
			com1 = Integer.parseInt(st.nextToken()) - 1;
			com2 = Integer.parseInt(st.nextToken()) - 1;
			adj.get(com1).add(com2);
			adj.get(com2).add(com1);
		}
		visited = new boolean[comCnt];
		System.out.print(virus(0) - 1);
	}
}