import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, d, k, c, num, max, i;
		int[] susi, cnt;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken()) - 1;
		susi = new int[n];
		cnt = new int[d];
		cnt[c]++;
		num = 1;
		for (i = 0; i < k; i++) {
			susi[i] = Integer.parseInt(br.readLine()) - 1;
			if (cnt[susi[i]]++ == 0) {
				num++;
			}
		}
		max = num;
		for (i = k; i < n; i++) {
			susi[i] = Integer.parseInt(br.readLine()) - 1;
			if (--cnt[susi[i - k]] == 0) {
				num--;
			}
			if (cnt[susi[i]]++ == 0) {
				num++;
			}
			max = Math.max(max, num);
		}
		for (i = 0; i < k - 1; i++) {
			if (--cnt[susi[i - k + n]] == 0) {
				num--;
			}
			if (cnt[susi[i]]++ == 0) {
				num++;
			}
			max = Math.max(max, num);
		}
		System.out.print(max);
	}
}