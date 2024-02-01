import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int s, p, cnt, i, j;
		char[] str;
		int[] min;
		int[][] sum;
		
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		sum = new int[s + 1][];
		sum[0] = new int[4];
		str = br.readLine().toCharArray();
		for (i = 1; i <= s; i++) {
			sum[i] = Arrays.copyOf(sum[i - 1], 4);
			switch (str[i - 1]) {
			case 'A':
				sum[i][0]++;
				break;
			case 'C':
				sum[i][1]++;
				break;
			case 'G':
				sum[i][2]++;
				break;
			case 'T':
				sum[i][3]++;
				break;
			}
		}
		min = new int[4];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < 4; i++) {
			min[i] = Integer.parseInt(st.nextToken());
		}
		cnt = 0;
		for (i = p; i <= s; i++) {
			for (j = 0; j < 4; j++) {
				if (sum[i][j] - sum[i - p][j] < min[j]) {
					break;
				}
			}
			if (j == 4) {
				cnt++;
			}
		}
		System.out.print(cnt);
	}
}
