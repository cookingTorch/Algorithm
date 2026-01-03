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
        Arrays.sort(arr, 0, len, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
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
