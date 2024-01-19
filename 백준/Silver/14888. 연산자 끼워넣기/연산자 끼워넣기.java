import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int MAX = Integer.MAX_VALUE;
	private static final int MIN = Integer.MIN_VALUE;
	
	private static int n;
	private static int[] arr;
	
	private static int[] maxMin(int add, int sub, int mul, int div, int val, int depth) {
		int max, min;
		int[] result;
		
		if (depth == n) {
			return new int[] {val, val};
		}
		max = MIN;
		min = MAX;
		if (add > 0) {
			result = maxMin(add - 1, sub, mul, div, val + arr[depth], depth + 1);
			max = Math.max(max, result[0]);
			min = Math.min(min, result[1]);
		}
		if (sub > 0) {
			result = maxMin(add, sub - 1, mul, div, val - arr[depth], depth + 1);
			max = Math.max(max, result[0]);
			min = Math.min(min, result[1]);
		}
		if (mul > 0) {
			result = maxMin(add, sub, mul - 1, div, val * arr[depth], depth + 1);
			max = Math.max(max, result[0]);
			min = Math.min(min, result[1]);
		}
		if (div > 0) {
			result = maxMin(add, sub, mul, div - 1, val / arr[depth], depth + 1);
			max = Math.max(max, result[0]);
			min = Math.min(min, result[1]);
		}
		return new int[] {max, min};
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int add, sub, div, mul, i;
		int[] ans;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		add = Integer.parseInt(st.nextToken());
		sub = Integer.parseInt(st.nextToken());
		mul = Integer.parseInt(st.nextToken());
		div = Integer.parseInt(st.nextToken());
		ans = maxMin(add, sub, mul, div, arr[0], 1);
		sb.append(ans[0]).append('\n').append(ans[1]);
		System.out.print(sb);
	}
}