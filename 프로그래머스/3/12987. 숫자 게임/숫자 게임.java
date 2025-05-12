import java.util.Arrays;

class Solution {
	public int solution(int[] A, int[] B) {
		int i;
		int idx;

		Arrays.sort(A);
		Arrays.sort(B);
		idx = B.length - 1;
		for (i = A.length - 1; i >= 0; i--) {
			if (A[i] < B[idx]) {
				idx--;
			}
		}
		return B.length - 1 - idx;
	}
}
