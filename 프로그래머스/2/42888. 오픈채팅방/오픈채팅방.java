import java.util.HashMap;
import java.util.StringTokenizer;

class Solution {
	private static final int ENTER = 'E';
	private static final int LEAVE = 'L';
	private static final int CHANGE = 'C';
	private static final String E = "님이 들어왔습니다.";
	private static final String L = "님이 나갔습니다.";
	private static final String DELIM = " ";

	private static final class Log {
		int query;
		String id;

		Log(StringTokenizer st) {
			this.query = st.nextToken().charAt(0);
			this.id = st.nextToken();
		}
	}

	public String[] solution(String[] record) {
		int i;
		int idx;
		int len;
		int size;
		int query;
		Log[] logs;
		String[] ans;
		HashMap<String, String> map;
		StringTokenizer st;

		map = new HashMap<>();
		len = record.length;
		size = len;
		logs = new Log[len];
		for (i = 0; i < len; i++) {
			st = new StringTokenizer(record[i], DELIM, false);
			logs[i] = new Log(st);
			query = logs[i].query;
			if (query == ENTER) {
				map.put(logs[i].id, st.nextToken());
			} else if (query == CHANGE) {
				map.put(logs[i].id, st.nextToken());
				size--;
			}
		}
		ans = new String[size];
		idx = 0;
		for (i = 0; i < len; i++) {
			query = logs[i].query;
			if (query == ENTER) {
				ans[idx++] = map.get(logs[i].id) + E;
			} else if (query == LEAVE) {
				ans[idx++] = map.get(logs[i].id) + L;
			}
		}
		return ans;
	}
}
