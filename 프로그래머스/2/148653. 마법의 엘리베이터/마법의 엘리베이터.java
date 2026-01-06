class Solution {
	public int solution(int storey) {
		int cnt;
		int digit;

		cnt = 0;
		for (; storey != 0; storey = storey / 10) {
			if ((digit = storey % 10) > 5 || digit == 5 && storey % 100 >= 50) {
				cnt += 10 - digit;
				storey += 10;
			} else {
				cnt += digit;
			}
		}
		return cnt;
	}
}
