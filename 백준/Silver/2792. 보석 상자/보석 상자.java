import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, left, right, mid, student, i;
		int[] jewel;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		jewel = new int[m];
		right = 0;
		for (i = 0; i < m; i++) {
			jewel[i] = Integer.parseInt(br.readLine());
			right = Math.max(right, jewel[i]);
		}
		left = 1;
		while (left < right) {
			mid = (left + right) / 2;
			student = 0;
			for (i = 0; i < m; i++) {
				student += (jewel[i] - 1) / mid + 1;
			}
			if (student > n) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		System.out.print(right);
	}
}
