import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int NUM = 1000;
	private static int n;
	
	private static int[][] multiply(int [][] a, int[][] c) {
		int i, j, k;
		int[][] result;
		
		result = new int[n][n];
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				for (k = 0; k < n; k++) {
					result[i][j] = (result[i][j] + (a[i][k] * c[k][j])) % NUM;
				}
			}
		}
		return result;
	}
	
	private static int[][] power(int[][] a, long b) {
		int[][] root;
		
		if (b == 1) {
			return a;
		}
		if ((b & 1) == 0) {
			root = power(a, b >> 1);
			return multiply(root, root);
		}
		return multiply(power(a, b - 1), a);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int i, j;
		int[][] a, result;
		long b;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		b = Long.parseLong(st.nextToken());
		a = new int[n][n];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < n; j++) {
				a[i][j] = Integer.parseInt(st.nextToken()) % NUM;
			}
		}
		result = power(a, b);
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				sb.append(result[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}
