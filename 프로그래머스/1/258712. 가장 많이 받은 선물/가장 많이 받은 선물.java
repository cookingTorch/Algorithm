import java.util.HashMap;
import java.util.StringTokenizer;

class Solution {
	private static final class Friend {
		int score;
		int[] cnts;
		
		Friend() {
			cnts = new int[num];
		}
		
		final void calc() {
			for (int cnt : cnts) {
				score += cnt;
			}
		}
	}
	
	private static int num;
	
    public int solution(String[] friends, String[] gifts) {
    	int u;
    	int v;
    	int i;
    	int j;
    	int max;
    	int[] results;
    	Friend friend1;
    	Friend friend2;
    	Friend[] friendArr;
        HashMap<String, Integer> map;
        StringTokenizer st;
        
        map = new HashMap<>();
        num = friends.length;
        friendArr = new Friend[num];
        for (i = 0; i < num; i++) {
        	map.put(friends[i], i);
        	friendArr[i] = new Friend();
        }
        for (String gift : gifts) {
        	st = new StringTokenizer(gift);
        	u = map.get(st.nextToken());
        	v = map.get(st.nextToken());
        	friendArr[u].cnts[v]++;
        	friendArr[v].cnts[u]--;
        }
        for (Friend friend : friendArr) {
        	friend.calc();
        }
        results = new int[num];
        for (i = 0; i < num; i++) {
        	friend1 = friendArr[i];
        	for (j = i + 1; j < num; j++) {
        		friend2 = friendArr[j];
        		if (friend1.cnts[j] == 0) {
        			if (friend1.score > friend2.score) {
        				results[i]++;
        			} else if (friend1.score < friend2.score) {
        				results[j]++;
        			}
        		} else if (friend1.cnts[j] > 0) {
        			results[i]++;
        		} else {
        			results[j]++;
        		}
        	}
        }
        max = 0;
        for (int result : results) {
        	if (result > max) {
        		max = result;
        	}
        }
        return max;
    }
}