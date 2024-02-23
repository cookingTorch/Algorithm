import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, d, k, c, susi, num, max, i;
		int[] cnt, first;
		Queue<Integer> dq;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		cnt = new int[d + 1];
		cnt[c]++;
		num = 1;
		max = 1;
		first = new int[k - 1];
		dq = new ArrayDeque<>();
		for (i = 0; i < k - 1; i++) {
			first[i] = susi = Integer.parseInt(br.readLine());
			dq.add(susi);
			if (cnt[susi]++ == 0) {
				num++;
			}
		}
		for (; i < n; i++) {
			susi = Integer.parseInt(br.readLine());
			dq.add(susi);
			if (cnt[susi]++ == 0) {
				num++;
			}
			max = Math.max(max, num);
			if (--cnt[dq.poll()] == 0) {
				num--;
			}
		}
		for (i = 0; i < k - 1; i++) {
			if (cnt[first[i]]++ == 0) {
				num++;
			}
			max = Math.max(max, num);
			if (--cnt[dq.poll()] == 0) {
				num--;
			}
		}
		System.out.print(max);
	}
}
