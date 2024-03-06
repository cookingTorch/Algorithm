import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, i;
		int[][] xa;
		long sum, prefix;
		
		n = Integer.parseInt(br.readLine());
		xa = new int[n][2];
		sum = 0;
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			xa[i][0] = Integer.parseInt(st.nextToken());
			xa[i][1] = Integer.parseInt(st.nextToken());
			sum += xa[i][1];
		}
		Arrays.sort(xa, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		});
		sum = (sum + 1) / 2;
		prefix = 0;
		for (i = 0; i < n; i++) {
			prefix += xa[i][1];
			if (prefix >= sum) {
				System.out.print(xa[i][0]);
				return;
			}
		}
	}
}
