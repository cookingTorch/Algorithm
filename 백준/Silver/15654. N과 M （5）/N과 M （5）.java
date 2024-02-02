import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int n, m;
	private static int[] nums, ans;
	private static boolean[] isSelected;
	
	private static void permu(int depth, StringBuilder sb) {
		int i;
		
		if (depth == m) {
			for (int num : ans) {
				sb.append(num).append(' ');
			}
			sb.append('\n');
			return;
		}
		for (i = 0; i < n; i++) {
			if (isSelected[i]) {
				continue;
			}
			ans[depth] = nums[i];
			isSelected[i] = true;
			permu(depth + 1, sb);
			isSelected[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		nums = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);
		isSelected = new boolean[n];
		ans = new int[m];
		permu(0, sb);
		System.out.print(sb);
	}
}
