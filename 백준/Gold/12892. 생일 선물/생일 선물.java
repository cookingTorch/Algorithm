import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static final class Present implements Comparable<Present> {
		long p, v;
		
		Present(long p, long v) {
			this.p = p;
			this.v = v;
		}
		
		@Override
		public int compareTo(Present o) {
			return Long.compare(this.p, o.p);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, d, left, right, i;
		long sum, max;
		Present[] presents;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		presents = new Present[n];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			presents[i] = new Present(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(presents);
		sum = presents[0].v;
		max = sum;
		for (left = 0, right = 0;; sum -= presents[left++].v) {
			while (presents[right].p - presents[left].p < d) {
				max = Math.max(max, sum);
				if (++right == n) {
					System.out.print(max);
					return;
				}
				sum += presents[right].v;
			}
		}
	}
}
