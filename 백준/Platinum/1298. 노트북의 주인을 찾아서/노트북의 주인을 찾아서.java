import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int[] laptops;
	private static boolean[] visited;
	private static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
	
	private static boolean match(int student) {
		int i, idx;
		
		for (i = 0; i < adj.get(student).size(); i++) {
			idx = adj.get(student).get(i);
			if (visited[idx]) {
				continue;
			}
			visited[idx] = true;
			if (laptops[idx] == -1 || match(laptops[idx])) {
				laptops[idx] = student;
				return true;
			}
		}
		return false;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, student, laptop, ans, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		for (i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			student = Integer.parseInt(st.nextToken()) - 1;
			laptop = Integer.parseInt(st.nextToken()) - 1;
			adj.get(student).add(laptop);
		}
		
		laptops = new int[n];
		Arrays.fill(laptops, -1);
		ans = 0;
		for (i = 0; i < n; i++) {
			visited = new boolean[n];
			if (match(i)) {
				ans++;
			}
		}
		
		sb.append(ans);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
