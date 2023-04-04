import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n, m, i, j, k, l, temp;
		int[] sum = new int[100001];
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		
		for (k = 1; k <= n; k++) {
			sum[k] = sum[k - 1] + Integer.parseInt(st.nextToken());
		}
		
		for (k = 0; k < m; k++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			i = Integer.parseInt(st.nextToken());
			j = Integer.parseInt(st.nextToken());
			
			System.out.println(sum[j] - sum[i - 1]);
		}
		
	}

}