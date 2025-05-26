import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    private static ArrayList<Integer> primes1;
    private static ArrayList<Integer> primes2;
    private static ArrayList<Integer> primes3;
    private static ArrayList<Integer> primes4;
    private static ArrayList<Integer> primes5;
    private static ArrayList<Integer> primes6;

    private static void genPrimes(int val) {
        int i;
        int j;
        int k;
        int l;
        int m;
        int n;
        int max;
        int size;
        long v1;
        long v2;
        long v3;
        long v4;
        long v5;
        long v6;
        boolean[] isPrime;

        max = (int) Math.sqrt(val);
        isPrime = new boolean[max + 1];
        for (i = 3; i <= max; i += 2) {
            isPrime[i] = true;
        }
        primes1 = new ArrayList<>();
        primes1.add(4);
        for (i = 3; i <= max; i += 2) {
            if (isPrime[i]) {
                primes1.add(i * i);
                for (j = i << 1; j <= max; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        size = primes1.size();
        primes2 = new ArrayList<>();
        primes3 = new ArrayList<>();
        primes4 = new ArrayList<>();
        primes5 = new ArrayList<>();
        primes6 = new ArrayList<>();
        for (i = 0; i < size; i++) {
            v1 = primes1.get(i);
            for (j = i + 1; j < size; j++) {
                if ((v2 = v1 * primes1.get(j)) > val) {
                    break;
                }
                primes2.add((int) v2);
                for (k = j + 1; k < size; k++) {
                    if ((v3 = v2 * primes1.get(k)) > val) {
                        break;
                    }
                    primes3.add((int) v3);
                    for (l = k + 1; l < size; l++) {
                        if ((v4 = v3 * primes1.get(l)) > val) {
                            break;
                        }
                        primes4.add((int) v4);
                        for (m = l + 1; m < size; m++) {
                            if ((v5 = v4 * primes1.get(m)) > val) {
                                break;
                            }
                            primes5.add((int) v5);
                            for (n = m + 1; n < size; n++) {
                                if ((v6 = v5 * primes1.get(n)) > val) {
                                    break;
                                }
                                primes6.add((int) v6);
                            }
                        }
                    }
                }
            }
        }
    }

    private static int getIdx(int val) {
        int idx;

        idx = val;
        for (int prime : primes1) {
            idx -= val / prime;
        }
        for (int prime : primes2) {
            idx += val / prime;
        }
        for (int prime : primes3) {
            idx -= val / prime;
        }
        for (int prime : primes4) {
            idx += val / prime;
        }
        for (int prime : primes5) {
            idx -= val / prime;
        }
        for (int prime : primes6) {
            idx += val / prime;
        }
        return idx;
    }

    private static int getAns(int idx) {
        int mid;
        int left;
        int right;

        genPrimes(idx << 1);
        left = idx;
        right = idx << 1 | 1;
        while (left < right) {
            mid = (int) ((long) left + right >>> 1);
            if (getIdx(mid) < idx) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    public static void main(String[] args) throws IOException {
        System.out.print(getAns(Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine())));
    }
}
