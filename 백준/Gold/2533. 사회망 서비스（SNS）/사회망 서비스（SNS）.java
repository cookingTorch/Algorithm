import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	private static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
	
	private static int[] findMin(int from, int num) {
		if (from > 0 && adj.get(num).size() == 1) {
			return new int[] {0, 1};
		}
		else {
			int i, next, min = 0, min1 = 0;
			int[] temp;
			for (i = 0; i < adj.get(num).size(); i++) {
				next = adj.get(num).get(i);
				if (next == from) {
					continue;
				}
				temp = findMin(num, next);
				min += Math.min(temp[0], temp[1]);
				min1 += temp[1];
			}
			return new int[] {min1, min + 1};
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int n, i, u, v;
		int[] ans;
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		for (i = 0; i < n + 1; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < n - 1; i++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			adj.get(u).add(v);
			adj.get(v).add(u);
		}
		
		ans = findMin(0, 1);
		sb.append(Math.min(ans[0], ans[1]));
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}