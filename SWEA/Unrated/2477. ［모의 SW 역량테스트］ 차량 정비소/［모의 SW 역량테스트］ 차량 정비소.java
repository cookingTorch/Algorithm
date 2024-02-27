import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
 
class Solution {
    private static final int MAX_N = 9;
    private static final int MAX_M = 9;
    private static final int MAX_TK = 1001;
    private static final Customer EMPTY = null;
     
    private static class Customer {
        int idx, time, a;
         
        Customer(int idx) {
            this.idx = idx;
        }
         
        Customer setA(int a) {
            this.a = a;
            return this;
        }
         
        Customer setTime(int time) {
            this.time = time;
            return this;
        }
    }
     
    private static int n, m, k, a, b;
    private static int[] ai, bi, tk;
    private static Customer[] reception, repair;
    private static Queue<Customer> qA, qB;
     
    private static int repairShop() {
        int time, cnt, num, idx, ans, i;
        Customer customer;
         
        ans = 0;
        idx = 1;
        for (cnt = 0, time = 0; cnt < k; time++) {
            if (time < MAX_TK) {
                num = tk[time];
                for (i = 0; i < num; i++, tk[time]--) {
                    qA.add(new Customer(idx++));
                }
            }
            for (i = 0; i < n; i++) {
                customer = reception[i];
                if (customer != EMPTY && customer.time == time) {
                    qB.add(customer);
                    reception[i] = EMPTY;
                }
            }
            for (i = 0; i < m; i++) {
                customer = repair[i];
                if (customer != EMPTY && customer.time == time) {
                    if (customer.a == a && i == b) {
                        ans += customer.idx;
                    }
                    repair[i] = EMPTY;
                    cnt++;
                }
            }
            for (i = 0; !qA.isEmpty() && i < n; i++) {
                if (reception[i] == EMPTY) {
                    reception[i] = qA.poll().setA(i).setTime(time + ai[i]);
                }
            }
            for (i = 0; !qB.isEmpty() && i < m; i++) {
                if (repair[i] == EMPTY) {
                    repair[i] = qB.poll().setTime(time + bi[i]);
                }
            }
        }
        return ans == 0 ? -1 : ans;
    }
     
    private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
        int i;
         
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken()) - 1;
        b = Integer.parseInt(st.nextToken()) - 1;
        st = new StringTokenizer(br.readLine());
        for (i = 0; i < n; i++) {
            ai[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (i = 0; i < m; i++) {
            bi[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (i = 0; i < k; i++) {
            tk[Integer.parseInt(st.nextToken())]++;
        }
        return repairShop();
    }
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
         
        int t, testCase;
         
        reception = new Customer[MAX_N];
        repair = new Customer[MAX_M];
        ai = new int[MAX_N];
        bi = new int[MAX_M];
        tk = new int[MAX_TK];
        qA = new ArrayDeque<>();
        qB = new ArrayDeque<>();
        t = Integer.parseInt(br.readLine());
        for (testCase = 1; testCase <= t; testCase++) {
            sb.append('#').append(testCase).append(' ').append(solution(br, st)).append('\n');
        }
        System.out.print(sb);
    }
}