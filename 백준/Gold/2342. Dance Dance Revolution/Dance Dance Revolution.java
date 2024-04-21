import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int DIFF = '0';
	private static final int MAX = 5;
	
	private static int size;
	private static int[] chart;
	private static int[][] idx, dp;
	
	private static final int getDp(int left, int right, int depth) {
		int note;
		
		if (depth == size) {
			return 0;
		}
		if (dp[idx[left][right]][depth] != 0) {
			return dp[idx[left][right]][depth];
		}
		note = chart[depth];
		if (note == left || note == right) {
			return dp[idx[left][right]][depth] = getDp(left, right, depth + 1) + 1;
		}
		return dp[idx[left][right]][depth] = Math.min(
				getDp(Math.min(note, right), Math.max(note, right), depth + 1) + (left == 0 ? 2 : ((note - left & 1) == 1 ? 3 : 4)),
				getDp(Math.min(left, note), Math.max(left, note), depth + 1) + ((note - right & 1) == 1 ? 3 : 4));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int len, i, j, index;
		String str;
		
		str = br.readLine();
		len = str.length() - 1;
		if (len == 0) {
			System.out.print("0");
			return;
		}
		size = len >> 1;
		chart = new int[size];
		for (i = 0; i < size; i++) {
			chart[i] = str.charAt(i << 1) - DIFF;
		}
		idx = new int[MAX - 1][MAX];
		index = 0;
		for (i = 0; i < MAX; i++) {
			for (j = i + 1; j < MAX; j++) {
				idx[i][j] = index++;
			}
		}
		dp = new int[index][size];
		System.out.print(getDp(0, chart[0], 1) + 2);
	}
}
