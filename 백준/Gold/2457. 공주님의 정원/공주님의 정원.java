import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	private static final int[] DAYS = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, left, right, cnt, i;
		int[] curr, days;
		int[][] arr;
		boolean flag;
		
		days = new int[12];
		for (i = 1; i < 12; i++) {
			days[i] = days[i - 1] + DAYS[i];
		}
		n = Integer.parseInt(br.readLine());
		arr = new int[n][2];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = days[Integer.parseInt(st.nextToken()) - 1] + Integer.parseInt(st.nextToken()) - 1;
			arr[i][1] = days[Integer.parseInt(st.nextToken()) - 1] + Integer.parseInt(st.nextToken()) - 2;
		}
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		});
		cnt = 0;
		left = days[3 - 1] + 1 - 1;
		right = left - 1;
		i = 0;
		while (true) {
			flag = false;
			for (; i < n; i++) {
				curr = arr[i];
				if (curr[0] > left) {
					break;
				}
				flag = true;
				right = Math.max(right, curr[1]);
			}
			left = right + 1;
			cnt++;
			if (!flag || i >= n || right >= days[11 - 1] + 30 - 1) {
				break;
			}
		}
		if (right < days[11 - 1] + 30 - 1) {
			System.out.print('0');
			return;
		}
		System.out.print(cnt);
	}
}
