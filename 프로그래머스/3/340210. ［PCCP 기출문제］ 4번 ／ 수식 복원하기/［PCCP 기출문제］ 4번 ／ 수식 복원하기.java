import java.util.ArrayList;
import java.util.StringTokenizer;

class Solution {
    private static final int X = 'X';
    private static final int MAX = 9;
    private static final int DIFF = '0';
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char EQUAL = '=';
    private static final char SPACE = ' ';
    private static final char QUESTION_MARK = '?';

    private static final class Exp {
        private static int min;
        private static int radix;

        int[] a;
        int[] b;
        int[] c;
        boolean op;

        Exp(String expression) {
            String num;
            StringTokenizer st;

            st = new StringTokenizer(expression);
            num = st.nextToken();
            a = new int[2];
            if (num.length() == 1) {
                a[0] = num.charAt(0) - DIFF;
            } else {
                a[0] = num.charAt(1) - DIFF;
                a[1] = num.charAt(0) - DIFF;
            }
            op = st.nextToken().charAt(0) == PLUS;
            num = st.nextToken();
            b = new int[2];
            if (num.length() == 1) {
                b[0] = num.charAt(0) - DIFF;
            } else {
                b[0] = num.charAt(1) - DIFF;
                b[1] = num.charAt(0) - DIFF;
            }
            st.nextToken();
            num = st.nextToken();
            c = new int[3];
            if (num.length() == 1) {
                if (num.charAt(0) != X) {
                    c[0] = num.charAt(0) - DIFF;
                } else {
                    c = null;
                }
            } else if (num.length() == 2) {
                c[0] = num.charAt(1) - DIFF;
                c[1] = num.charAt(0) - DIFF;
            } else {
                c[0] = num.charAt(2) - DIFF;
                c[1] = num.charAt(1) - DIFF;
                c[2] = num.charAt(0) - DIFF;
            }
            calc();
        }

        private void calc() {
            if (radix != 0) {
                return;
            }
            min = Math.max(min, Math.max(Math.max(a[0], a[1]), Math.max(b[0], b[1])) + 1);
            if (c == null) {
                return;
            }
            min = Math.max(min, Math.max(Math.max(c[0], c[1]), c[2]) + 1);
            if (op) {
                if (a[0] + b[0] != c[0]) {
                    radix = a[0] + b[0] - c[0];
                } else if (a[1] + b[1] != c[1]) {
                    radix = a[1] + b[1] - c[1];
                }
            } else {
                if (c[0] + b[0] != a[0]) {
                    radix = c[0] + b[0] - a[0];
                } else if (c[1] + b[1] != a[1]) {
                    radix = c[1] + b[1] - a[1];
                }
            }
        }

        String eval() {
            int i;
            int num1;
            int num2;
            String res;
            String str;
            StringBuilder sb;

            sb = new StringBuilder();
            if (a[1] == 0) {
                sb.append(a[0]);
            } else {
                sb.append(a[1]).append(a[0]);
            }
            sb.append(SPACE).append(op ? PLUS : MINUS).append(SPACE);
            if (b[1] == 0) {
                sb.append(b[0]);
            } else {
                sb.append(b[1]).append(b[0]);
            }
            sb.append(SPACE).append(EQUAL).append(SPACE);
            if (radix == 0) {
                num1 = a[1] * min + a[0];
                num2 = b[1] * min + b[0];
                if (op) {
                    res = Integer.toString(num1 + num2, min);
                } else {
                    res = Integer.toString(num1 - num2, min);
                }
                for (i = min + 1; i <= MAX; i++) {
                    num1 = a[1] * i + a[0];
                    num2 = b[1] * i + b[0];
                    if (op) {
                        str = Integer.toString(num1 + num2, i);
                    } else {
                        str = Integer.toString(num1 - num2, i);
                    }
                    if (!res.equals(str)) {
                        return sb.append(QUESTION_MARK).toString();
                    }
                }
            } else {
                num1 = a[1] * radix + a[0];
                num2 = b[1] * radix + b[0];
                if (op) {
                    res = Integer.toString(num1 + num2, radix);
                } else {
                    res = Integer.toString(num1 - num2, radix);
                }
            }
            return sb.append(res).toString();
        }
    }

    public String[] solution(String[] expressions) {
        int i;
        int len;
        Exp exp;
        String[] ans;
        ArrayList<Exp> results;

        Exp.min = 2;
        results = new ArrayList();
        for (String expression : expressions) {
            exp = new Exp(expression);
            if (exp.c == null) {
                results.add(exp);
            }
        }
        len = results.size();
        ans = new String[len];
        for (i = 0; i < len; i++) {
            ans[i] = results.get(i).eval();
        }
        return ans;
    }
}
