import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int P = 998244353;
	private static final long[][] FIRST = {{0L, 1L, 0L}, {0L, 0L, 1L}, {1L, 1L, 0L}};
	
    private static long[][] multiply(long[][] a, long[][] b) {
        long[][] c;

        c = new long[3][3];
        c[0][0] = ((a[0][0] * b[0][0]) + (a[0][1] * b[1][0]) + (a[0][2] * b[2][0])) % P;
        c[0][1] = ((a[0][0] * b[0][1]) + (a[0][1] * b[1][1]) + (a[0][2] * b[2][1])) % P;
        c[0][2] = ((a[0][0] * b[0][2]) + (a[0][1] * b[1][2]) + (a[0][2] * b[2][2])) % P;
        c[1][0] = ((a[1][0] * b[0][0]) + (a[1][1] * b[1][0]) + (a[1][2] * b[2][0])) % P;
        c[1][1] = ((a[1][0] * b[0][1]) + (a[1][1] * b[1][1]) + (a[1][2] * b[2][1])) % P;
        c[1][2] = ((a[1][0] * b[0][2]) + (a[1][1] * b[1][2]) + (a[1][2] * b[2][2])) % P;
        c[2][0] = ((a[2][0] * b[0][0]) + (a[2][1] * b[1][0]) + (a[2][2] * b[2][0])) % P;
        c[2][1] = ((a[2][0] * b[0][1]) + (a[2][1] * b[1][1]) + (a[2][2] * b[2][1])) % P;
        c[2][2] = ((a[2][0] * b[0][2]) + (a[2][1] * b[1][2]) + (a[2][2] * b[2][2])) % P;
        return c;
    }

    private static long[][] power(long[][] matrix, long n) {
    	long[][] sqrt;
    	
        if (n == 1) {
        	return matrix;
        }
        if (n % 2 == 0) {
            sqrt = power(matrix, n / 2);
            return multiply(sqrt, sqrt);
        }
        return multiply(power(matrix, n - 1), matrix);
    }
    
    private static long solution(BufferedReader br) throws IOException {
    	return power(FIRST, Long.parseLong(br.readLine()))[2][1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int t, testCase;
        
        t = Integer.parseInt(br.readLine());
        for (testCase = 0; testCase < t; testCase++) {
        	sb.append(solution(br)).append('\n');
        }
        System.out.print(sb);
    }
}
