import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static final class Box implements Comparable<Box> {
		int idx, area, height, weight;
		
		Box(int idx, int area, int height, int weight) {
			this.idx = idx;
			this.area = area;
			this.height = height;
			this.weight = weight;
		}

		@Override
		public int compareTo(Box o) {
			if (o.area == this.area) {
				return Integer.compare(o.weight, this.weight);
			}
			return Integer.compare(o.area, this.area);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, max, idx, i, j;
		int[] dp, prev;
		Box[] arr;
		ArrayList<Integer> ans;
		
		n = Integer.parseInt(br.readLine());
		arr = new Box[n];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new Box(i + 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(arr);
		dp = new int[n];
		prev = new int[n];
		Arrays.fill(prev, -1);
		max = 0;
		idx = 0;
		for (i = 0; i < n; i++) {
			for (j = 0; j < i; j++) {
				if (arr[j].weight > arr[i].weight) {
					if (dp[j] > dp[i]) {
						dp[i] = dp[j];
						prev[i] = j;
					}
				}
			}
			dp[i] += arr[i].height;
			if (dp[i] > max) {
				max = dp[i];
				idx = i;
			}
		}
		ans = new ArrayList<>();
		for (i = idx; i != -1; i = prev[i]) {
			ans.add(arr[i].idx);
		}
		sb.append(ans.size()).append('\n');
		for (int num : ans) {
			sb.append(num).append('\n');
		}
		System.out.print(sb);
	}
}
