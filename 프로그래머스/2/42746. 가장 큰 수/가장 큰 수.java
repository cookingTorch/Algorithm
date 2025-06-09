import java.util.Arrays;

class Solution {
    private static final char ZERO = '0';

    public String solution(int[] numbers) {
        int i;
        int len;
        String[] arr;
        StringBuilder sb;

        len = numbers.length;
        arr = new String[len];
        for (i = 0; i < len; i++) {
            arr[i] = Integer.toString(numbers[i]);
        }
        Arrays.sort(arr, 0, len, (o1, o2) -> {
            int i1;
            int len1;
            int len2;
            int total;
            char c1;
            char c2;

            len1 = o1.length();
            len2 = o2.length();
            total = len1 + len2;
            for (i1 = 0; i1 < total; i1++) {
                c1 = i1 < len1 ? o1.charAt(i1) : o2.charAt(i1 - len1);
                c2 = i1 < len2 ? o2.charAt(i1) : o1.charAt(i1 - len2);
                if (c1 != c2) {
                    return c2 - c1;
                }
            }
            return 0;
        });
        sb = new StringBuilder();
        if (arr[0].charAt(0) == ZERO) {
            sb.append(ZERO);
        } else {
            for (i = 0; i < len; i++) {
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }
}
