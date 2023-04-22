import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int[] shed;
	static int[][] shedPreference;
	static boolean[] visited;
	
	private static boolean assignCow(int cowNum) {
		
		for (int i = 0; i < shedPreference[cowNum].length; i++) {
			
			int shedIndex = shedPreference[cowNum][i];
			
			if (visited[shedIndex]) {
				continue;
			}
			
			visited[shedIndex] = true;

			if (shed[shedIndex] == -1 || assignCow(shed[shedIndex])) {
				shed[shedIndex] = cowNum;
				return true;
			}
		}
		
		return false;
		
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		StringTokenizer st;
		
		int n, m, i, j, ans = 0;
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		shed = new int[m];
		shedPreference = new int[n][];
		visited = new boolean[m];
		for (i = 0; i < m; i++) {
			shed[i] = -1;
		}
		
		for (i = 0; i < n; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			shedPreference[i] = new int[Integer.parseInt(st.nextToken())];
			for (j = 0; j < shedPreference[i].length; j++) {
				shedPreference[i][j] = Integer.parseInt(st.nextToken()) - 1;
			}
		}
		
		for (i = 0; i < n; i++) {
			visited = new boolean[m];
			if (assignCow(i)) {
				ans++;
			}
		}
		
		bw.write(Integer.toString(ans));
		
		bw.flush();
		bw.close();
	}
	
}