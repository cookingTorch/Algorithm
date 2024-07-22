import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int ans;
		int size;
		long i;
		long j;
		long min;
		long max;
		long num;
		long thr;
		boolean[] sqrt;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		min = Long.parseLong(st.nextToken());
		max = Long.parseLong(st.nextToken());
		size = (int) (max - min + 1L);
		sqrt = new boolean[size];
		thr = (long) Math.ceil(Math.sqrt(max));
		ans = size;
		for (i = 2; i <= thr; i++) {
			num = i * i;
			for (j = (min - 1) / num * num + num; j <= max; j += num) {
				if (!sqrt[(int) (j - min)]) {
					sqrt[(int) (j - min)] = true;
					ans--;
				}
			}
		}
		System.out.print(ans);
	}
}
