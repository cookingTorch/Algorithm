class Solution {
    private static final int GT = '>';
    private static final int LT = '<';
    private static final int DIFF = '0';
    private static final int EQUAL = '=';

    private static final class Query {
        int a;
        int b;
        int op;
        int dist;

        Query(String query) {
            this.a = convert(query.charAt(0));
            this.b = convert(query.charAt(2));
            this.op = query.charAt(3);
            this.dist = query.charAt(4) - DIFF + 1;
        }

        private static int convert(char ch) {
            return switch (ch) {
                case 'A' -> 0;
                case 'C' -> 1;
                case 'F' -> 2;
                case 'J' -> 3;
                case 'M' -> 4;
                case 'N' -> 5;
                case 'R' -> 6;
                case 'T' -> 7;
                default -> -1;
            };
        }

        boolean match(int[] loc) {
            int dist;

            dist = Math.abs(loc[a] - loc[b]);
            return switch (op) {
                case LT -> dist < this.dist;
                case GT -> dist > this.dist;
                case EQUAL -> dist == this.dist;
                default -> false;
            };
        }
    }

    private static boolean nextPermutation(int[] arr) {
        int i;
        int j;
        int tmp;

        for (i = arr.length - 2; i >= 0 && arr[i] >= arr[i + 1]; i--);
        if (i < 0) {
            return false;
        }
        for (j = arr.length - 1; arr[j] <= arr[i]; j--);
        tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        for (++i, j = arr.length - 1; i < j; i++, j--) {
            tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return true;
    }

    public int solution(int n, String[] data) {
        int i;
        int cnt;
        int[] loc;
        Query[] queries;

        loc = new int[] {0, 1, 2, 3, 4, 5, 6, 7};
        queries = new Query[n];
        for (i = 0; i < n; i++) {
            queries[i] = new Query(data[i]);
        }
        cnt = 0;
        do {
            for (i = 0; i < n; i++) {
                if (!queries[i].match(loc)) {
                    break;
                }
            }
            if (i == n) {
                cnt++;
            }
        } while (nextPermutation(loc));
        return cnt;
    }
}
