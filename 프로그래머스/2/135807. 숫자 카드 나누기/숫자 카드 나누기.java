class Solution {
	private static int gcd(int a, int b) {
		int r;

		while (b != 0) {
			r = a % b;
			a = b;
			b = r;
		};
		return a;
	}
	
	private static int calc(int[] arr1, int[] arr2) {
		int a;
		int i;
		int len;

		len = arr1.length;
		a = arr1[0];
		for (i = 1; i < len; i++) {
			if ((a = gcd(arr1[i], a)) == 1) {
				break;
			}
		}
		for (i = 0; i < len; i++) {
			if (arr2[i] % a == 0) {
				return 0;
			}
		}
		return a;
	}

	public int solution(int[] arrayA, int[] arrayB) {
		return Math.max(calc(arrayA, arrayB), calc(arrayB, arrayA));
	}
}
