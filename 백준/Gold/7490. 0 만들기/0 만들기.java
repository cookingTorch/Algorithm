import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	private static final int MAX = 10;
	private static final int SIZE = 500;
	private static final int DIFF = '0';
	private static final char PLUS = '+';
	private static final char MINUS = '-';
	private static final char SPACE = ' ';
	
	private static int max;
	private static int[] num;
	private static int[] idx;
	private static byte[] expression;
	private static ArrayList<ArrayList<byte[]>> list;
	
	private static final void dfs(int sum, int temp, int depth) {
		int next;
		
		if (num[depth] >= 0 && sum + temp == 0) {
			list.get(num[depth]).add(Arrays.copyOf(expression, idx[depth] + 2));
		}
		if (depth == max) {
			return;
		}
		next = depth + 1;
		expression[idx[next]] = SPACE;
		dfs(sum, temp * 10 + (temp > 0 ? next : -next), next);
		expression[idx[next]] = PLUS;
		dfs(sum + temp, next, next);
		expression[idx[next]] = MINUS;
		dfs(sum + temp, -next, next);
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int t;
		int i;
		byte j;
		byte[] ans;
		BufferedReader br;
		
		num = new int[MAX];
		Arrays.fill(num, -1);
		idx = new int[MAX];
		for (i = 1; i < MAX; i++) {
			idx[i] = ((i - 1) << 1) - 1;
		}
		list = new ArrayList<>();
		br = new BufferedReader(new InputStreamReader(System.in));
		t = br.read() - DIFF;
		for (i = 0; i < t; i++) {
			br.read();
			n = br.read() - DIFF;
			num[n] = i;
			list.add(new ArrayList<>());
			max = Math.max(max, n);
		}
		expression = new byte[(max << 1) + 1];
		for (i = 1, j = '1'; i <= max; i++, j++) {
			expression[idx[i] + 1] = j;
		}
		dfs(0, 1, 1);
		ans = new byte[SIZE];
		i = 0;
		for (ArrayList<byte[]> strs : list) {
			for (byte[] str : strs) {
				System.arraycopy(str, 0, ans, i, str.length);
				i += str.length;
				ans[i++] = '\n';
			}
			ans[i++] = '\n';
		}
		System.out.write(ans, 0, i);
	}
}
