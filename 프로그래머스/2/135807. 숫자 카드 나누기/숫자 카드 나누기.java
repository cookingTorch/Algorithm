class Solution {
	private static int getGcd(int a, int b) {
		int r;

		while (b != 0) {
			r = a % b;
			a = b;
			b = r;
		};
		return a;
	}

	public int solution(int[] arrayA, int[] arrayB) {
		int a;
		int b;
		int i;
		int len;

		len = arrayA.length;
		a = arrayA[0];
		b = arrayB[0];
		for (i = 1; i < len; i++) {
			a = getGcd(arrayA[i], a);
			b = getGcd(arrayB[i], b);
		}
		for (i = 0; i < len; i++) {
			if (arrayB[i] % a == 0) {
				a = 0;
				break;
			}
		}
		for (i = 0; i < len; i++) {
			if (arrayA[i] % b == 0) {
				b = 0;
				break;
			}
		}
		return Math.max(a, b);
	}
}
