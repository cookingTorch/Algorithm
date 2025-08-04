import java.util.HashMap;
import java.util.StringTokenizer;

class Solution {
	private static final int ENTER = 'E';
	private static final int LEAVE = 'L';
	private static final int CHANGE = 'C';
	private static final String E = "님이 들어왔습니다.";
	private static final String L = "님이 나갔습니다.";
	private static final String DELIM = " ";

	public String[] solution(String[] record) {
		int i;
		int idx;
		int len;
		int size;
		int[] queries;
		String[] ids;
		String[] ans;
		HashMap<String, String> map;
		StringTokenizer st;

		map = new HashMap<>();
		len = record.length;
		size = len;
		queries = new int[len];
		ids = new String[len];
		for (i = 0; i < len; i++) {
			st = new StringTokenizer(record[i], DELIM, false);
			queries[i] = st.nextToken().charAt(0);
			ids[i] = st.nextToken();
			if (queries[i] == ENTER) {
				map.put(ids[i], st.nextToken());
			} else if (queries[i] == CHANGE) {
				map.put(ids[i], st.nextToken());
				size--;
			}
		}
		ans = new String[size];
		idx = 0;
		for (i = 0; i < len; i++) {
			if (queries[i] == ENTER) {
				ans[idx++] = map.get(ids[i]) + E;
			} else if (queries[i] == LEAVE) {
				ans[idx++] = map.get(ids[i]) + L;
			}
		}
		return ans;
	}
}
