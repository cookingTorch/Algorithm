class Solution {
	private static final char WILD = '*';

	private boolean isConnected(char[] banId, char[] usrId) {
		int i;
		int len;

		if ((len = banId.length) != usrId.length) {
			return false;
		}
		for (i = 0; i < len; i++) {
			if (banId[i] != WILD && banId[i] != usrId[i]) {
				return false;
			}
		}
		return true;
	}

	public int solution(String[] user_id, String[] banned_id) {
		int i;
		int j;
		int idx;
		int cnt;
		int ans;
		int banCnt;
		int usrCnt;
		int bitField;
		int[] cnts;
		int[][] usrs;
		char[] banId;
		char[][] usrIds;
		boolean[] dp;

		banCnt = banned_id.length;
		usrCnt = user_id.length;
		usrIds = new char[usrCnt][];
		for (i = 0; i < usrCnt; i++) {
			usrIds[i] = user_id[i].toCharArray();
		}
		usrs = new int[banCnt][usrCnt];
		cnts = new int[banCnt];
		for (i = 0; i < banCnt; i++) {
			banId = banned_id[i].toCharArray();
			for (j = 0; j < usrCnt; j++) {
				if (isConnected(banId, usrIds[j])) {
					usrs[i][cnts[i]++] = 1 << j;
				}
			}
		}
		ans = 0;
		bitField = ((1 << banCnt) - 1 << usrCnt - banCnt) + 1;
		dp = new boolean[bitField];
		dp[0] = true;
		for (i = 0; i < bitField; i++) {
			if (dp[i]) {
				idx = Integer.bitCount(i);
				if (idx == banCnt) {
					ans++;
				} else {
					cnt = cnts[idx];
					for (j = 0; j < cnt; j++) {
						dp[i | usrs[idx][j]] = true;
					}
				}
			}
		}
		return ans;
	}
}
