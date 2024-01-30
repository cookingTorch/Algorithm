import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, num, i, j;
		int[] light;
		
		n = Integer.parseInt(br.readLine());
		light = new int[n + 2];
		light[0] = -1;
		light[n + 1] = -2;
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			light[i] = Integer.parseInt(st.nextToken());
		}
		m = Integer.parseInt(br.readLine());
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			if (Integer.parseInt(st.nextToken()) == 1) {
				num = Integer.parseInt(st.nextToken());
				for (j = num; j <= n; j += num) {
					light[j] ^= 1;
				}
			} else {
				num = Integer.parseInt(st.nextToken());
				light[num] ^= 1;
				for (j = 1; light[num - j] == light[num + j]; j++) {
					light[num - j] ^= 1;
					light[num + j] ^= 1;
				}
			}
		}
		for (i = 1; i <= n; i++) {
			sb.append(light[i]).append(' ');
			if (i % 20 == 0) {
				sb.append('\n');
			}
		}
		System.out.print(sb);
	}
}
