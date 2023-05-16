import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static int t, n;
	private static long[][][] map;
	private static long[][][] cumulativeProduct;
	
	// 행렬곱
	private static long[][] multiplyMatrix(long[][] matrix1, long[][] matrix2) {
		int i, j, k;
		long[][] returnMatrix = new long[n][n];
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				for (k = 0; k < n; k++) {
					returnMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
					returnMatrix[i][j] %= 1000000007;
				}
			}
		}
		return returnMatrix;
	}
	
	// 행렬 제곱
	private static long[][] squareMatrix(long[][] matrix) {
		int i, j, k;
		long[][] returnMatrix = new long[n][n];
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				for (k = 0; k < n; k++) {
					returnMatrix[i][j] += matrix[i][k] * matrix[k][j];
					returnMatrix[i][j] %= 1000000007;
				}
			}
		}
		return returnMatrix;
	}
	
	// 행렬 거듭제곱
	private static long[][] matrixPow(long[][] matrix, int exp) {
		if (exp == 1) {
			return matrix;
		}
		else if (exp % 2 == 0) {
			return squareMatrix(matrixPow(matrix, exp / 2));
		}
		else {
			return multiplyMatrix(matrixPow(matrix, exp - 1), matrix);
		}
	}
	
	// 경로 탐색
	private static long[][] findMatrix(int d) {
		int quotient, remainder, i;
		long[][] ones = new long[n][n];
		quotient = d / t;
		remainder = d % t;
		if (d == 0) {
			for (i = 0; i < n; i++) {
				Arrays.fill(ones[i], 1);
			}
			return ones;
		}
		else if (quotient == 0) {
			return cumulativeProduct[remainder - 1];
		}
		else if (remainder == 0) {
			return matrixPow(cumulativeProduct[t - 1], quotient);
		}
		else {
			return multiplyMatrix(matrixPow(cumulativeProduct[t - 1], quotient), cumulativeProduct[remainder - 1]);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int d, m, a, b, c, i, j;
		long[][] ansMatrix;
		
		// 지도 입력
		str = br.readLine();
		st = new StringTokenizer(str);
		t = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		map = new long[t][n][n];
		for (i = 0; i < t; i++) {
			str = br.readLine();
			m = Integer.parseInt(str);
			for (j = 0; j < m; j++) {
				str = br.readLine();
				st = new StringTokenizer(str);
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				map[i][a - 1][b - 1] = c;
			}
		}
		
		// 전처리
		cumulativeProduct = new long[t][n][n];
		for (i = 0; i < n; i++) {
			cumulativeProduct[0][i] = Arrays.copyOf(map[0][i], n);
		}
		for (i = 1; i < t; i++) {
			cumulativeProduct[i] = multiplyMatrix(cumulativeProduct[i - 1], map[i]);
		}
		
		// 답 출력
		ansMatrix = findMatrix(d);
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				sb.append(ansMatrix[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}