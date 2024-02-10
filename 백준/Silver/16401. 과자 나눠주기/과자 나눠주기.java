import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, left, right, mid, nephew, i;
		int[] stick;
		
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		stick = new int[n];
		right = 0;
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			stick[i] = Integer.parseInt(st.nextToken());
			right = Math.max(right, stick[i]);
		}
		left = 1;
		right++;
		while (left < right) {
			mid = (left + right) / 2;
			nephew = 0;
			for (i = 0; i < n; i++) {
				nephew += stick[i] / mid;
			}
			if (nephew < m) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		System.out.print(left - 1);
	}
}
