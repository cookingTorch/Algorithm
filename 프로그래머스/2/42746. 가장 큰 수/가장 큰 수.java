import java.util.Arrays;

class Solution {
	public String solution(int[] numbers) {
		int i;
		int len;
		int[] size;
		Integer[] idx;
		StringBuilder sb;

		len = numbers.length;
		idx = new Integer[len];
		size = new int[len];
		for (i = 0; i < len; i++) {
			idx[i] = i;
			size[i] = numbers[i] == 0 ? 10 : (int) Math.pow(10, (int) Math.log10(numbers[i]) + 1);
		}
		Arrays.sort(idx, 0, len, (o1, o2) -> numbers[o2] * size[o1] + numbers[o1] - (numbers[o1] * size[o2] + numbers[o2]));
		sb = new StringBuilder();
		if (numbers[idx[0]] == 0) {
			sb.append(0);
		} else {
			for (i = 0; i < len; i++) {
				sb.append(numbers[idx[i]]);
			}
		}
		return sb.toString();
	}
}
