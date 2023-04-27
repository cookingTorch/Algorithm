import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	// 행렬 곱
	private static long[][] matrixMultiplication(long[][] matrix1, long[][] matrix2, int size) {
		int i, j, k;
		long[][] resultMatrix = new long[size][size];
		for (i = 0; i < size; i++) {
			for (j = 0; j < size; j++) {
				for (k = 0; k < size; k++) {
					resultMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
					resultMatrix[i][j] %= 1000003;
				}
			}
		}
		return resultMatrix;
	}
	
	// 행렬 거듭제곱
	private static long[][] matrixPower(long[][] matrix, int exponent, int size) {
		if (exponent == 1) {
			return matrix;
		}
		else if (exponent % 2 == 0) {
			long[][] halfPowerMatrix = Arrays.stream(matrixPower(matrix, exponent / 2, size)).map(long[]::clone).toArray(long[][]::new);
			return matrixMultiplication(halfPowerMatrix, halfPowerMatrix, size);
		}
		else {
			return matrixMultiplication(matrixPower(matrix, exponent - 1, size), matrix, size);
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int n, s, e, t, i, j;
		long[][] adjacencyMatrix;
		
		// 입력
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		// 인접 행렬
		adjacencyMatrix = new long[5 * n][5 * n];
		for (i = 0; i < n; i++) {
			str = br.readLine();
			for (j = 0; j < n; j++) {
				if (str.charAt(j) - '0' > 0) {
					adjacencyMatrix[5 * i + (str.charAt(j) - '0' - 1)][5 * j]++;
				}
			}
			for (j = 0; j < 4; j++) {
				adjacencyMatrix[5 * i + j][5 * i + j + 1]++;
			}
		}
		
		// 거듭제곱
		sb.append(matrixPower(adjacencyMatrix, t, 5 * n)[5 * (s - 1)][5 * (e - 1)]);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

}