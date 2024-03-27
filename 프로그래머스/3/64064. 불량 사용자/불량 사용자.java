import java.util.ArrayList;

public class Solution {
	private static final char WILDCARD = '*';
	
	private static int ans, bannedNum;
	private static boolean[] visited;
	private static ArrayList<ArrayList<Integer>> adj;
	
	private boolean isConnected(String userId, String bannedId) {
		int len, i;
		
		if ((len = userId.length()) != bannedId.length()) {
			return false;
		}
		for (i = 0; i < len; i++) {
			if (userId.charAt(i) != bannedId.charAt(i) && bannedId.charAt(i) != WILDCARD) {
				return false;
			}
		}
		return true;
	}
	
	private void dfs(int bit, int depth) {
		if (depth == bannedNum) {
			if (!visited[bit]) {
				ans++;
				visited[bit] = true;
			}
			return;
		}
		for (int idx : adj.get(depth)) {
			if ((bit & idx) != 0) {
				continue;
			}
			dfs(bit | idx, depth + 1);
		}
	}

	public int solution(String[] user_id, String[] banned_id) {
		int userNum, bit, i, j;
		
		userNum = user_id.length;
		bannedNum = banned_id.length;
		adj = new ArrayList<>(bannedNum);
		for (i = 0; i < bannedNum; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0, bit = 1; i < userNum; i++, bit <<= 1) {
			for (j = 0; j < bannedNum; j++) {
				if (isConnected(user_id[i], banned_id[j])) {
					adj.get(j).add(bit);
				}
			}
		}
		ans = 0;
		visited = new boolean[1 << userNum];
		dfs(0, 0);
		return ans;
    }
} 