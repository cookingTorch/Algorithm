import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static int[] parent;
	
	private static int first(int node) {
		while (parent[node] != node) {
			node = parent[node];
		}
		return node;
	}
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, i, from, to;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		parent = new int[n];
		for (i = 0; i < n; i++) {
			parent[i] = i;
		}
		
		for (i = 0; i <= m; i++) {
			if (i == m) {
				sb.append(0);
				break;
			}
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			if (first(from) == first(to)) {
				sb.append(i + 1);
				break;
			}
			parent[first(to)] = first(from);
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}