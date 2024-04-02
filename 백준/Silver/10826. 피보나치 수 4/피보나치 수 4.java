import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	private static final BigInteger ZERO = new BigInteger("0");
	private static final BigInteger ONE = new BigInteger("1");
	private static final BigInteger[][] FIRST = {{ONE, ONE}, {ONE, ZERO}};
	
    private static BigInteger[][] multiply(BigInteger[][] a, BigInteger[][] b) {
    	BigInteger[][] c;

        c = new BigInteger[2][2];
        c[0][0] = a[0][0].multiply(b[0][0]).add(a[0][1].multiply(b[1][0]));
        c[0][1] = a[0][0].multiply(b[0][1]).add(a[0][1].multiply(b[1][1]));
        c[1][0] = a[1][0].multiply(b[0][0]).add(a[1][1].multiply(b[1][0]));
        c[1][1] = a[1][0].multiply(b[0][1]).add(a[1][1].multiply(b[1][1]));
        return c;
    }

    private static BigInteger[][] power(BigInteger[][] matrix, long n) {
    	BigInteger[][] sqrt;
    	
        if (n == 0) {
        	return new BigInteger[][] {{ZERO, ZERO}, {ZERO, ZERO}};
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
