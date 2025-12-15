class Solution {
	public int solution(int[] citations) {
		int i;
		int len;
		int ans;
		int[] cnt;
		
		len = citations.length;
		cnt = new int[len + 1];
		for (i = 0; i < len; i++) {
			if (citations[i] < len) {
				cnt[citations[i]]++;
			} else {
				cnt[len]++;
			}
		}
		ans = 0;
		for (i = len; i > 0; i--) {
			if (cnt[i] >= i) {
				ans = i;
				break;
			}
			cnt[i - 1] += cnt[i];
		}
		return ans;
	}
}
