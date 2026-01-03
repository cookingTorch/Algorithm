import java.util.Arrays;

class Solution {
    private static final class Num implements Comparable<Num> {
        int num;
        int size;

        Num(int num) {
            this.num = num;
            size = num == 0 ? 10 : (int) Math.pow(10, (int) Math.log10(num) + 1);
        }

        @Override
        public int compareTo(Num o) {
            return o.num * size + num - (num * o.size + o.num);
        }
    }

    public String solution(int[] numbers) {
        int i;
        int len;
        Num[] nums;
        StringBuilder sb;

        len = numbers.length;
        nums = new Num[len];
        for (i = 0; i < len; i++) {
            nums[i] = new Num(numbers[i]);
        }
        Arrays.sort(nums, 0, len);
        sb = new StringBuilder();
        if (nums[0].num == 0) {
            sb.append(0);
        } else {
            for (i = 0; i < len; i++) {
                sb.append(nums[i].num);
            }
        }
        return sb.toString();
    }
}
