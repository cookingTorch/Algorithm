import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	private static final int SIZE = 10;
	private static final char PLUS = '+';
	private static final char MINUS = '-';
	private static final char SPACE = ' ';
	
	private static int max;
	private static int[] num;
	private static int[] idx;
	private static char[] expression;
	private static ArrayList<ArrayList<char[]>> strs;
	
	private static final void dfs(int sum, int temp, int depth) {
		int next;
		
		if (num[depth] >= 0 && sum + temp == 0) {
			strs.get(num[depth]).add(Arrays.copyOf(expression, idx[depth] + 2));
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
		char j;
		StringBuilder sb;
		BufferedReader br;
		
		num = new int[SIZE];
		Arrays.fill(num, -1);
		idx = new int[SIZE];
		for (i = 1; i < SIZE; i++) {
			idx[i] = ((i - 1) << 1) - 1;
		}
		strs = new ArrayList<>();
		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		for (i = 0; i < t; i++) {
			n = Integer.parseInt(br.readLine());
			num[n] = i;
			strs.add(new ArrayList<>());
			max = Math.max(max, n);
		}
		expression = new char[(max << 1) + 1];
		for (i = 1, j = '1'; i <= max; i++, j++) {
			expression[idx[i] + 1] = j;
		}
		dfs(0, 1, 1);
		sb = new StringBuilder();
		for (ArrayList<char[]> ans : strs) {
			for (char[] str : ans) {
				sb.append(str).append('\n');
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}
