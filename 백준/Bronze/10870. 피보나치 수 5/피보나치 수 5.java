import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final long[][] FIRST = {{1L, 1L}, {1L, 0L}};
	
    private static long[][] multiply(long[][] a, long[][] b) {
        long[][] c;

        
        c = new long[2][2];
        c[0][0] = ((a[0][0] * b[0][0]) + (a[0][1] * b[1][0]));
        c[0][1] = ((a[0][0] * b[0][1]) + (a[0][1] * b[1][1]));
        c[1][0] = ((a[1][0] * b[0][0]) + (a[1][1] * b[1][0]));
        c[1][1] = ((a[1][0] * b[0][1]) + (a[1][1] * b[1][1]));
        return c;
    }

    private static long[][] power(long[][] matrix, long n) {
    	long[][] sqrt;
    	
        if (n == 0) {
        	return new long[2][2];
        }
        if (n == 1) {
        	return matrix;
        }
        if (n % 2 == 0) {
            sqrt = power(matrix, n / 2);
            return multiply(sqrt, sqrt);
        }
        return multiply(power(matrix, n - 1), matrix);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print(power(FIRST, Long.parseLong(br.readLine()))[0][1]);
    }
}
